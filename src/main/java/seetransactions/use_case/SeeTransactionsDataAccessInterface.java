package seetransactions.use_case;

import java.util.List;

import entity.Transaction;
import entity.User;

/**
 * DAO for the See Transactions Use Case.
 */
public interface SeeTransactionsDataAccessInterface {
    /**
     * Saves the user.
     * @param user the user to save
     * @return List of Transactions
     */
    List<Transaction> getTransactions(User user);
}
