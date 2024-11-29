package use_case.loggedin;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface LoggedinInputBoundary {
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
