package loggedin.use_case;

import entity.User;

/**
 * The output boundary for the Welcome Use Case.
 */
public interface LoggedinOutputBoundary {
    /**
     * Executes the switch to login view use case.
     * @param user param
     */
    void switchToMakeTransactionView(User user);

    /**
     * Executes the switch to signup view use case.
     * @param user param
     */
    void switchToSeeTransactionHistoryView(User user);

    /**
     * Executes the switch to signup view use case.
     */
    void switchToWelcomeView();
}
