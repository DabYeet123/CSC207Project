package oldstuff.Views.login.loggedin;

import oldstuff.Views.ATM.ATMMap.ATMMapController;
import oldstuff.Views.appold.ControllerInterface;
import brokerage.interface_adapter.BrokerageController;
import oldstuff.Views.Card.CardController;
import oldstuff.Views.Exchange.CurrencyExchangeController;
import oldstuff.Views.House.HouseMap.HouseMapController;
import oldstuff.Views.Loans.ApplyLoans.ApplyLoansController;
import oldstuff.Views.Loans.SeeLoansHistory.SeeLoansHistoryController;
import oldstuff.Views.transaction.makeTransaction.MakeTransactionController;
import oldstuff.Views.transaction.seeTransactionHistory.SeeTransactionHistoryController;
import oldstuff.Views.userdataobject.UserObject;
import oldstuff.Views.login.welcome.WelcomeController;

/**
 * Controller responsible for managing user interactions after login.
 */
public class LoggedInController implements ControllerInterface {
    private UserObject loggedInUser;
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
    public void launch() {
        seeLoansHistoryController.update();
        loggedInPresenter.showView();
    }

    /**
     * Logs the user out and navigates to the welcome screen.
     */
    public void logOutTriggered() {
        loggedInPresenter.disposeView();
        welcomeController.launch();
    }

    /**
     * Navigates to the "Send Money" view.
     */
    public void sendMoneyTriggered() {
        loggedInPresenter.disposeView();
        makeTransactionController.launch();
    }

    /**
     * Navigates to the transaction history view.
     */
    public void seeTransactionHistoryTriggered() {
        loggedInPresenter.disposeView();
        seeTransactionHistoryController.launch();
    }

    /**
     * Navigates to the card management view.
     */
    public void cardTriggered() {
        loggedInPresenter.disposeView();
        cardController.launch();
    }

    /**
     * Navigates to the currency exchange view.
     */
    public void exchangeTriggered() {
        loggedInPresenter.disposeView();
        exchangeController.launch();
    }

    /**
     * Navigates to the asset purchasing view.
     */
    public void buyAssetsTriggered() {
        loggedInPresenter.disposeView();
        brokerageController.launch();
    }

    /**
     * Navigates to the loan application view.
     */
    public void applyLoansTriggered() {
        loggedInPresenter.disposeView();
        applyLoansController.launch();
    }

    /**
     * Navigates to the loan history view.
     */
    public void seeLoansHistoryTriggered() {
        loggedInPresenter.disposeView();
        seeLoansHistoryController.launch();
    }

    /**
     * Navigates to the ATM map view.
     */
    public void atmMapTriggered() {
        loggedInPresenter.disposeView();
        atmMapController.launch();
    }

    /**
     * Navigates to the house map view.
     */
    public void houseMapTriggered() {
        loggedInPresenter.disposeView();
        houseMapController.launch();
    }

    /**
     * Gets the house map controller.
     *
     * @return the house map controller
     */
    public HouseMapController getHouseMapController() {
        return houseMapController;
    }

    public UserObject getLoggedInUser() {
        return loggedInUser;
    }
}

