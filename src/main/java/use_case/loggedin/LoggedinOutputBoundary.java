package use_case.loggedin;

/**
 * The output boundary for the Welcome Use Case.
 */
public interface LoggedinOutputBoundary {
    /**
     * Executes the switch to login view use case.
     */
    void switchToMakeTransactionView();

    /**
     * Executes the switch to signup view use case.
     */
    void switchToSeeTransactionHistoryView();

    /**
     * Executes the switch to signup view use case.
     */
    void switchToWelcomeView();
}
