package entity;

import java.time.LocalDateTime;

/**
 * Represents a transaction object in the system.
 */
public class Transaction {
    private String transactionID;
    private int senderID;
    private int receiverID;
    private String cardUsed;
    private double amount;
    private LocalDateTime timeStamp;

    public Transaction() {
    }

    public Transaction(String transactionID, int senderID, int receiverID,
                             String cardUsed, double amount, LocalDateTime timeStamp) {
        this.transactionID = transactionID;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.cardUsed = cardUsed;
        this.amount = amount;
        this.timeStamp = timeStamp;
    }

    public String getTransactionID() {
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
