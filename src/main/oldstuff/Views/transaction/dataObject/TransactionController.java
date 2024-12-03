package oldstuff.Views.transaction.dataObject;

import java.util.List;

import oldstuff.Views.userdataobject.UserObject;

/**
 * Controller class responsible for handling transactions. This class interacts with the data access layer
 * for saving and retrieving transaction data, as well as creating new transactions.
 */
public class TransactionController {
    private TransactionDBAccess transactionDBAccess = new TransactionDBAccess();
    private NewTransactionController newTransactionController = new NewTransactionController();

    /**
     * Adds a new transaction to the system by creating a new transaction object and saving it to the database.
     *
     * @param senderID    the ID of the user sending the money
     * @param receiverID  the ID of the user receiving the money
     * @param cardUsed    the card used for the transaction
     * @param amount      the amount of money being transferred
     * @return the {@link UserObject} associated with the sender after the transaction is added
     */
    public UserObject addTransaction(int senderID, int receiverID, String cardUsed, double amount) {
        final TransactionObject newTransaction = newTransactionController.createNewTransaction(senderID, receiverID,
                cardUsed, amount);
        final UserObject user = transactionDBAccess.saveData(senderID, newTransaction);

        return user;
    }

    /**
     * Retrieves all transactions associated with a particular user.
     *
     * @param userID the ID of the user whose transactions are to be retrieved
     * @return a list of {@link TransactionObject} representing all transactions associated with the user
     */
    public List<TransactionObject> getAllTransactions(int userID) {
        return transactionDBAccess.readData(userID);
    }
}
