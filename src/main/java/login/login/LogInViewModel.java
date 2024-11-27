package login.login;

import userdataobject.UserObject;

/**
 * ViewModel for managing data between the Login View and the Controller.
 */
public class LogInViewModel {

    private String userID;
    private String password;
    private String message;
    private boolean isLoginSuccessful;

    private final LogInUseCase logInUseCase;

    /**
     * Constructor to initialize the ViewModel with the use case.
     *
     * @param logInUseCase The use case responsible for login logic.
     */
    public LogInViewModel(LogInUseCase logInUseCase) {
        this.logInUseCase = logInUseCase;
    }

    /**
     * Attempts to log in with the current user credentials.
     *
     * @return true if login is successful, false otherwise.
     */
    public boolean attemptLogin() {
        boolean success = false;
        if (userID == null || password == null || userID.isEmpty() || password.isEmpty()) {
            message = "User ID and Password cannot be empty.";
        }
        else {
            final UserObject user = logInUseCase.logIn(Integer.parseInt(userID), password);
            if (user != null) {
                isLoginSuccessful = true;
                message = "Login successful.";
                success = true;
            }
        }

        isLoginSuccessful = false;
        message = "Incorrect UserID or Password.";
        return success;
    }

    // Getters and setters for the view data.
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public boolean isLoginSuccessful() {
        return isLoginSuccessful;
    }
}
