package login.loggedin;

import atm_map.adapter.AtmMapController;
import house_map.adapter.HouseMapController;
import app.ControllerInterface;
import brokerage.BrokerageController;
import card.adapter.CardController;
import exchange.inferface_adapter.CurrencyExchangeController;
import insurance.adapter.MyInsuranceController;
import insurance.adapter.PurchaseInsuranceController;
import loans.adapter.ApplyLoansController;
import loans.adapter.SeeLoansHistoryController;
import login.welcome.WelcomeController;
import transaction.makeTransaction.MakeTransactionController;
import transaction.seeTransactionHistory.SeeTransactionHistoryController;
import userdataobject.UserObject;

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
    private AtmMapController atmMapController;
    private PurchaseInsuranceController purchaseInsuranceController;
    private MyInsuranceController myInsuranceController;

    public LoggedInController(UserObject user) {
        this.loggedInUser = user;
        this.welcomeController = new WelcomeController();
        this.makeTransactionController = new MakeTransactionController(loggedInUser);
        this.seeTransactionHistoryController = new SeeTransactionHistoryController(loggedInUser);
        this.houseMapController = new HouseMapController(user, this);
        this.atmMapController = new AtmMapController(user, this);
        this.applyLoansController = new ApplyLoansController(loggedInUser);
        this.seeLoansHistoryController = new SeeLoansHistoryController(loggedInUser);
        this.brokerageController = new BrokerageController(loggedInUser);
        this.cardController = new CardController(user);
        this.exchangeController = new CurrencyExchangeController(user);
        this.purchaseInsuranceController = new PurchaseInsuranceController(loggedInUser);
        this.myInsuranceController = new MyInsuranceController(loggedInUser);

        // at last
        this.loggedInPresenter = new LoggedInPresenter(this);
    }

    @Override
    public void launch() {
        seeLoansHistoryController.update();
        myInsuranceController.update();
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
     * Refresh the current window.
     */
    public void refreshTriggered() {
        loggedInPresenter.disposeView();
        seeLoansHistoryController.update();
        myInsuranceController.update();
        cardTriggered();
        cardController.goBackToBaseView();
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
     * Navigates to the purchase insurance view.
     */
    public void purchaseInsuranceTriggered() {
        loggedInPresenter.disposeView();
        purchaseInsuranceController.launch();
    }

    /**
     * Navigates to my insurance view.
     */
    public void myInsuranceTriggered() {
        loggedInPresenter.disposeView();
        myInsuranceController.launch();
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

