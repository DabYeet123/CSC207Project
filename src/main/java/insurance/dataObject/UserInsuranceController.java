package insurance.dataObject;

import java.util.List;

import DataObjects.UserObject;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class UserInsuranceController {
    private final UserInsuranceDBAccess userInsuranceDBAccess = new UserInsuranceDBAccess();

    /**
     * Adds a new insurance policy for the given user.
     *
     * @param userID       The ID of the user.
     * @param insurance    The insurance object to be added.
     * @param term         The term duration of the insurance in years.
     * @param autoRenew    Indicates whether the insurance is set to auto-renew.
     * @param cardNumber   The card number used for payment.
     * @return The user object associated with the given userID.
     */
    public UserObject addInsurance(int userID, InsuranceObject insurance, int term,
                                   boolean autoRenew, String cardNumber) {
        final UserInsuranceObject newInsurance = new UserInsuranceObject(userID, insurance, term,
                autoRenew, cardNumber);
        return userInsuranceDBAccess.saveData(userID, newInsurance);
    }

    /**
     * Retrieves all insurance policies associated with a user.
     *
     * @param userID The ID of the user.
     * @return A list of all user insurance objects.
     */
    public List<UserInsuranceObject> getAllInsurance(int userID) {
        return userInsuranceDBAccess.readData(userID);
    }

    /**
     * Cancels the auto-renewal option for a specific insurance policy.
     *
     * @param userID       The ID of the user.
     * @param InsuranceID  The ID of the insurance policy to cancel auto-renew for.
     */
    public void cancelAutoRenewalByInsuranceID(int userID, int InsuranceID) {
        userInsuranceDBAccess.cancelAutoRenewInsuranceID(userID, InsuranceID);
    }

    /**
     * Checks if a specific insurance policy has been purchased by a user.
     *
     * @param userID    The ID of the user.
     * @param insurance The insurance object to check.
     * @return True if the insurance policy has been purchased by the user, false otherwise.
     */
    public boolean isPurchased(int userID, InsuranceObject insurance) {
        boolean res = false;
        final List<UserInsuranceObject> insurances = getAllInsurance(userID);
        for (UserInsuranceObject purchasedInsurance : insurances) {
            if (purchasedInsurance.getInsurance().getInsuranceID() == insurance.getInsuranceID()) {
                res = true;
                break;
            }
        }
        return res;
    }
}
