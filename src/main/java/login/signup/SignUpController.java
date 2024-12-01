package login.signup;

import app.ControllerInterface;
import login.loggedin.LoggedInController;
import login.welcome.WelcomeController;
import userdataobject.UserObject;
import userdataobject.UsersController;

/**
 * Controller class for the Sign-Up process.
 */
public class SignUpController implements ControllerInterface {
    private SignUpPresenter signUpPresenter = new SignUpPresenter(this);
    private LoggedInController loggedInController;
    private UsersController usersController = new UsersController();

    @Override
    public void launch() {
        signUpPresenter.showView();
    }

    /**
     * Validates the input data for the Sign-Up process.
     *
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     * @param password  the password of the user
     * @return true if all inputs are valid, false otherwise
     */
    public boolean signUpTriggered(String firstName, String lastName, String password) {
        return (!(firstName.isEmpty() || lastName.isEmpty() || password.isEmpty()));
    }

    /**
     * Handles the logic upon successful Sign-Up.
     *
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     * @param password  the password of the user
     */
    public void onSignUpSuccess(String firstName, String lastName, String password) {
        final UserObject newUser = usersController.addUser(firstName, lastName, password);
        signUpPresenter.disposeView();
        final LoggedInController controller = new LoggedInController(newUser);
        controller.launch();
    }

    public void goBackToWelcomeView() {
        signUpPresenter.disposeView();
        final WelcomeController controller = new WelcomeController();
        controller.launch();
    }
}
