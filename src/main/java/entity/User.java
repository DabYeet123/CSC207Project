package entity;

/**
 * Represents a user object in the system.
 */
public class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String passwordHash;
    private double balance;
    private String fileDirectory;

    public User(int userID, String firstName, String lastName, String passwordHash, double balance,
                      String fileDirectory) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passwordHash = passwordHash;
        this.balance = balance;
        this.fileDirectory = fileDirectory;
    }

    public User() {

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
}
