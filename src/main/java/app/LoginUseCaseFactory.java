package app;

// import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin.LoggedinViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import view.LoginView;

/**
 * This class contains the static factory function for creating the LoginView.
 */
public final class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {

    }

    /**
     * Factory function for creating the LoginView.
     * @param viewManagerModel the ViewManagerModel to inject into the LoginView
     * @param welcomeViewModel the LoginViewModel to inject into the LoginView
     * @param loggedinViewModel the LoginViewModel to inject into the LoginView
     * @param loginViewModel the LoginViewModel to inject into the LoginView
     * @param userDataAccessObject the LoginUserDataAccessInterface to inject into the LoginView
     * @return the LoginView created for the provided input classes
     */
    public static LoginView create(ViewManagerModel viewManagerModel, WelcomeViewModel welcomeViewModel,
                                                LoggedinViewModel loggedinViewModel, LoginViewModel loginViewModel,
                                               LoginUserDataAccessInterface userDataAccessObject) {

        final LoginController loginController = createLoginUseCase(viewManagerModel, welcomeViewModel,
                loggedinViewModel, loginViewModel, userDataAccessObject);
        return new LoginView(loginViewModel, loginController);

    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            WelcomeViewModel welcomeViewModel,
            LoggedinViewModel loggedinViewModel,
            LoginViewModel loginViewModel,
            LoginUserDataAccessInterface userDataAccessObject) {

        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, welcomeViewModel,
                loggedinViewModel, loginViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }
}
