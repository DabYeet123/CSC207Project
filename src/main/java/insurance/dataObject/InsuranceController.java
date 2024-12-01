package insurance.dataObject;

import java.util.List;

import DataObjects.UserObject;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class InsuranceController {
    private final InsuranceDBAccess insuranceDBAccess = new InsuranceDBAccess();

    public UserObject addInsurance(int userID, String type, double premium, int insuranceID, String policyDetails,
                                   String insuranceName) {
        final InsuranceObject newInsurance = new InsuranceObject(type, premium, insuranceID, policyDetails,
                insuranceName);
        return insuranceDBAccess.saveData(userID, newInsurance);
    }

    public List<InsuranceObject> getAllInsurance() {
        return insuranceDBAccess.readData(0);
    }
}
