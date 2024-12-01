package insurance.dataObject;

import java.util.List;

import DataObjects.UserObject;

public class InsuranceController {
    private InsuranceDBAccess insuranceDBAccess = new InsuranceDBAccess();

    public UserObject addInsurance(int userID, String type, double premium, int insuranceID, String policyDetails, String insuranceName) {
        InsuranceObject newInsurance = new InsuranceObject(type, premium, insuranceID, policyDetails, insuranceName);
        UserObject user = insuranceDBAccess.saveData(userID, newInsurance);
        return user;
    }

    public List<InsuranceObject> getAllInsurance() {
        return insuranceDBAccess.readData(0);
    }
}
