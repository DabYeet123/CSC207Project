package insurance.dataAccess;

import java.util.ArrayList;
import java.util.List;

import DataAccess.DataAccessController;
import DataAccess.DataAccessInterface;
import DataObjects.UserObject;
import DataObjects.UsersController;
import insurance.dataObject.InsuranceObject;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class InsuranceDBAccess implements DataAccessInterface<InsuranceObject> {
    private static final String INSURANCE_JSON = "\\Insurance.json";
    private final DataAccessController controller = new DataAccessController();
    private final UsersController usersController = new UsersController();

    /**
     * Saves a new insurance object for a user.
     *
     * @param userID    The ID of the user.
     * @param insurance The insurance object to be saved.
     * @return The user object associated with the given userID.
     */
    @Override
    public UserObject saveData(int userID, InsuranceObject insurance) {
        final UserObject user = usersController.getUser(userID);
        final List<InsuranceObject> insurances = controller.readData(INSURANCE_JSON, InsuranceObject.class);
        insurances.add(insurance);
        controller.saveData(INSURANCE_JSON, insurances, InsuranceObject.class);
        return user;
    }

    /**
     * Reads insurance data for a user.
     *
     * @param userID The ID of the user.
     * @return A list of insurance objects associated with the user.
     */
    @Override
    public List<InsuranceObject> readData(int userID) {
        return controller.readData(INSURANCE_JSON, InsuranceObject.class);
    }

    /**
     * Retrieves the next available insurance ID.
     *
     * @return The next available insurance ID.
     */
    public int getInsuranceID() {
        final List<InsuranceObject> insurances = controller.readData(INSURANCE_JSON, InsuranceObject.class);
        final int size = insurances.size();
        return insurances.get(size - 1).getInsuranceID() + 1;
    }

    /**
     * Retrieves the latest added insurance object.
     *
     * @return The latest insurance object.
     */
    public InsuranceObject getLatestInsurance() {
        final List<InsuranceObject> insurances = controller.readData(INSURANCE_JSON, InsuranceObject.class);
        final int size = insurances.size();
        return insurances.get(size - 1);
    }

    /**
     * Deletes an insurance policy by its ID.
     *
     * @param insuranceID The ID of the insurance to be deleted.
     */
    public void deleteInsuranceByID(int insuranceID) {
        final List<InsuranceObject> insurances = controller.readData(INSURANCE_JSON, InsuranceObject.class);
        final List<InsuranceObject> newInsurances = new ArrayList<>();
        for (final InsuranceObject insurance : insurances) {
            if (insurance.getInsuranceID() != insuranceID) {
                newInsurances.add(insurance);
            }
        }
        controller.saveData(INSURANCE_JSON, newInsurances, InsuranceObject.class);
    }
}
