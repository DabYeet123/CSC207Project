package loggedin.use_case;

import entity.User;

/**
 * The LoggedIn Interactor.
 */
public class LoggedinInteractor implements LoggedinInputBoundary {
    private final LoggedinOutputBoundary loggedinOutputBoundary;

    public LoggedinInteractor(LoggedinOutputBoundary loggedinOutputBoundary) {
        this.loggedinOutputBoundary = loggedinOutputBoundary;
    }

    @Override
    public void switchToMakeTransactionView(User user) {
        loggedinOutputBoundary.switchToMakeTransactionView(user);
    }

    @Override
    public void switchToSeeTransactionHistoryView(User user) {
        loggedinOutputBoundary.switchToSeeTransactionHistoryView(user);
    }

    @Override
    public void switchToWelcomeView() {
        loggedinOutputBoundary.switchToWelcomeView();
    }
}
