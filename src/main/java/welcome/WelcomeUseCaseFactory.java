package welcome;

import interface_adapter.ViewManagerModel;
import login.interface_adapter.LoginViewModel;
import signup.interface_adapter.SignupViewModel;
import welcome.interface_adapter.WelcomeController;
import welcome.interface_adapter.WelcomePresenter;
import welcome.use_case.WelcomeInputBoundary;
import welcome.use_case.WelcomeInteractor;
import welcome.use_case.WelcomeOutputBoundary;

/**
 * This class contains the static factory function for creating the SignupView.
 */
public final class WelcomeUseCaseFactory {
    /** Prevent instantiation. */
    private WelcomeUseCaseFactory() {

    }

    /**
     * Factory function for creating the WelcomeView.
     * @param viewManagerModel the ViewManagerModel to inject into the SignupView
     * @param signupViewModel the LoginViewModel to inject into the SignupView
     * @param loginViewModel the LoginViewModel to inject into the SignupView
     * @return the WelcomeView created for the provided input classes
     */
    public static WelcomeView create(
            ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel) {

        final WelcomeController welcomeController = createWelcomeUseCase(viewManagerModel, signupViewModel,
                loginViewModel);
        return new WelcomeView(welcomeController);
    }

    private static WelcomeController createWelcomeUseCase(ViewManagerModel viewManagerModel,
                                                            SignupViewModel signupViewModel,
                                                            LoginViewModel loginViewModel) {

        // Notice how we pass this method's parameters to the Presenter.
        final WelcomeOutputBoundary welcomeOutputBoundary = new WelcomePresenter(viewManagerModel, signupViewModel,
                loginViewModel);

        final WelcomeInputBoundary welcomeInputBoundary = new WelcomeInteractor(welcomeOutputBoundary);

        return new WelcomeController(welcomeInputBoundary);
    }
}
