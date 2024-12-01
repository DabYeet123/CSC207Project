package login.login;

import app.ControllerInterface;
import userdataobject.UserObject;
import userdataobject.UsersController;
import login.loggedin.LoggedInController;
import login.welcome.WelcomeController;

/**
 * Controller class for handling user login operations.
 */
public class LogInController implements ControllerInterface {
    private LogInPresenter logInPresenter = new LogInPresenter(this);
    private LoggedInController loggedInController;
    private UsersController usersController = new UsersController();

    public LogInController() {
    }

    @Override
    public void launch() {
        logInPresenter.showView();
    }

    /**
     * Handles login attempt by verifying user credentials.
     *
     * @param userID   the user's ID
     * @param password the user's password
     * @return true if the credentials are valid, false otherwise
     */
    public boolean logInTriggered(int userID, String password) {
        final UserObject user = usersController.getUser(userID);
        return user != null && user.getPasswordHash().equals(password);
    }

    /**
     * Handles successful login, transitioning to the logged-in view.
     *
     * @param userID the ID of the successfully logged-in user
     */
    public void onLoginSuccess(int userID) {
        final UserObject user = usersController.getUser(userID);
        logInPresenter.disposeView();
        loggedInController = new LoggedInController(user);
        loggedInController.launch();
    }

    /**
     * Navigates back to the welcome view.
     */
    public void goBackToWelcomeView() {
        logInPresenter.disposeView();
        final WelcomeController controller = new WelcomeController();
        controller.launch();
    }
}
