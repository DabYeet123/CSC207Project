package loans.adapter;

import java.util.List;

import App.ControllerInterface;
import DataObjects.UserObject;
import LogIn.LoggedIn.LoggedInController;
import LogIn.Welcome.WelcomeController;
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

    /**
     * Constructs a new SeeLoansHistoryController with the given user.
     *
     * @param user The logged-in user.
     */
    public SeeLoansHistoryController(UserObject user) {
        this.loggedInUser = user;
        this.loansController = new LoansController();
        this.loans = loansController.getAllLoans(loggedInUser.getUserID());
        this.welcomeController = new WelcomeController();
        this.seeLoansHistoryPresenter = new SeeLoansHistoryPresenter(this);
    }

    /**
     * Launches the SeeLoansHistory view.
     */
    @Override
    public void launch() {
        seeLoansHistoryPresenter.showView();
    }

    /**
     * Navigates back to the base view for the logged-in user.
     */
    public void goBackToBaseView() {
        seeLoansHistoryPresenter.disposeView();
        final LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }

    /**
     * Updates the list of loans associated with the logged-in user.
     */
    public void update() {
        this.loans = loansController.getAllLoans(loggedInUser.getUserID());
    }
}
