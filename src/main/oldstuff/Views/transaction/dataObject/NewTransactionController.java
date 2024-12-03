package oldstuff.Views.transaction.dataObject;

import java.time.LocalDateTime;

/**
 * Controller for creating a new transaction. This class handles the logic to generate a new
 * transaction with details such as sender ID, receiver ID, the card used, transaction amount, and timestamp.
 */
public class NewTransactionController {
    /**
     * Creates a new transaction with the provided details.
     *
     * @param senderID    the ID of the sender of the transaction
     * @param receiverID  the ID of the receiver of the transaction
     * @param cardUsed    the card used for the transaction
     * @param amount      the amount of money in the transaction
     * @return a new {@link TransactionObject} representing the created transaction
     */
    public TransactionObject createNewTransaction(int senderID, int receiverID, String cardUsed, double amount) {
        final int newTransactionID = generateTransactionID();
        final int newSenderID = senderID;
        final int newReceiverID = receiverID;
        final String newCardUsed = cardUsed;
        final double newAmount = amount;
        final LocalDateTime newTimeStamp = getTimeStamp();

        final TransactionObject newTransaction = new TransactionObject(newTransactionID, newSenderID, newReceiverID,
                newCardUsed, newAmount, newTimeStamp);

        return newTransaction;
    }

    private int generateTransactionID() {
        return 1;
    }

    private LocalDateTime getTimeStamp() {
        return LocalDateTime.now();
    }
}
