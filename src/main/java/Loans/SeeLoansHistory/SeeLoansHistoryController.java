package Loans.SeeLoansHistory;

import App.ControllerInterface;
import DataObjects.UserObject;
import Loans.DataObject.LoansController;
import Loans.DataObject.LoansObject;
import LogIn.LoggedIn.LoggedInController;
import LogIn.Welcome.WelcomeController;

import java.util.List;

public class SeeLoansHistoryController implements ControllerInterface {
    UserObject loggedInUser;
    List<LoansObject> loans;
    private SeeLoansHistoryPresenter seeLoansHistoryPresenter;
    private WelcomeController welcomeController;
    private LoansController loansController;

    public SeeLoansHistoryController(UserObject user) {
        this.loggedInUser = user;
        this.loansController = new LoansController();
        this.loans = loansController.getAllLoans(loggedInUser.getUserID());
        this.welcomeController = new WelcomeController();
        this.seeLoansHistoryPresenter = new SeeLoansHistoryPresenter(this);
    }

    @Override
    public void launch(){
        seeLoansHistoryPresenter.showView();
    }

    public void logOutTriggered(){
        seeLoansHistoryPresenter.disposeView();
        welcomeController.launch();
    }

    public void goBackToBaseView() {
        seeLoansHistoryPresenter.disposeView();
        LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }

    public void update() {
        this.loans = loansController.getAllLoans(loggedInUser.getUserID());
    }
}
