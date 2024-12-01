package userdataobject;

/**
 * Represents a user object in the system, holding essential information about the user.
 * This class includes the user's ID, name, password (hashed), balance, and file directory.
 * It provides getter and setter methods to access and modify the user's information.
 */
public class UserObject {
    private int userID;
    private String firstName;
    private String lastName;
    private String passwordHash;
    private double balance;
    private String fileDirectory;

    public UserObject(int userID, String firstName, String lastName, String passwordHash, double balance,
                      String fileDirectory) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passwordHash = passwordHash;
        this.balance = balance;
        this.fileDirectory = fileDirectory;
    }

    public UserObject() {

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
