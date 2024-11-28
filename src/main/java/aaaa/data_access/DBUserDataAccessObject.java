package aaaa.data_access;

import java.util.List;

import userdataobject.UserObject;

/**
 * Provides database access operations for managing `UserObject` instances.
 * This class interacts with the underlying data access controller to perform
 * CRUD (Create, Read, Update, Delete) operations on user data stored in a JSON file.
 */
public class DBUserDataAccessObject {
    private static final String DIRECTORY = "Users.json";
    private DBDataAccessObject controller = new DBDataAccessObject();

    /**
     * Saves a new user to the database.
     * This method reads the current user data from the file, adds the new user,
     * and then saves the updated list back to the file.
     *
     * @param user the `UserObject` to save
     */
    public void saveData(UserObject user) {
        final List<UserObject> users = controller.readData(DIRECTORY, UserObject.class);
        users.add(user);
        controller.saveData(DIRECTORY, users, UserObject.class);
    }

    /**
     * Reads all user data from the database.
     *
     * @return a list of all `UserObject` instances stored in the database
     */
    public List<UserObject> readData() {
        return controller.readData(DIRECTORY, UserObject.class);
    }

    /**
     * Retrieves a user by their unique user ID.
     * This method searches through the list of users and returns the `UserObject`
     * that matches the provided user ID.
     *
     * @param userID the unique ID of the user to retrieve
     * @return the `UserObject` associated with the given user ID, or null if not found
     */
    public UserObject readDataPoint(int userID) {
        final List<UserObject> users = readData();
        UserObject userFound = null;
        for (UserObject user : users) {
            if (user != null && user.getUserID() == userID) {
                userFound = user;
                break;
            }
        }
        return userFound;
    }

    /**
     * Updates an existing user's data in the database.
     * This method searches for the user by their user ID, and if found,
     * updates their information with the provided `UserObject`.
     *
     * @param userID the unique ID of the user to update
     * @param user the updated `UserObject` containing the new user data
     */
    public void updateDataPoint(int userID, UserObject user) {
        final List<UserObject> users = readData();
        for (int i = 0; i < users.size(); i++) {
            if (user != null && users.get(i).getUserID() == userID) {
                users.set(i, user);
            }
        }
        controller.saveData(DIRECTORY, users, UserObject.class);
    }

    /**
     * Returns the number of users stored in the database.
     *
     * @return the number of `UserObject` instances in the database
     */
    public int numberOfUsers() {
        final List<UserObject> users = readData();
        return users.size();
    }
}
