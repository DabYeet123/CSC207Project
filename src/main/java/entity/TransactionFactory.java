package entity;

import java.time.LocalDateTime;

/**
 * Factory for creating Transaction objects.
 */
public class TransactionFactory {

    /**
     * Creates a new Transaction.
     * @param senderID is a parameter
     * @param receiverID is a parameter
     * @param cardUsed is a parameter
     * @param amount is a parameter
     * @return the new transaction
     */
    public Transaction create(int senderID, int receiverID,
                       String cardUsed, double amount) {

        final LocalDateTime timeStamp = LocalDateTime.now();
        final int transactionID = Integer.parseInt(generateTransactionId(senderID, receiverID, timeStamp));

        return new Transaction(transactionID, senderID, receiverID, cardUsed, amount, timeStamp);
    }

    /**
     * Creates a new Transaction.
     * @param senderID is a parameter
     * @param receiverID is a parameter
     * @param timeStamp is a parameter
     * @return the new transaction
     */
    public String generateTransactionId(int senderID, int receiverID, LocalDateTime timeStamp) {
        return String.valueOf(senderID) + String.valueOf(receiverID) + timeStamp;
    }
}
