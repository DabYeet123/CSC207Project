package transaction.makeTransaction;

import java.util.List;

import Card.Card;
import Card.CardDBAccess;
import app.ControllerInterface;
import login.loggedin.LoggedInController;
import transaction.dataObject.TransactionController;
import userdataobject.UserObject;

/**
 * Controller for managing the make transaction functionality. This class handles the logic of creating
 * and processing a transaction, ensuring that the user has sufficient balance and directing the flow
 * to either a success or failure state.
 */
public class MakeTransactionController implements ControllerInterface {
    private UserObject loggedInUser;
    private MakeTransactionPresenter makeTransactionPresenter;
    private TransactionController transactionController;
    private CardDBAccess cardDBAccess;

    public MakeTransactionController(UserObject user) {
        this.loggedInUser = user;
        this.transactionController = new TransactionController();
        this.cardDBAccess = new CardDBAccess();
        this.makeTransactionPresenter = new MakeTransactionPresenter(this);
    }

    public UserObject getLoggedInUser() {
        return loggedInUser;
    }

    @Override
    public void launch() {
        makeTransactionPresenter.showView();
    }

    /**
     * Handles the action when the user clicks "Back" in the transaction view.
     * This method disposes of the transaction view and returns to the logged-in user view.
     */
    public void backTriggered() {
        makeTransactionPresenter.disposeView();
        final LoggedInController loggedInController = new LoggedInController(loggedInUser);
        loggedInController.launch();
    }

    /**
     * Validates whether the user can make the transaction based on their current balance.
     * Ensures the transaction amount is positive and does not exceed the user's balance.
     *
     * @param cardUsed the card used for the transaction
     * @param receiverID the ID of the receiver of the transaction
     * @param amount the amount to be transferred in the transaction
     * @return true if the transaction can be made, false otherwise
     */
    public boolean makeTransactionTriggered(String cardUsed, int receiverID, int amount) {
        return amount <= loggedInUser.getBalance() && amount > 0;
    }

    /**
     * Handles the action when a transaction is successfully made.
     * The transaction is added to the user's transaction history, and the user balance is updated.
     * After the transaction is completed, the logged-in user view is launched.
     *
     * @param cardUsed the card used for the transaction
     * @param receiverID the ID of the receiver of the transaction
     * @param amount the amount of money involved in the transaction
     */
    public void onMakeTransactonSuccess(String cardUsed, int receiverID, int amount) {
        loggedInUser = transactionController.addTransaction(loggedInUser.getUserID(), receiverID, cardUsed, amount);

        makeTransactionPresenter.disposeView();
        final LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }

    /**
     * Retrieve cards from database.
     *
     * @return array of cards
     */
    public String[] getCards() {
        final List<Card> cards = cardDBAccess.readData(loggedInUser.getUserID());
        final String[] cardNames = new String[cards.size()];
        for (int i = 0; i < cards.size(); i++) {
            cardNames[i] = cards.get(i).getUsage();
        }
        return cardNames;
    }
}
