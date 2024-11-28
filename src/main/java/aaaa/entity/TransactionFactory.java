package aaaa.entity;

import java.time.LocalDateTime;

/**
 * Factory for creating Transaction objects.
 */
public class TransactionFactory {

    /**
     * Creates a new Transaction.
     * @param transactionID is a parameter
     * @param senderID is a parameter
     * @param receiverID is a parameter
     * @param cardUsed is a parameter
     * @param amount is a parameter
     * @param timeStamp is a parameter
     * @return the new transaction
     */
    public Transaction create(int transactionID, int senderID, int receiverID,
                       String cardUsed, double amount, LocalDateTime timeStamp) {
        return new Transaction(transactionID, senderID, receiverID, cardUsed, amount, timeStamp);
    }
}
