package loggedin.use_case;

import entity.User;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface LoggedinInputBoundary {
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
