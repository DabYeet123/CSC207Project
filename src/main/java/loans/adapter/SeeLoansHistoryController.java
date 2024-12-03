package loans.adapter;

import java.util.List;

import app.ControllerInterface;
import card.adapter.CardController;
import card.dataObject.Card;
import loans.dataObject.LoansObject;
import login.loggedin.LoggedInController;
import login.welcome.WelcomeController;
import lombok.Getter;
import userdataobject.UserObject;

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

    public String showCardInformation(String cardUsed) {
        final CardController cardController = new CardController(loggedInUser);
        Card card = cardController.getCard(cardUsed);
        return card.getId() + " (" + card.getUsage() + ")";
    }
}
