package oldstuff.Views.userdataobject;

/**
 * Manages user-related operations, including adding, retrieving, updating,
 * and checking the existence of users. This class interacts with the database
 * and handles the creation of new users through the `NewUserController`.
 */
public class UsersController {
    private static final int INITIALID = 10000;
    private static int lastUserID;
    private UsersDBAccess usersDBAccess = new UsersDBAccess();
    private NewUserController newUserController = new NewUserController();

    public UsersController() {
        this.lastUserID = INITIALID + usersDBAccess.numberOfUsers();
    }

    /**
     * Adds a new user to the system by creating a new `UserObject` and saving
     * it to the database.
     *
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param password the password for the new user
     * @return the newly created `UserObject`
     */
    public UserObject addUser(String firstName, String lastName, String password) {
        lastUserID += 1;
        final UserObject newUser = newUserController.createNewUser(lastUserID, firstName, lastName, password);
        usersDBAccess.saveData(newUser);
        return newUser;
    }

    /**
     * Retrieves a user by their unique user ID.
     *
     * @param userID the unique ID of the user to retrieve
     * @return the `UserObject` associated with the given user ID
     */
    public UserObject getUser(int userID) {
        return usersDBAccess.readDataPoint(userID);
    }

    /**
     * Updates the information of an existing user in the database.
     *
     * @param userID the unique ID of the user to update
     * @param user the updated `UserObject` containing the new user data
     */
    public void changeUser(int userID, UserObject user) {
        usersDBAccess.updateDataPoint(userID, user);
    }

    /**
     * Checks whether a user exists in the system based on their user ID.
     *
     * @param userID the unique ID of the user to check
     * @return true if the user exists, false otherwise
     */
    public boolean checkUserExistance(int userID) {
        return usersDBAccess.readDataPoint(userID) != null;
    }
}
