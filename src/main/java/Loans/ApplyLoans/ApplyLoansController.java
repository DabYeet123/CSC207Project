package Loans.ApplyLoans;

import App.ControllerInterface;
import Card.CardController;
import DataObjects.UserObject;
import Loans.DataObject.LoansController;
import LogIn.LoggedIn.LoggedInController;
import LogIn.Welcome.WelcomeController;

public class ApplyLoansController implements ControllerInterface {
    UserObject loggedInUser;
    private ApplyLoansPresenter applyLoansPresenter;
    private WelcomeController welcomeController;
    private LoansController loansController;

    public ApplyLoansController(UserObject user){
        this.loggedInUser = user;
        this.loansController = new LoansController();
        this.welcomeController = new WelcomeController();
        this.applyLoansPresenter = new ApplyLoansPresenter(this);
    }

    @Override
    public void launch(){
        applyLoansPresenter.showView();
    }

    public void logOutTriggered(){
        applyLoansPresenter.disposeView();
        welcomeController.launch();
    }

    public boolean applyLoansTriggered(double amount, int term, double rate, String cardNumber){
        CardController cardController = new CardController(loggedInUser);
        return amount > 0 && term > 0 && term < 100 && rate >= 0 && cardController.getCard(cardNumber) != null;
    }

    public void onApplyLoansSuccess(double amount, int term, double rate, String cardUsed) {
        loggedInUser = loansController.addLoans(loggedInUser.getUserID(), amount, term, rate, cardUsed);
        applyLoansPresenter.disposeView();
        LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }

    public void goBackToBaseView() {
        applyLoansPresenter.disposeView();
        LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }
}