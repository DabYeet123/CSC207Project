package data_access;

import java.util.List;

import entity.User;
import login.use_case.LoginUserDataAccessInterface;
import signup.use_case.SignupUserDataAccessInterface;

/**
 * Provides database access operations for managing `UserObject` instances.
 * This class interacts with the underlying data access controller to perform
 * CRUD (Create, Read, Update, Delete) operations on user data stored in a JSON file.
 */
public class DBUserDataAccessObject implements LoginUserDataAccessInterface,
        SignupUserDataAccessInterface {
    private static final String DIRECTORY = "Users.json";
    private DBDataAccessObject controller = new DBDataAccessObject();

    public DBUserDataAccessObject() {
    }

    @Override
    public User get(int userId) {
        final List<User> users = readData();
        User userFound = null;
        for (User user : users) {
            if (user != null && user.getUserID() == userId) {
                userFound = user;
                break;
            }
        }
        return userFound;
    }

    @Override
    public void setCurrentUser(int userId, User user) {
        final List<User> users = readData();
        for (int i = 0; i < users.size(); i++) {
            if (user != null && users.get(i).getUserID() == userId) {
                users.set(i, user);
            }
        }
        controller.saveData(DIRECTORY, users, User.class);
    }

    @Override
    public int getCurrentUser() {
        return 0;
    }

    @Override
    public boolean existsByName(int userId) {
        return readDataPoint(userId) != null;
    }

    @Override
    public void save(User user) {
        final List<User> users = controller.readData(DIRECTORY, User.class);
        users.add(user);
        controller.saveData(DIRECTORY, users, User.class);
    }

    @Override
    public boolean correspondsToUser(int userID, String password) {
        boolean success = false;
        if (readDataPoint(userID) != null) {
            final User user = readDataPoint(userID);
            System.out.println(user.getPasswordHash());
            System.out.println(password);
            if (password.equals(user.getPasswordHash())) {
                success = true;
            }
        }
        return success;
    }

    /**
     * Saves a new user to the database.
     * This method reads the current user data from the file, adds the new user,
     * and then saves the updated list back to the file.
     *
     * @param user the `UserObject` to save
     */
    public void saveData(User user) {
        final List<User> users = controller.readData(DIRECTORY, User.class);
        users.add(user);
        controller.saveData(DIRECTORY, users, User.class);
    }

    /**
     * Reads all user data from the database.
     *
     * @return a list of all `UserObject` instances stored in the database
     */
    public List<User> readData() {
        return controller.readData(DIRECTORY, User.class);
    }

    /**
     * Retrieves a user by their unique user ID.
     * This method searches through the list of users and returns the `UserObject`
     * that matches the provided user ID.
     *
     * @param userID the unique ID of the user to retrieve
     * @return the `UserObject` associated with the given user ID, or null if not found
     */
    public User readDataPoint(int userID) {
        final List<User> users = readData();
        User userFound = null;
        for (User user : users) {
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
     * @param user   the updated `UserObject` containing the new user data
     */
    public void updateDataPoint(int userID, User user) {
        final List<User> users = readData();
        for (int i = 0; i < users.size(); i++) {
            if (user != null && users.get(i).getUserID() == userID) {
                users.set(i, user);
            }
        }
        controller.saveData(DIRECTORY, users, User.class);
    }

    /**
     * Returns the number of users stored in the database.
     *
     * @return the number of `UserObject` instances in the database
     */
    public int numberOfUsers() {
        final List<User> users = readData();
        return users.size();
    }

    /**
     * Checks whether a user exists in the system based on their user ID.
     *
     * @param userID the unique ID of the user to check
     * @return true if the user exists, false otherwise
     */
    public boolean checkUserExistance(int userID) {
        return readDataPoint(userID) != null;
    }
}