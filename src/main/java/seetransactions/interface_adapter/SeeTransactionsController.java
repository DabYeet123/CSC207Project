package seetransactions.interface_adapter;

import entity.User;
import seetransactions.use_case.SeeTransactionsInputBoundary;
import seetransactions.use_case.SeeTransactionsInputData;

/**
 * The controller for the See Transactions Use Case.
 */
public class SeeTransactionsController {
    private final SeeTransactionsInputBoundary seeTransactionInteractor;

    public SeeTransactionsController(SeeTransactionsInputBoundary seeTransactionInteractor) {
        this.seeTransactionInteractor = seeTransactionInteractor;
    }

    /**
     * Executes the Make Transaction Use Case.
     * @param user the username of the user logging in
     */
    public void execute(User user) {
        final SeeTransactionsInputData seeTransactionsInputData = new SeeTransactionsInputData(user);

        seeTransactionInteractor.execute(seeTransactionsInputData);
    }

    /**
     * Executes the "switch to WelcomeView" Use Case.
     */
    public void switchToLoggedinView() {
        seeTransactionInteractor.switchToLoggedinView();
    }
}
