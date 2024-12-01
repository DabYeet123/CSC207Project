package loans.applyLoans;

import App.ControllerInterface;
import Card.CardController;
import DataObjects.UserObject;
import LogIn.LoggedIn.LoggedInController;
import loans.dataObject.LoansController;
import lombok.Getter;

@Getter
@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class ApplyLoansController implements ControllerInterface {
    private static final int MAX_TERM = 100;
    private UserObject loggedInUser;
    private final ApplyLoansPresenter applyLoansPresenter;
    private final LoansController loansController;

    public ApplyLoansController(UserObject user) {
        this.loggedInUser = user;
        this.loansController = new LoansController();
        this.applyLoansPresenter = new ApplyLoansPresenter(this);
    }

    @Override
    public void launch() {
        applyLoansPresenter.showView();
    }

    public boolean applyLoansTriggered(Double amount, int term, Double rate, String cardNumber) {
        final CardController cardController = new CardController(loggedInUser);
        return amount.compareTo(0.0) >= 0 && term > 0 && term < MAX_TERM
                && rate.compareTo(0.0) >= 0 && cardController.getCard(cardNumber) != null;
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
