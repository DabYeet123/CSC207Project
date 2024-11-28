package aaaa.entity;

/**
 * Factory for creating users.
 */
public class UserFactory {
    /**
     * Creates a new User.
     * @param userID is a parameter
     * @param firstName is a parameter
     * @param lastName is a parameter
     * @param passwordHash is a parameter
     * @param balance is a parameter
     * @param fileDirectory is a parameter
     * @return the new user
     */
    public User create(int userID, String firstName, String lastName, String passwordHash, double balance,
                               String fileDirectory) {
        return new User(userID, firstName, lastName, passwordHash, balance, fileDirectory);
    }
}
