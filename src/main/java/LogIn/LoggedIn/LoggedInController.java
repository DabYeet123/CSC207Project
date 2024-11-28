package LogIn.LoggedIn;

import ATMMap.Adapter.ATMMapController;
import Card.CardController;
import Brokerage.BrokerageController;
import App.ControllerInterface;
import DataObjects.UserObject;
import Exchange.CurrencyExchangeController;
import HouseMap.Adapter.HouseMapController;
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
    private CardController cardController;
    private CurrencyExchangeController exchangeController;
    private BrokerageController brokerageController;
    private ApplyLoansController applyLoansController;
    private SeeLoansHistoryController seeLoansHistoryController;
    private HouseMapController houseMapController;
    private ATMMapController atmMapController;

    public LoggedInController(UserObject user) {
        this.loggedInUser = user;
        this.welcomeController = new WelcomeController();
        this.makeTransactionController = new MakeTransactionController(loggedInUser);
        this.seeTransactionHistoryController = new SeeTransactionHistoryController(loggedInUser);
        this.houseMapController = new HouseMapController(user, this);
        this.atmMapController = new ATMMapController(user, this);
        this.applyLoansController = new ApplyLoansController(loggedInUser);
        this.seeLoansHistoryController = new SeeLoansHistoryController(loggedInUser);
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

    public void sendMoneyTriggered() {
        loggedInPresenter.disposeView();
        makeTransactionController.launch();
    }
    public void refreshTriggered() {
        loggedInPresenter.disposeView();
        seeLoansHistoryController.update();
        loggedInPresenter.showView();
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

    public void buyAssetsTriggered() {
        loggedInPresenter.disposeView();
        brokerageController.launch();
    }

    public void applyLoansTriggered() {
        loggedInPresenter.disposeView();
        applyLoansController.launch();
    }

    public void seeLoansHistoryTriggered() {
        loggedInPresenter.disposeView();
        seeLoansHistoryController.launch();
    }

    public void atmMapTriggered() {
        loggedInPresenter.disposeView();
        atmMapController.launch();
    }

    public void houseMapTriggered() {
        loggedInPresenter.disposeView();
        houseMapController.launch();
    }

}

