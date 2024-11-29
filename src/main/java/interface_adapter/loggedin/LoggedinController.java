package interface_adapter.loggedin;

import use_case.loggedin.LoggedinInputBoundary;

/**
 * Controller for the Loggedin Use Case.
 */
public class LoggedinController {

    private final LoggedinInputBoundary loggedUseCaseInteractor;

    public LoggedinController(LoggedinInputBoundary loggedUseCaseInteractor) {
        this.loggedUseCaseInteractor = loggedUseCaseInteractor;
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToMakeTransactionView() {
        loggedUseCaseInteractor.switchToMakeTransactionView();
    }

    /**
     * Executes the "switch to SignupView" Use Case.
     */
    public void switchToSeeTransactionHistoryView() {
        loggedUseCaseInteractor.switchToSeeTransactionHistoryView();
    }

    /**
     * Executes the "switch to SignupView" Use Case.
     */
    public void switchToWelcomeView() {
        loggedUseCaseInteractor.switchToWelcomeView();
    }
}
