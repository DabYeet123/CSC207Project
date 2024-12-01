package insurance.dataObject;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import Card.CardController;
import DataAccess.DataAccessController;
import DataAccess.DataAccessInterface;
import DataObjects.UserObject;
import DataObjects.UsersController;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class UserInsuranceDBAccess implements DataAccessInterface<UserInsuranceObject> {
    private static final String INSURANCE_JSON = "\\Insurance.json";
    private final DataAccessController controller = new DataAccessController();
    private final UsersController usersController = new UsersController();

    @Override
    public UserObject saveData(int userID, UserInsuranceObject insurance) {
        final UserObject user = usersController.getUser(userID);
        final CardController cardController = new CardController(user);
        final List<UserInsuranceObject> insurances = controller.readData(user.getFileDirectory()
                + INSURANCE_JSON, UserInsuranceObject.class);
        insurances.add(insurance);
        Collections.sort(insurances);
        if (!insurance.isAutoRenew()) {
            final double amount = insurance.getInsurance().getPremium() * getTerm(insurance);
            updateBalance(user, -amount);
            cardController.updateData(userID, insurance.getCardUsed(), amount);
        }
        controller.saveData(user.getFileDirectory() + INSURANCE_JSON, insurances, UserInsuranceObject.class);
        return user;
    }

    @Override
    public List<UserInsuranceObject> readData(int userID) {
        final UserObject user = usersController.getUser(userID);
        final CardController cardController = new CardController(user);
        final List<UserInsuranceObject> insurances = controller.readData(user.getFileDirectory()
                + INSURANCE_JSON, UserInsuranceObject.class);
        final List<UserInsuranceObject> newInsurances = new LinkedList<>();
        final LocalDate today = LocalDate.now();
        for (UserInsuranceObject insurance : insurances) {
            if (insurance.getEndDate().isBefore(today)) {
                if (insurance.isAutoRenew()) {
                    newInsurances.add(renew(insurance));
                    final double amount = insurance.getInsurance().getPremium();
                    updateBalance(user, -amount);
                    cardController.updateData(userID, insurance.getCardUsed(), amount);
                }
            }
            else {
                newInsurances.add(insurance);
            }
        }
        controller.saveData(user.getFileDirectory() + INSURANCE_JSON, newInsurances, UserInsuranceObject.class);
        return newInsurances;
    }

    public void cancelAutoRenewInsuranceID(int userID, int insuranceID) {
        final UserObject user = usersController.getUser(userID);
        final List<UserInsuranceObject> insurances = readData(user.getUserID());
        final List<UserInsuranceObject> newInsurances = new LinkedList<>();
        for (UserInsuranceObject insurance : insurances) {
            if (insurance.getInsurance().getInsuranceID() == insuranceID) {
                newInsurances.add(cancelAutoRenew(insurance));
            }
            else {
                newInsurances.add(insurance);
            }
        }
        controller.saveData(user.getFileDirectory() + INSURANCE_JSON, newInsurances, UserInsuranceObject.class);
    }

    private void updateBalance(UserObject user, double amount) {
        user.setBalance(user.getBalance() + amount);
        usersController.changeUser(user.getUserID(), user);
    }

    private UserInsuranceObject renew(UserInsuranceObject insurance) {
        final LocalDate newEndDate = insurance.getEndDate().plusYears(1);
        return new UserInsuranceObject(insurance.getUserID(), insurance.getInsurance(),
                insurance.getStartDate(), newEndDate, insurance.isAutoRenew(), insurance.getCardUsed());
    }

    private UserInsuranceObject cancelAutoRenew(UserInsuranceObject insurance) {
        final boolean isAutoRenew = !insurance.isAutoRenew();
        return new UserInsuranceObject(insurance.getUserID(), insurance.getInsurance(),
                insurance.getStartDate(), insurance.getEndDate(), isAutoRenew, insurance.getCardUsed());
    }

    private int getTerm(UserInsuranceObject insurance) {
        return insurance.getEndDate().plusDays(1).getYear() - insurance.getStartDate().getYear();
    }
}
