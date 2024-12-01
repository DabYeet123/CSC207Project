package welcome.interface_adapter;

import welcome.use_case.WelcomeInputBoundary;

/**
 * Controller for the Welcome Use Case.
 */
public class WelcomeController {

    private final WelcomeInputBoundary welcomeUseCaseInteractor;

    public WelcomeController(WelcomeInputBoundary welcomeUseCaseInteractor) {
        this.welcomeUseCaseInteractor = welcomeUseCaseInteractor;
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToLoginView() {
        welcomeUseCaseInteractor.switchToLoginView();
    }

    /**
     * Executes the "switch to SignupView" Use Case.
     */
    public void switchToSignupView() {
        welcomeUseCaseInteractor.switchToSignupView();
    }

}
