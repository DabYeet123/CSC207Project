package loans.seeLoansHistory;

import java.util.List;

import App.ControllerInterface;
import DataObjects.UserObject;
import LogIn.LoggedIn.LoggedInController;
import LogIn.Welcome.WelcomeController;
import loans.dataObject.LoansController;
import loans.dataObject.LoansObject;
import lombok.Getter;

@Getter
@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class SeeLoansHistoryController implements ControllerInterface {
    private final UserObject loggedInUser;
    private List<LoansObject> loans;
    private final SeeLoansHistoryPresenter seeLoansHistoryPresenter;
    private final WelcomeController welcomeController;
    private final LoansController loansController;

    public SeeLoansHistoryController(UserObject user) {
        this.loggedInUser = user;
        this.loansController = new LoansController();
        this.loans = loansController.getAllLoans(loggedInUser.getUserID());
        this.welcomeController = new WelcomeController();
        this.seeLoansHistoryPresenter = new SeeLoansHistoryPresenter(this);
    }

    @Override
    public void launch() {
        seeLoansHistoryPresenter.showView();
    }

    public void logOutTriggered() {
        seeLoansHistoryPresenter.disposeView();
        welcomeController.launch();
    }

    public void goBackToBaseView() {
        seeLoansHistoryPresenter.disposeView();
        final LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }

    public void update() {
        this.loans = loansController.getAllLoans(loggedInUser.getUserID());
    }
}
