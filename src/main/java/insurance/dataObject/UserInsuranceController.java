package insurance.dataObject;

import java.util.List;

import DataObjects.UserObject;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class UserInsuranceController {
    private final UserInsuranceDBAccess userInsuranceDBAccess = new UserInsuranceDBAccess();

    public UserObject addInsurance(int userID, InsuranceObject insurance, int term,
                                   boolean autoRenew, String cardNumber) {
        final UserInsuranceObject newInsurance = new UserInsuranceObject(userID, insurance, term,
                autoRenew, cardNumber);
        return userInsuranceDBAccess.saveData(userID, newInsurance);
    }

    public List<UserInsuranceObject> getAllInsurance(int userID) {
        return userInsuranceDBAccess.readData(userID);
    }

    public void cancelAutoRenewByInsuranceID(int userID, int InsuranceID) {
        userInsuranceDBAccess.cancelAutoRenewInsuranceID(userID, InsuranceID);
    }

    public boolean isPurchased(int userID, InsuranceObject insurance) {
        boolean res = false;
        final List<UserInsuranceObject> insurances = getAllInsurance(userID);
        for (UserInsuranceObject purchasedInsurance : insurances) {
            if (purchasedInsurance.getInsurance().getInsuranceID() == insurance.getInsuranceID()) {
                res = true;
            }
        }
        return res;
    }
}
