package entity;

/**
 * Factory for creating users.
 */
public class UserFactory {
    /**
     * Creates a new User.
     * @param firstName is a parameter
     * @param lastName is a parameter
     * @param passwordHash is a parameter
     * @return the new user
     */
    public User create(String firstName, String lastName, String passwordHash) {
        return new User(firstName, lastName, passwordHash);
    }
}