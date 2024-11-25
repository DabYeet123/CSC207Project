package DataObjects;

public class UsersController {
    static int lastUserID;
    UsersDBAccess usersDBAccess = new UsersDBAccess();
    NewUserController newUserController = new NewUserController();

    public UsersController(){
        this.lastUserID = 10000 + usersDBAccess.numberOfUsers();
    }

    public UserObject addUser(String firstName, String lastName, String password) {
        lastUserID += 1;
        UserObject newUser = newUserController.createNewUser(lastUserID, firstName, lastName, password);
        usersDBAccess.saveData(newUser);
        return newUser;
    }

    public UserObject getUser(int userID) {
        return usersDBAccess.readDataPoint(userID);
    }

    public void changeUser(int userID, UserObject user) {
        usersDBAccess.updateDataPoint(userID, user);
    }
}