
package seetransactions.use_case;

import java.util.List;

import entity.Transaction;
import entity.User;

/**
 * Output Data for the Login Use Case.
 */
public class SeeTransactionsOutputData {
    private final User user;
    private final List<Transaction> transactions;

    public SeeTransactionsOutputData(User user, List<Transaction> transactions) {
        this.user = user;
        this.transactions = transactions;
    }

    public User getUser() {
        return user;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
