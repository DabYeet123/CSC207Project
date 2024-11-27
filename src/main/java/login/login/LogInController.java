package login.login;

import app.ControllerInterface;
import login.loggedin.LoggedInController;
import login.welcome.WelcomeController;
import userdataobject.UserObject;

/**
 * Controller responsible for handling user login actions.
 */
public class LogInController implements ControllerInterface {

    private final LogInUseCase logInUseCase;
    private final LogInPresenter logInPresenter;

    /**
     * Constructs the LogInController with the given use case and presenter.
     *
     * @param logInUseCase The use case that handles the login logic.
     * @param logInPresenter The presenter that handles the display logic.
     */
    public LogInController(LogInUseCase logInUseCase, LogInPresenter logInPresenter) {
        this.logInUseCase = logInUseCase;
        this.logInPresenter = logInPresenter;
    }

    /**
     * Launches the login view.
     */
    @Override
    public void launch() {
        logInPresenter.showView();
    }

    /**
     * Handles login attempt by verifying user credentials using the use case.
     *
     * @param userID   The user's ID.
     * @param password The user's password.
     */
    public void logInTriggered(int userID, String password) {
        final UserObject user = logInUseCase.logIn(userID, password);
        if (user != null) {
            onLoginSuccess(user);
        }
        else {
            logInPresenter.showError("Incorrect UserID or Password");
        }
    }

    /**
     * Handles successful login and transitions to the logged-in view.
     *
     * @param user The successfully logged-in user.
     */
    private void onLoginSuccess(UserObject user) {
        logInPresenter.disposeView();
        final LoggedInController loggedInController = new LoggedInController(user);
        loggedInController.launch();
    }

    /**
     * Navigates back to the welcome view.
     */
    public void goBackToWelcomeView() {
        logInPresenter.disposeView();
        final WelcomeController welcomeController = new WelcomeController();
        welcomeController.launch();
    }
}
