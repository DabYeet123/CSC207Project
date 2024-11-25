package Transaction.MakeTransaction;

import App.ControllerInterface;
import DataObjects.UserObject;
import LogIn.LoggedIn.LoggedInController;
import LogIn.Welcome.WelcomeController;
import Transaction.DataObject.TransactionController;

public class MakeTransactionController implements ControllerInterface {
    UserObject loggedInUser;
    private MakeTransactionPresenter makeTransactionPresenter;
    private WelcomeController welcomeController;
    private TransactionController transactionController;

    public MakeTransactionController(UserObject user){
        this.loggedInUser = user;
        this.transactionController = new TransactionController();
        this.welcomeController = new WelcomeController();
        this.makeTransactionPresenter = new MakeTransactionPresenter(this);
    }

    @Override
    public void launch(){
        makeTransactionPresenter.showView();
    }

    public void logOutTriggered(){
        makeTransactionPresenter.disposeView();
        welcomeController.launch();
    }

    public boolean makeTransactionTriggered(String cardUsed, int receiverID, int amount){
        if (amount <= loggedInUser.getBalance() && amount > 0) {
            return true;
        }
        return false;
    }

    public void onMakeTransactonSuccess(String cardUsed, int receiverID, int amount) {
        loggedInUser = transactionController.addTransaction(loggedInUser.getUserID(), receiverID, cardUsed, amount);
        makeTransactionPresenter.disposeView();
        LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }
}