package insurance.dataAccess;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import cardandexchange.adapter.CardController;
import dataaccess.DataAccessController;
import dataaccess.DataAccessInterface;
import insurance.dataObject.UserInsuranceObject;
import userdataobject.UserObject;
import userdataobject.UsersController;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class UserInsuranceDBAccess implements DataAccessInterface<UserInsuranceObject> {
    private static final String INSURANCE_JSON = "\\Insurance.json";
    private final DataAccessController controller = new DataAccessController();
    private final UsersController usersController = new UsersController();

    /**
     * Saves a new insurance policy for the given user.
     *
     * @param userID    The ID of the user.
     * @param insurance The insurance object to be saved.
     * @return The user object associated with the given userID.
     */
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

    /**
     * Reads the insurance data for a given user.
     *
     * @param userID The ID of the user.
     * @return A list of user insurance objects.
     */
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

    /**
     * Cancels the auto-renewal option for a specific insurance policy by ID.
     *
     * @param userID      The ID of the user.
     * @param insuranceID The ID of the insurance policy to cancel auto-renew for.
     */
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

    /**
     * Updates the user's balance.
     *
     * @param user   The user whose balance is to be updated.
     * @param amount The amount to add to the balance (can be negative).
     */
    private void updateBalance(UserObject user, double amount) {
        user.setBalance(user.getBalance() + amount);
        usersController.changeUser(user.getUserID(), user);
    }

    /**
     * Renews the given insurance policy for another term.
     *
     * @param insurance The insurance object to be renewed.
     * @return A new insurance object with an updated end date.
     */
    private UserInsuranceObject renew(UserInsuranceObject insurance) {
        final LocalDate newEndDate = insurance.getEndDate().plusYears(1);
        return new UserInsuranceObject(insurance.getUserID(), insurance.getInsurance(),
                insurance.getStartDate(), newEndDate, insurance.isAutoRenew(), insurance.getCardUsed());
    }

    /**
     * Cancels the auto-renewal option for the given insurance object.
     *
     * @param insurance The insurance object to cancel auto-renew for.
     * @return A new insurance object with auto-renew set to false.
     */
    private UserInsuranceObject cancelAutoRenew(UserInsuranceObject insurance) {
        final boolean isAutoRenew = false;
        return new UserInsuranceObject(insurance.getUserID(), insurance.getInsurance(),
                insurance.getStartDate(), insurance.getEndDate(), isAutoRenew, insurance.getCardUsed());
    }

    /**
     * Gets the term duration of the given insurance policy in years.
     *
     * @param insurance The insurance object.
     * @return The term duration in years.
     */
    private int getTerm(UserInsuranceObject insurance) {
        return insurance.getEndDate().plusDays(1).getYear() - insurance.getStartDate().getYear();
    }
}
