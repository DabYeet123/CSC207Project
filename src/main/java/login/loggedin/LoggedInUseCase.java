package login.loggedin;

import ATM.ATMMap.ATMMapController;
import Brokerage.BrokerageController;
import Card.CardController;
import Exchange.CurrencyExchangeController;
import House.HouseMap.HouseMapController;
import Loans.ApplyLoans.ApplyLoansController;
import Loans.SeeLoansHistory.SeeLoansHistoryController;
import login.welcome.WelcomeController;
import transaction.makeTransaction.MakeTransactionController;
import transaction.seeTransactionHistory.SeeTransactionHistoryController;
import userdataobject.UserObject;

/**
 * Use Case responsible for handling user-related actions.
 */
public class LoggedInUseCase {
    private final WelcomeController welcomeController;
    private final MakeTransactionController makeTransactionController;
    private final SeeTransactionHistoryController seeTransactionHistoryController;
    private final CardController cardController;
    private final CurrencyExchangeController exchangeController;
    private final BrokerageController brokerageController;
    private final ApplyLoansController applyLoansController;
    private final SeeLoansHistoryController seeLoansHistoryController;
    private final HouseMapController houseMapController;
    private final ATMMapController atmMapController;
    private final LoggedInViewModel viewModel;

    @SuppressWarnings("checkstyle:ParameterNumber")
    public LoggedInUseCase(
            WelcomeController welcomeController,
            MakeTransactionController makeTransactionController,
            SeeTransactionHistoryController seeTransactionHistoryController,
            CardController cardController,
            CurrencyExchangeController exchangeController,
            BrokerageController brokerageController,
            ApplyLoansController applyLoansController,
            SeeLoansHistoryController seeLoansHistoryController,
            HouseMapController houseMapController,
            ATMMapController atmMapController) {
        this.welcomeController = welcomeController;
        this.makeTransactionController = makeTransactionController;
        this.seeTransactionHistoryController = seeTransactionHistoryController;
        this.cardController = cardController;
        this.exchangeController = exchangeController;
        this.brokerageController = brokerageController;
        this.applyLoansController = applyLoansController;
        this.seeLoansHistoryController = seeLoansHistoryController;
        this.houseMapController = houseMapController;
        this.atmMapController = atmMapController;
        this.viewModel = new LoggedInViewModel();
    }

    /**
     * Loads user data and updates the view model with the user's information.
     * This method is typically called when the user logs in to set up their welcome message,
     * user ID, and balance information for display.
     *
     * @param user The user whose data is to be loaded into the view model.
     */
    public void loadUserData(UserObject user) {
        // Logic to load user data and update ViewModel
        viewModel.setWelcomeMessage("Welcome, " + user.getFirstName() + " " + user.getLastName());
        viewModel.setUserId(String.valueOf(user.getUserID()));
        viewModel.setBalance("$" + user.getBalance());
    }

    /**
     * Logs out the user by launching the welcome screen through the WelcomeController.
     */
    public void logOut() {
        welcomeController.launch();
    }

    /**
     * Initiates the process to send money by launching the MakeTransactionController.
     */
    public void sendMoney() {
        makeTransactionController.launch();
    }

    /**
     * Views the transaction history by launching the SeeTransactionHistoryController.
     */
    public void viewTransactionHistory() {
        seeTransactionHistoryController.launch();
    }

    /**
     * Manages user cards by launching the CardController.
     */
    public void manageCards() {
        cardController.launch();
    }

    /**
     * Launches the CurrencyExchangeController to manage currency exchange operations.
     */
    public void exchangeCurrency() {
        exchangeController.launch();
    }

    /**
     * Launches the BrokerageController to manage asset buying and brokerage services.
     */
    public void buyAssets() {
        brokerageController.launch();
    }

    /**
     * Launches the ApplyLoansController for users to apply for loans.
     */
    public void applyForLoans() {
        applyLoansController.launch();
    }

    /**
     * Views the loan history by launching the SeeLoansHistoryController.
     */
    public void viewLoansHistory() {
        seeLoansHistoryController.launch();
    }

    /**
     * Views the ATM map by launching the ATMMapController.
     */
    public void viewAtmMap() {
        atmMapController.launch();
    }

    /**
     * Views the house map by launching the HouseMapController.
     */
    public void viewHouseMap() {
        houseMapController.launch();
    }

    public LoggedInViewModel getViewModel() {
        return viewModel;
    }
}
