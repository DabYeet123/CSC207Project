package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.welcome.WelcomeController;
import interface_adapter.welcome.WelcomePresenter;
import use_case.welcome.WelcomeInputBoundary;
import use_case.welcome.WelcomeInteractor;
import use_case.welcome.WelcomeOutputBoundary;
import view.WelcomeView;

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
     * @param loginViewModel the LoginViewModel to inject into the SignupView
     * @return the WelcomeView created for the provided input classes
     */
    public static WelcomeView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) {

        final WelcomeController welcomeController = createWelcomeUseCase(viewManagerModel, loginViewModel);
        return new WelcomeView(welcomeController);
    }

    private static WelcomeController createWelcomeUseCase(ViewManagerModel viewManagerModel,
                                                            LoginViewModel loginViewModel) {

        // Notice how we pass this method's parameters to the Presenter.
        final WelcomeOutputBoundary welcomeOutputBoundary = new WelcomePresenter(viewManagerModel, loginViewModel);

        final WelcomeInputBoundary welcomeInputBoundary = new WelcomeInteractor(welcomeOutputBoundary);

        return new WelcomeController(welcomeInputBoundary);
    }
}
