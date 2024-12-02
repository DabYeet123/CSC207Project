package Transaction.SeeTransactionHistory;

import app.ControllerInterface;
import DataObjects.UserObject;
import LogIn.Welcome.WelcomeController;
import Transaction.DataObject.TransactionController;
import Transaction.DataObject.TransactionObject;

import java.util.List;

public class SeeTransactionHistoryController implements ControllerInterface {
    UserObject loggedInUser;
    List<TransactionObject> transactions;
    private SeeTransactionHistoryPresenter seeTransactionHistoryPresenter;
    private WelcomeController welcomeController;
    private TransactionController transactionController;

    public SeeTransactionHistoryController(UserObject user) {
        this.loggedInUser = user;
        this.transactionController = new TransactionController();
        this.transactions = transactionController.getAllTransactions(loggedInUser.getUserID());
        this.welcomeController = new WelcomeController();
        this.seeTransactionHistoryPresenter = new SeeTransactionHistoryPresenter(this);
    }

    @Override
    public void launch(){
        seeTransactionHistoryPresenter.showView();
    }

    public void logOutTriggered(){
        seeTransactionHistoryPresenter.disposeView();
        welcomeController.launch();
    }
}
