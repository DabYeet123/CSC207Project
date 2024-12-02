package userdataobject;

/**
 * Controller for creating a new user. This class handles the creation of a new user by
 * assigning a user ID, generating a password hash, initializing the user's balance, and
 * creating a file directory path for the user.
 */
public class NewUserController {
    /**
     * Creates a new user with the specified details and returns a UserObject.
     *
     * @param userID the unique identifier for the new user
     * @param firstName the first name of the new user
     * @param lastName the last name of the new user
     * @param password the password for the new user
     * @return a UserObject containing the details of the newly created user
     */
    public UserObject createNewUser(int userID, String firstName, String lastName, String password) {
        final int newUserID = userID;
        final String newFirstName = firstName;
        final String newLastName = lastName;
        final String passwordHash = generatePasswordHash(password);
        final double newBalance = startingBalance();
        final String fileDirectory = generateFileDirectory(newUserID, newFirstName, newLastName);

        final UserObject newUser = new UserObject(newUserID, newFirstName, newLastName, passwordHash, newBalance,
                fileDirectory);

        return newUser;
    }

    private String generatePasswordHash(String password) {
        return password;
    }

    private double startingBalance() {
        return 0;
    }

    private String generateFileDirectory(int userID, String firstName, String lastName) {
        return firstName + lastName + userID;
    }
}
