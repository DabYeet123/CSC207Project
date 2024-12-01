package welcome.use_case;

/**
 * The Welcome Interactor.
 */
public class WelcomeInteractor implements WelcomeInputBoundary {
    private final WelcomeOutputBoundary welcomeOutputBoundary;

    public WelcomeInteractor(WelcomeOutputBoundary welcomeOutputBoundary) {
        this.welcomeOutputBoundary = welcomeOutputBoundary;
    }

    @Override
    public void switchToLoginView() {
        welcomeOutputBoundary.switchToLoginView();
    }

    @Override
    public void switchToSignupView() {
        welcomeOutputBoundary.switchToSignupView();
    }
}
