package transaction.seeTransactionHistory;

import java.util.List;

import appold.ControllerInterface;
import userdataobject.UserObject;
import login.loggedin.LoggedInController;
import transaction.dataObject.TransactionController;
import transaction.dataObject.TransactionObject;

/**
 * Controller for managing the transaction history view. This class retrieves and displays
 * the transaction history of the currently logged-in user. It also provides functionality
 * to navigate back to the logged-in user's dashboard.
 */
public class SeeTransactionHistoryController implements ControllerInterface {
    private UserObject loggedInUser;
    private List<TransactionObject> transactions;
    private SeeTransactionHistoryPresenter seeTransactionHistoryPresenter;
    private TransactionController transactionController;

    public SeeTransactionHistoryController(UserObject user) {
        this.loggedInUser = user;
        this.transactionController = new TransactionController();
        this.transactions = transactionController.getAllTransactions(loggedInUser.getUserID());
        this.seeTransactionHistoryPresenter = new SeeTransactionHistoryPresenter(this);
    }

    public UserObject getLoggedInUser() {
        return loggedInUser;
    }

    public List<TransactionObject> getTransactions() {
        return transactions;
    }

    @Override
    public void launch() {
        seeTransactionHistoryPresenter.showView();
    }

    /**
     * Handles the action of navigating back to the previous view (logged-in user's dashboard).
     * This method is triggered when the back button is pressed.
     */
    public void backTriggered() {
        seeTransactionHistoryPresenter.disposeView();
        final LoggedInController loggedInController = new LoggedInController(loggedInUser);
        loggedInController.launch();
    }
}
