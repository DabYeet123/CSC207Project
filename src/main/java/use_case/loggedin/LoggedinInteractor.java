package use_case.loggedin;

/**
 * The LoggedIn Interactor.
 */
public class LoggedinInteractor implements LoggedinInputBoundary {
    private final LoggedinOutputBoundary loggedinOutputBoundary;

    public LoggedinInteractor(LoggedinOutputBoundary loggedinOutputBoundary) {
        this.loggedinOutputBoundary = loggedinOutputBoundary;
    }

    @Override
    public void switchToMakeTransactionView() {
        loggedinOutputBoundary.switchToMakeTransactionView();
    }

    @Override
    public void switchToSeeTransactionHistoryView() {
        loggedinOutputBoundary.switchToSeeTransactionHistoryView();
    }

    @Override
    public void switchToWelcomeView() {
        loggedinOutputBoundary.switchToWelcomeView();
    }
}
