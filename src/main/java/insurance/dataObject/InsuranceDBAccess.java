package insurance.dataObject;

import java.util.List;

import DataAccess.DataAccessController;
import DataAccess.DataAccessInterface;
import DataObjects.UserObject;
import DataObjects.UsersController;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class InsuranceDBAccess implements DataAccessInterface<InsuranceObject> {
    private static final String INSURANCE_JSON = "\\Insurance.json";
    private final DataAccessController controller = new DataAccessController();
    private final UsersController usersController = new UsersController();

    @Override
    public UserObject saveData(int userID, InsuranceObject insurance) {
        final UserObject user = usersController.getUser(userID);
        final List<InsuranceObject> insurances = controller.readData(INSURANCE_JSON, InsuranceObject.class);
        insurances.add(insurance);
        controller.saveData(INSURANCE_JSON, insurances, InsuranceObject.class);
        return user;
    }

    @Override
    public List<InsuranceObject> readData(int userID) {
        return controller.readData(INSURANCE_JSON, InsuranceObject.class);
    }
}
