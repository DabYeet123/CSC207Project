package oldstuff.Views.transaction.dataObject;

import java.time.LocalDateTime;

/**
 * This class provides methods for accessing and updating transaction data in the system. It is responsible
 * for saving transaction data to a file, reading transaction data from a file, and updating user balances
 * after a transaction is processed.
 */
public class TransactionObject {
    private int transactionID;
    private int senderID;
    private int receiverID;
    private String cardUsed;
    private double amount;
    private LocalDateTime timeStamp;

    public TransactionObject() {
    }

    public TransactionObject(int transactionID, int senderID, int receiverID,
                             String cardUsed, double amount, LocalDateTime timeStamp) {
        this.transactionID = transactionID;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.cardUsed = cardUsed;
        this.amount = amount;
        this.timeStamp = timeStamp;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public int getSenderID() {
        return senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public String getCardUsed() {
        return cardUsed;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
