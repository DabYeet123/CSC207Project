package Transaction.DataObject;

import java.time.LocalDateTime;

public class TransactionObject {
    int transactionID;
    int senderID;
    int receiverID;
    String cardUsed;
    double amount;
    LocalDateTime timeStamp;

    public TransactionObject() {}

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
    public int getSenderID(){
        return senderID;
    }
    public int getReceiverID(){
        return receiverID;
    }
    public String getCardUsed(){
        return cardUsed;
    }
    public double getAmount(){
        return amount;
    }
    public LocalDateTime getTimeStamp(){
        return timeStamp;
    }
}
