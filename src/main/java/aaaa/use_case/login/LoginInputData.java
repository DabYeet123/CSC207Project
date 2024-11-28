package aaaa.use_case.login;

/**
 * Represents the input data required for the login use case.
 * Contains a user's ID and password.
 */
public class LoginInputData {
    private final int userId;
    private final String password;

    public LoginInputData(int userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    int getUserId() {
        return userId;
    }

    String getPassword() {
        return password;
    }

}