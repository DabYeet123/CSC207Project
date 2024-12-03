package oldstuff.Views.login.welcome;

import oldstuff.Views.appold.ControllerInterface;
import oldstuff.Views.login.login.LogInController;
import oldstuff.Views.login.signup.SignUpController;

/**
 * Controller for managing the Welcome view. This class handles user actions
 * related to logging in and signing up by delegating the logic to the respective controllers.
 */
public class WelcomeController implements ControllerInterface {
    private WelcomePresenter welcomePresenter = new WelcomePresenter(this);
    private LogInController logInController = new LogInController();
    private SignUpController signUpController = new SignUpController();

    public WelcomeController() {
    }

    @Override
    public void launch() {
        welcomePresenter.showView();
    }

    /**
     * Handles the log-in action by disposing of the current view and launching the LogInController.
     */
    public void logInTriggered() {
        welcomePresenter.disposeView();
        logInController.launch();
    }

    /**
     * Handles the sign-up action by disposing of the current view and launching the SignUpController.
     */
    public void signUpTriggered() {
        welcomePresenter.disposeView();
        signUpController.launch();
    }
}
