package signup;

import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import loggedin.interface_adapter.LoggedinViewModel;
import signup.interface_adapter.SignupController;
import signup.interface_adapter.SignupPresenter;
import signup.interface_adapter.SignupViewModel;
import welcome.interface_adapter.WelcomeViewModel;
import signup.use_case.SignupInputBoundary;
import signup.use_case.SignupInteractor;
import signup.use_case.SignupOutputBoundary;
import signup.use_case.SignupUserDataAccessInterface;

/**
 * This class contains the static factory function for creating the SignupView.
 */
public final class SignupUseCaseFactory {

    /** Prevent instantiation. */
    private SignupUseCaseFactory() {

    }

    /**
     * Factory function for creating the SignupView.
     * @param viewManagerModel the ViewManagerModel to inject into the SignupView
     * @param welcomeViewModel the LoginViewModel to inject into the LoginView
     * @param loggedinViewModel the SignupViewModel to inject into the SignupView
     * @param signupViewModel the SignupViewModel to inject into the SignupView
     * @param userDataAccessObject the SignupUserDataAccessInterface to inject into the SignupView
     * @return the LoginView created for the provided input classes
     */
    public static SignupView create(
            ViewManagerModel viewManagerModel, WelcomeViewModel welcomeViewModel,
            LoggedinViewModel loggedinViewModel, SignupViewModel signupViewModel,
            SignupUserDataAccessInterface userDataAccessObject) {

        final SignupController signupController = createUserSignupUseCase(viewManagerModel, welcomeViewModel,
                loggedinViewModel, signupViewModel, userDataAccessObject);
        return new SignupView(signupController, signupViewModel);

    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel,
                                                            WelcomeViewModel welcomeViewModel,
                                                            LoggedinViewModel loggedinViewModel,
                                                            SignupViewModel signupViewModel,
                                                            SignupUserDataAccessInterface userDataAccessObject) {

        // Notice how we pass this method's parameters to the Presenter.
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, welcomeViewModel,
                loggedinViewModel, signupViewModel);

        final UserFactory userFactory = new UserFactory();

        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }
}
