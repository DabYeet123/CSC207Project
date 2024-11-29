package use_case.welcome;

/**
 * Input Boundary for actions which are related to welcome.
 */
public interface WelcomeInputBoundary {
    /**
     * Executes the switch to login view use case.
     */
    void switchToLoginView();

    /**
     * Executes the switch to signup view use case.
     */
    void switchToSignupView();
}
