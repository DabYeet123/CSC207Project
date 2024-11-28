package Insurance.DataObject;

import DataAccess.DataAccessController;
import DataAccess.DataAccessInterface;
import DataObjects.UserObject;
import DataObjects.UsersController;

import java.util.ArrayList;
import java.util.List;

public class InsuranceDBAccess implements DataAccessInterface<InsuranceObject> {
    DataAccessController controller = new DataAccessController();
    UsersController usersController = new UsersController();

    @Override
    public UserObject saveData(int userID, InsuranceObject insurance) {
        UserObject user = usersController.getUser(userID);
        List<InsuranceObject> insurances = controller.readData(user.getFileDirectory() + "\\MyInsurance.json", InsuranceObject.class);
        insurances.add(insurance);
        controller.saveData(user.getFileDirectory() + "\\MyInsurance.json", insurances, InsuranceObject.class);
        return user;
    }

    @Override
    public List<InsuranceObject> readData(int userID) {
        UserObject user = usersController.getUser(userID);
        return controller.readData(user.getFileDirectory() + "\\MyInsurance.json", InsuranceObject.class);
    }
}
