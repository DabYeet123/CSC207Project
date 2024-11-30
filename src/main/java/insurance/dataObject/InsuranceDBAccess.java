package insurance.dataObject;

import java.util.List;

import DataAccess.DataAccessController;
import DataAccess.DataAccessInterface;
import DataObjects.UserObject;
import DataObjects.UsersController;

public class InsuranceDBAccess implements DataAccessInterface<InsuranceObject> {
    DataAccessController controller = new DataAccessController();
    UsersController usersController = new UsersController();

    @Override
    public UserObject saveData(int userID, InsuranceObject insurance) {
        UserObject user = usersController.getUser(userID);
        List<InsuranceObject> insurances = controller.readData("\\Insurance.json", InsuranceObject.class);
        insurances.add(insurance);
        controller.saveData("\\Insurance.json", insurances, InsuranceObject.class);
        return user;
    }

    @Override
    public List<InsuranceObject> readData(int userID) {
        return controller.readData("\\Insurance.json", InsuranceObject.class);
    }
}
