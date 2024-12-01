package login.use_case;

import entity.User;

/**
 * DAO for the Login Use Case.
 */
public interface LoginUserDataAccessInterface {

    /**
     * Checks if the given username exists.
     * @param userId the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    boolean existsByName(int userId);

    /**
     * Saves the user.
     * @param user the user to save
     */
    void save(User user);

    /**
     * Returns the user with the given username.
     * @param userId the username to look up
     * @return the user with the given username
     */
    User get(int userId);

    /**
     * Saves the data of the user with the given username.
     * @param userId the username to look up
     * @param user the user
     */
    void setCurrentUser(int userId, User user);

    /**
     * Returns the data of the user with the given username.
     * @return the username to look up
     */
    int getCurrentUser();

    /**
     * Returns the data of the user with the given username.
     * @param userId the username to look up
     * @param password the user
     * @return the username to look up
     */
    boolean correspondsToUser(int userId, String password);
}
