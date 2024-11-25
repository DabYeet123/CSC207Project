package DataObjects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserObject {
    int userID;
    String firstName;
    String lastName;
    String passwordHash;
    double balance;
    String fileDirectory;

    public UserObject(int userID, String firstName, String lastName, String passwordHash, double balance, String fileDirectory) {
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
}
