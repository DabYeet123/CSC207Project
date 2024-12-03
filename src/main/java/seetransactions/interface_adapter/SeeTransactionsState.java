package seetransactions.interface_adapter;

import java.util.ArrayList;
import java.util.List;

import entity.Transaction;
import entity.User;

/**
 * The state for the SeeTransactions View Model.
 */
public class SeeTransactionsState {
    private User user;
    private List<Transaction> transactions = new ArrayList<Transaction>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "SeeTransactionsState{"
                + "user=" + user
                + ", transactions=" + transactions
                + '}';
    }
}