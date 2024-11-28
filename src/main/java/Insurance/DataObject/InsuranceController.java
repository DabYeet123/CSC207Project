package Insurance.DataObject;

import DataObjects.UserObject;
import java.util.List;

public class InsuranceController {
    private InsuranceDBAccess insuranceDBAccess = new InsuranceDBAccess();

    public UserObject addInsurance(int userID, String type, double premium, int term, boolean autoRenew) {
        InsuranceObject newInsurance = new InsuranceObject(userID, type, premium, term, autoRenew);
        UserObject user = insuranceDBAccess.saveData(userID, newInsurance);
        return user;
    }

    public List<InsuranceObject> getAllInsurance(int userID) {
        return insuranceDBAccess.readData(userID);
    }
}
