package entity;

import data_access.DBUserDataAccessObject;

/**
 * Represents a user object in the system.
 */
public class User {
    private static final int STARTING_USER_ID = 10001;
    private static int lastUserId = STARTING_USER_ID;
    private int userID;
    private String firstName;
    private String lastName;
    private String passwordHash;
    private double balance;
    private String fileDirectory;

    public User() {
    }

    public User(int userID, String firstName, String lastName, String passwordHash, double balance,
                String fileDirectory) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passwordHash = passwordHash;
        this.balance = balance;
        this.fileDirectory = fileDirectory;
    }

    public User(String firstName, String lastName, String passwordHash) {
        this.userID = generateUserID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.passwordHash = passwordHash;
        this.balance = generateStartingBalance();
        this.fileDirectory = generateFileDirectory();
    }

    public int getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public double getBalance() {
        return balance;
    }

    public String getFileDirectory() {
        return fileDirectory;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Generates a new unique user ID by incrementing the last used user ID.
     *
     * @return the newly generated user ID.
     */
    public int generateUserID() {
        final DBUserDataAccessObject dbUserDataAccessObject = new DBUserDataAccessObject();
        lastUserId = lastUserId + dbUserDataAccessObject.numberOfUsers();
        return lastUserId;
    }

    /**
     * Generates a new unique user ID by incrementing the last used user ID.
     *
     * @return the newly generated user ID.
     */
    public double generateStartingBalance() {
        return 0;
    }

    /**
     * Generates a file directory string based on the user's first name, last name, and user ID.
     *
     * @return the generated file directory as a concatenated string.
     */
    public String generateFileDirectory() {
        return firstName + lastName + userID;
    }
}