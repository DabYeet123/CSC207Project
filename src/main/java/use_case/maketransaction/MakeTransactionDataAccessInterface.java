package use_case.maketransaction;

import entity.Transaction;
import entity.User;

/**
 * DAO for the Make Transaction Use Case.
 */
public interface MakeTransactionDataAccessInterface {
    /**
     * Saves the user.
     * @param transaction the user to save
     * @return user
     */
    User save(Transaction transaction);

    /**
     * Saves the data of the user with the given username.
     * @param userId the username to look up
     * @param user the user
     * @return user
     */
    User setCurrentUser(int userId, User user);

    /**
     * Saves the data of the user with the given username.
     * @param userId the username to look up
     * @return user
     */
    User getUser(int userId);
}
