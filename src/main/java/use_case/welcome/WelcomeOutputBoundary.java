package use_case.welcome;

/**
 * The output boundary for the Welcome Use Case.
 */
public interface WelcomeOutputBoundary {
    /**
     * Switches to the Login View.
     */
    void switchToLoginView();

    /**
     * Switches to the Signup View.
     */
    void switchToSignupView();
}
