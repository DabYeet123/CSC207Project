package maketransaction.interface_adapter;

import entity.User;

/**
 * The state for the MakeTransaction View Model.
 */
public class MakeTransactionState {
    private User user;
    private int receiverId;
    private String card;
    private double amount;
    private String transactionError;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionError() {
        return transactionError;
    }

    public void setTransactionError(String transactionError) {
        this.transactionError = transactionError;
    }

    @Override
    public String toString() {
        return "MakeTransactionState{"
                + "user=" + user
                + ", receiverId=" + receiverId
                + ", card='" + card + '\''
                + ", amount=" + amount
                + ", transactionError='" + transactionError + '\''
                + '}';
    }
}
