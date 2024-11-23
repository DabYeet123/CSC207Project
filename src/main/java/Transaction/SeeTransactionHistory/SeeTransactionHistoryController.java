package Transaction.SeeTransactionHistory;

import DataObjects.UserObject;
import LogIn.Welcome.WelcomeController;
import Transaction.DataObject.TransactionController;
import Transaction.DataObject.TransactionObject;

import java.util.ArrayList;
import java.util.List;

public class SeeTransactionHistoryController {
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

    public void launch(){
        seeTransactionHistoryPresenter.showView();
    }

    public void logOutTriggered(){
        seeTransactionHistoryPresenter.disposeView();
        welcomeController.launch();
    }
}
