package DataObjects;

public class NewUserController {

    public UserObject createNewUser(int userID, String firstName, String lastName, String password){
        int newUserID = userID;
        String newFirstName = firstName;
        String newLastName = lastName;
        String passwordHash = generatePasswordHash(password);
        double newBalance = startingBalance();
        String fileDirectory = generateFileDirectory(newUserID, newFirstName, newLastName);

        UserObject newUser = new UserObject(newUserID, newFirstName, newLastName, passwordHash, newBalance, fileDirectory);

        return newUser;
    }

    private String generatePasswordHash(String password) {
        return password;
    }

    private double startingBalance() {
        return 0;
    }

    private String generateFileDirectory(int userID, String firstName, String lastName){
        return firstName + lastName + userID;
    }
}
