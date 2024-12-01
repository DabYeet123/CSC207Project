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

    /**
     * Constructs a new ApplyLoansController with the given user.
     *
     * @param user The logged-in user.
     */
    public ApplyLoansController(UserObject user) {
        this.loggedInUser = user;
        this.loansController = new LoansController();
        this.applyLoansPresenter = new ApplyLoansPresenter(this);
    }

    /**
     * Launches the ApplyLoans view.
     */
    @Override
    public void launch() {
        applyLoansPresenter.showView();
    }

    /**
     * Handles the process of applying for a loan.
     *
     * @param amount     The loan amount.
     * @param term       The term of the loan in years.
     * @param rate       The interest rate for the loan.
     * @param cardNumber The card number used for repayment.
     * @return True if the loan can be applied for; false otherwise.
     */
    public boolean applyLoansTriggered(Double amount, int term, Double rate, String cardNumber) {
        final CardController cardController = new CardController(loggedInUser);
        return amount.compareTo(0.0) >= 0 && term > 0 && term < MAX_TERM
                && rate.compareTo(0.0) >= 0 && cardController.getCard(cardNumber) != null;
    }

    /**
     * Completes the loan application process.
     *
     * @param amount    The loan amount.
     * @param term      The term of the loan in years.
     * @param rate      The interest rate for the loan.
     * @param cardUsed  The card used for repayment.
     */
    public void onApplyLoansSuccess(double amount, int term, double rate, String cardUsed) {
        loggedInUser = loansController.addLoans(loggedInUser.getUserID(), amount, term, rate, cardUsed);
        applyLoansPresenter.disposeView();
        final LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }

    /**
     * Navigates back to the base view for the logged-in user.
     */
    public void goBackToBaseView() {
        applyLoansPresenter.disposeView();
        final LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }
}
