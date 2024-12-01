package loans.applyLoans;

import App.ControllerInterface;
import Card.CardController;
import DataObjects.UserObject;
import LogIn.LoggedIn.LoggedInController;
import LogIn.Welcome.WelcomeController;
import loans.dataObject.LoansController;
import lombok.Getter;

@Getter
@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class ApplyLoansController implements ControllerInterface {
    private static final int MAX_TERM = 100;
    private UserObject loggedInUser;
    private final ApplyLoansPresenter applyLoansPresenter;
    private final WelcomeController welcomeController;
    private final LoansController loansController;

    public ApplyLoansController(UserObject user) {
        this.loggedInUser = user;
        this.loansController = new LoansController();
        this.welcomeController = new WelcomeController();
        this.applyLoansPresenter = new ApplyLoansPresenter(this);
    }

    @Override
    public void launch() {
        applyLoansPresenter.showView();
    }

    public void logOutTriggered() {
        applyLoansPresenter.disposeView();
        welcomeController.launch();
    }

    public boolean applyLoansTriggered(double amount, int term, double rate, String cardNumber) {
        final CardController cardController = new CardController(loggedInUser);
        return amount > 0 && term > 0 && term < MAX_TERM && rate >= 0 && cardController.getCard(cardNumber) != null;
    }

    public void onApplyLoansSuccess(double amount, int term, double rate, String cardUsed) {
        loggedInUser = loansController.addLoans(loggedInUser.getUserID(), amount, term, rate, cardUsed);
        applyLoansPresenter.disposeView();
        final LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }

    public void goBackToBaseView() {
        applyLoansPresenter.disposeView();
        final LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }
}
