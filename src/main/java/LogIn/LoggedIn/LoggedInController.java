package LogIn.LoggedIn;

import Brokerage.BrokerageController;
import Card.CardController;
import App.ControllerInterface;
import DataObjects.UserObject;
import Exchange.CurrencyExchangeController;
import Insurance.MyInsurance.MyInsuranceController;
import Insurance.PurchaseInsurance.PurchaseInsuranceController;
import Loans.ApplyLoans.ApplyLoansController;
import Loans.SeeLoansHistory.SeeLoansHistoryController;
import LogIn.Welcome.WelcomeController;
import Transaction.MakeTransaction.MakeTransactionController;
import Transaction.SeeTransactionHistory.SeeTransactionHistoryController;

public class LoggedInController implements ControllerInterface {
    UserObject loggedInUser;
    private LoggedInPresenter loggedInPresenter;
    private WelcomeController welcomeController;
    private MakeTransactionController makeTransactionController;
    private SeeTransactionHistoryController seeTransactionHistoryController;
    private BrokerageController brokerageController;
    private CardController cardController;
    private CurrencyExchangeController exchangeController;
    private ApplyLoansController applyLoansController;
    private SeeLoansHistoryController seeLoansHistoryController;
    private PurchaseInsuranceController purchaseInsuranceController;
    private MyInsuranceController myInsuranceController;

    public LoggedInController(UserObject user) {
        this.loggedInUser = user;
        this.welcomeController = new WelcomeController();
        this.makeTransactionController = new MakeTransactionController(loggedInUser);
        this.seeTransactionHistoryController = new SeeTransactionHistoryController(loggedInUser);
        this.applyLoansController = new ApplyLoansController(loggedInUser);
        this.seeLoansHistoryController = new SeeLoansHistoryController(loggedInUser);
        this.purchaseInsuranceController = new PurchaseInsuranceController(loggedInUser);
        this.myInsuranceController = new MyInsuranceController(loggedInUser);
        this.brokerageController = new BrokerageController(loggedInUser);
        this.cardController = new CardController(user);
        this.exchangeController = new CurrencyExchangeController(user);

        // at last
        this.loggedInPresenter = new LoggedInPresenter(this);
    }

    @Override
    public void launch(){
        seeLoansHistoryController.update();
        loggedInPresenter.showView();
    }

    public void logOutTriggered() {
        loggedInPresenter.disposeView();
        welcomeController.launch();
    }

    public void refreshTriggered() {
        loggedInPresenter.disposeView();
        seeLoansHistoryController.update();
        loggedInPresenter.showView();
    }

    public void sendMoneyTriggered() {
        loggedInPresenter.disposeView();
        makeTransactionController.launch();
    }

    public void seeTransactionHistoryTriggered() {
        loggedInPresenter.disposeView();
        seeTransactionHistoryController.launch();
    }

    public void cardTriggered() {
        loggedInPresenter.disposeView();
        cardController.launch();
    }

    public void exchangeTriggered() {
        loggedInPresenter.disposeView();
        exchangeController.launch();
    }

    public void applyLoansTriggered() {
        loggedInPresenter.disposeView();
        applyLoansController.launch();
    }

    public void seeLoansHistoryTriggered() {
        loggedInPresenter.disposeView();
        seeLoansHistoryController.launch();
    }

    public void purchaseInsuranceTriggered() {
        loggedInPresenter.disposeView();
        purchaseInsuranceController.launch();
    }

    public void myInsuranceTriggered() {
        loggedInPresenter.disposeView();
        myInsuranceController.launch();
    }

    public void buyAssetsTriggered() {
        loggedInPresenter.disposeView();
        //buyAssetsController.launch();
    }
}

