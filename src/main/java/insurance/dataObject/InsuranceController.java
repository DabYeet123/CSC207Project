package insurance.dataObject;

import java.util.List;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class InsuranceController {
    private final InsuranceDBAccess insuranceDBAccess = new InsuranceDBAccess();

    /**
     * Adds a new insurance policy for the given user.
     *
     * @param userID        The ID of the user.
     * @param type          The type of the insurance.
     * @param premium       The premium amount for the insurance.
     * @param policyDetails The details of the insurance policy.
     * @param insuranceName The name of the insurance.
     */
    public void addInsurance(int userID, String type, double premium, String policyDetails,
                             String insuranceName) {
        final int insuranceID = insuranceDBAccess.getInsuranceID();
        final InsuranceObject newInsurance = new InsuranceObject(type, premium, insuranceID, policyDetails,
                insuranceName);
        insuranceDBAccess.saveData(userID, newInsurance);
    }

    /**
     * Deletes an existing insurance policy.
     *
     * @param insurance The insurance object to be deleted.
     */
    public void deleteInsurance(InsuranceObject insurance) {
        insuranceDBAccess.deleteInsuranceByID(insurance.getInsuranceID());
    }

    /**
     * Retrieves the latest added insurance policy.
     *
     * @return The latest insurance object.
     */
    public InsuranceObject getLatestInsurance() {
        return insuranceDBAccess.getLatestInsurance();
    }

    /**
     * Retrieves all insurance policies.
     *
     * @return A list of all insurance objects.
     */
    public List<InsuranceObject> getAllInsurance() {
        return insuranceDBAccess.readData(0);
    }
}
