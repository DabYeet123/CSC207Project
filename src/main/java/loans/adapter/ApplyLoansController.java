package loans.adapter;

import app.ControllerInterface;
import card.adapter.CardController;
import card.dataAccess.CardDBAccess;
import card.dataObject.Card;
import login.loggedin.LoggedInController;
import lombok.Getter;
import userdataobject.UserObject;

import java.util.List;

@Getter
@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class ApplyLoansController implements ControllerInterface {
    private static final int MAX_TERM = 1000;
    private UserObject loggedInUser;
    private final ApplyLoansPresenter applyLoansPresenter;
    private final CardDBAccess cardDBAccess;
    private final LoansController loansController;

    /**
     * Constructs a new ApplyLoansController with the given user.
     *
     * @param user The logged-in user.
     */
    public ApplyLoansController(UserObject user) {
        this.loggedInUser = user;
        this.loansController = new LoansController();
        this.cardDBAccess = new CardDBAccess();
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
     * @return True if the loan can be applied for; false otherwise.
     */
    public boolean applyLoansTriggered(Double amount, int term, Double rate) {
        final CardController cardController = new CardController(loggedInUser);
        return amount.compareTo(0.0) >= 0 && term > 0 && term < MAX_TERM && rate.compareTo(0.0) >= 0;
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

    public List<Card> getCards() {
        return cardDBAccess.readData(loggedInUser.getUserID());
    }
}
