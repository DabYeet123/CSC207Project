package interface_adapter.loggedin;

import entity.User;
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
     * @param user param
     */
    public void switchToMakeTransactionView(User user) {
        loggedUseCaseInteractor.switchToMakeTransactionView(user);
    }

    /**
     * Executes the "switch to SignupView" Use Case.
     * @param user param
     */
    public void switchToSeeTransactionHistoryView(User user) {
        loggedUseCaseInteractor.switchToSeeTransactionHistoryView(user);
    }

    /**
     * Executes the "switch to SignupView" Use Case.
     */
    public void switchToWelcomeView() {
        loggedUseCaseInteractor.switchToWelcomeView();
    }
}
