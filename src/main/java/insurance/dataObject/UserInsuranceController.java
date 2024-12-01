package insurance.dataObject;

import java.util.List;

import DataObjects.UserObject;

public class UserInsuranceController {
    private final UserInsuranceDBAccess userInsuranceDBAccess = new UserInsuranceDBAccess();

    public UserObject addInsurance(int userID, InsuranceObject insurance, int term, boolean autoRenew, String cardNumber) {
        UserInsuranceObject newInsurance = new UserInsuranceObject(userID, insurance, term, autoRenew, cardNumber);
        UserObject user = userInsuranceDBAccess.saveData(userID, newInsurance);
        return user;
    }

    public List<UserInsuranceObject> getAllInsurance(int userID) {
        return userInsuranceDBAccess.readData(userID);
    }

    public boolean isPurchased(int userID, InsuranceObject insurance) {
        final List<UserInsuranceObject> insurances = getAllInsurance(userID);
        for (UserInsuranceObject purchasedInsurance : insurances) {
            if (purchasedInsurance.getInsurance().getInsuranceID() == insurance.getInsuranceID()) {
                return true;
            }
        }
        return false;
    }
}
