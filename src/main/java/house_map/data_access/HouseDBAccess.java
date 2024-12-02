package house_map.data_access;

import java.util.ArrayList;
import java.util.List;

import DataAccess.DataAccessController;
import DataAccess.DataAccessInterface;
import DataObjects.UserObject;
import DataObjects.UsersController;
import house_map.data_object.HouseObject;

/**
 * This class handles saving and reading house data to/from a JSON file.
 * It communicates with a DataAccessController to perform the data operations and a UsersController
 * to interact with user-specific data.
 */
public class HouseDBAccess implements DataAccessInterface<HouseObject> {

    private static String directory = "";
    private final DataAccessController controller = new DataAccessController();
    private final UsersController usersController = new UsersController();

    /**
     * Saves house data to a JSON file. If a house with the same address already exists, it replaces
     * the existing entry with the new house data.
     *
     * @param userID the ID of the user performing the save operation (not used in this implementation)
     * @param house the HouseObject instance representing the house to be saved
     * @return a UserObject instance, though it currently returns null (can be extended for user-specific operations)
     */
    @Override
    public UserObject saveData(int userID, HouseObject house) {
        final List<HouseObject> houses = readData(0);
        final List<HouseObject> newHouses = new ArrayList<>();

        // Check if the house with the same address exists
        for (HouseObject h: houses) {
            if (h.getAddress().equals(house.getAddress())) {
                newHouses.add(house);
            }
            else {
                newHouses.add(h);
            }
        }

        // Save the updated list of houses to the JSON file
        controller.saveData("\\Houses.json", newHouses, HouseObject.class);
        return null;
    }

    /**
     * Reads house data from a JSON file and returns the list of house objects.
     *
     * @param userID the ID of the user whose data is being read (not used in this implementation)
     * @return a List of HouseObject instances representing all houses from the stored data
     */
    @Override
    public List<HouseObject> readData(int userID) {
        return controller.readData("\\Houses.json", HouseObject.class);
    }
}
