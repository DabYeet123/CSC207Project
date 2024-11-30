package insurance.dataObject;

import DataAccess.DataAccessController;
import DataAccess.DataAccessInterface;
import DataObjects.UserObject;
import DataObjects.UsersController;

import java.util.List;

public class UserInsuranceDBAccess implements DataAccessInterface<UserInsuranceObject> {
    DataAccessController controller = new DataAccessController();
    UsersController usersController = new UsersController();

    @Override
    public UserObject saveData(int userID, UserInsuranceObject insurance) {
        UserObject user = usersController.getUser(userID);
        List<UserInsuranceObject> insurances = controller.readData(user.getFileDirectory() + "\\Insurance.json", UserInsuranceObject.class);
        insurances.add(insurance);
        controller.saveData(user.getFileDirectory() + "\\Insurance.json", insurances, UserInsuranceObject.class);
        return user;
    }

    @Override
    public List<UserInsuranceObject> readData(int userID) {
        UserObject user = usersController.getUser(userID);
        return controller.readData(user.getFileDirectory() + "\\Insurance.json", UserInsuranceObject.class);
    }
}
