package maketransaction.use_case;

import entity.User;

/**
 * Represents the input data required for the login use case.
 * Contains a user's ID and password.
 */
public class MakeTransactionInputData {
    private final User user;
    private final int receiverId;
    private final String card;
    private final double amount;

    public MakeTransactionInputData(User user, int receiverId, String card, double amount) {
        this.user = user;
        this.receiverId = receiverId;
        this.card = card;
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public String getCard() {
        return card;
    }

    public double getAmount() {
        return amount;
    }
}