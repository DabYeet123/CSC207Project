package insurance.dataObject;

import java.util.List;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class InsuranceController {
    private final InsuranceDBAccess insuranceDBAccess = new InsuranceDBAccess();

    public void addInsurance(int userID, String type, double premium, String policyDetails,
                                   String insuranceName) {
        final int insuranceID = insuranceDBAccess.getInsuranceID();
        final InsuranceObject newInsurance = new InsuranceObject(type, premium, insuranceID, policyDetails,
                insuranceName);
        insuranceDBAccess.saveData(userID, newInsurance);
    }

    public void deleteInsurance(InsuranceObject insurance) {
        insuranceDBAccess.deleteInsuranceByID(insurance.getInsuranceID());
    }

    public InsuranceObject getLatestInsurance() {
        return insuranceDBAccess.getLatestInsurance();
    }

    public List<InsuranceObject> getAllInsurance() {
        return insuranceDBAccess.readData(0);
    }
}
