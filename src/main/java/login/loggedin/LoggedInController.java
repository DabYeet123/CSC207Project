package login.loggedin;

import app.ControllerInterface;
import userdataobject.UserObject;

/**
 * Controller responsible for managing user interactions after login.
 */
public class LoggedInController implements ControllerInterface {
    private final UserObject loggedInUser;
    private final LoggedInPresenter loggedInPresenter;
    private final LoggedInUseCase loggedInUseCase;

    public LoggedInController(UserObject user, LoggedInUseCase loggedInUseCase) {
        this.loggedInUser = user;
        this.loggedInUseCase = loggedInUseCase;
        this.loggedInPresenter = new LoggedInPresenter(this, loggedInUseCase);
    }

    @Override
    public void launch() {
        loggedInUseCase.loadUserData(loggedInUser);
        loggedInPresenter.showView();
    }

    /**
     * Handles the logout action, triggers the logout process, and disposes of the view.
     */
    public void logOutTriggered() {
        loggedInUseCase.logOut();
        loggedInPresenter.disposeView();
    }

    /**
     * Handles the send money action, triggers the send money process, and disposes of the view.
     */
    public void sendMoneyTriggered() {
        loggedInUseCase.sendMoney();
        loggedInPresenter.disposeView();
    }

    /**
     * Handles the view transaction history action, triggers the transaction history view, and disposes of the view.
     */
    public void seeTransactionHistoryTriggered() {
        loggedInUseCase.viewTransactionHistory();
        loggedInPresenter.disposeView();
    }

    /**
     * Handles the manage cards action, triggers the card management process, and disposes of the view.
     */
    public void cardTriggered() {
        loggedInUseCase.manageCards();
        loggedInPresenter.disposeView();
    }

    /**
     * Handles the exchange currency action, triggers the currency exchange process, and disposes of the view.
     */
    public void exchangeTriggered() {
        loggedInUseCase.exchangeCurrency();
        loggedInPresenter.disposeView();
    }

    /**
     * Handles the buy assets action, triggers the asset buying process, and disposes of the view.
     */
    public void buyAssetsTriggered() {
        loggedInUseCase.buyAssets();
        loggedInPresenter.disposeView();
    }

    /**
     * Handles the apply for loans action, triggers the loan application process, and disposes of the view.
     */
    public void applyLoansTriggered() {
        loggedInUseCase.applyForLoans();
        loggedInPresenter.disposeView();
    }

    /**
     * Handles the view loans history action, triggers the loan history view, and disposes of the view.
     */
    public void seeLoansHistoryTriggered() {
        loggedInUseCase.viewLoansHistory();
        loggedInPresenter.disposeView();
    }

    /**
     * Handles the view ATM map action, triggers the ATM map view, and disposes of the view.
     */
    public void atmMapTriggered() {
        loggedInUseCase.viewAtmMap();
        loggedInPresenter.disposeView();
    }

    /**
     * Handles the view house map action, triggers the house map view, and disposes of the view.
     */
    public void houseMapTriggered() {
        loggedInUseCase.viewHouseMap();
        loggedInPresenter.disposeView();
    }

    public UserObject getLoggedInUser() {
        return loggedInUser;
    }
}
