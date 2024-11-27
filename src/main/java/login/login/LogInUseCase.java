package login.login;

import userdataobject.UserObject;
import userdataobject.UsersController;

/**
 * Use Case responsible for handling user login logic.
 */
public class LogInUseCase {

    private final UsersController usersController;

    /**
     * Constructs the LogInUseCase with the given UsersController.
     *
     * @param usersController The controller that manages user data.
     */
    public LogInUseCase(UsersController usersController) {
        this.usersController = usersController;
    }

    /**
     * Attempts to log in with the given credentials.
     *
     * @param userID   The user's ID.
     * @param password The user's password.
     * @return A UserObject if login is successful, otherwise null.
     */
    public UserObject logIn(int userID, String password) {
        UserObject newUser = null;
        final UserObject user = usersController.getUser(userID);
        if (user != null && user.getPasswordHash().equals(password)) {
            newUser = user;
        }
        return newUser;
    }
}
