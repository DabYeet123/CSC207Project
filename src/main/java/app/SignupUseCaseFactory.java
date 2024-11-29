package app;

import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;
import view.SignupView;

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
     * @param signupViewModel the SignupViewModel to inject into the SignupView
     * @param userDataAccessObject the SignupUserDataAccessInterface to inject into the SignupView
     * @return the LoginView created for the provided input classes
     */
    public static SignupView create(
            ViewManagerModel viewManagerModel, WelcomeViewModel welcomeViewModel,
            SignupViewModel signupViewModel, SignupUserDataAccessInterface userDataAccessObject) {

        final SignupController signupController = createUserSignupUseCase(viewManagerModel, welcomeViewModel,
                signupViewModel, userDataAccessObject);
        return new SignupView(signupController, signupViewModel);

    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel,
                                                            WelcomeViewModel welcomeViewModel,
                                                            SignupViewModel signupViewModel,
                                                            SignupUserDataAccessInterface userDataAccessObject) {

        // Notice how we pass this method's parameters to the Presenter.
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, welcomeViewModel,
                signupViewModel);

        final UserFactory userFactory = new UserFactory();

        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }
}
