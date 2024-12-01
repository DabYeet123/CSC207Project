package interface_adapter.login;

/**
 * The state for the Login View Model.
 */
public class LoginState {
    private int userId;
    private String loginError = "";
    private String password = "";

    public int getUserId() {
        return userId;
    }

    public String getLoginError() {
        return loginError;
    }

    public String getPassword() {
        return password;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setLoginError(String usernameError) {
        this.loginError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginState{"
                + "userId=" + userId
                + ", loginError='" + loginError + '\''
                + ", password='" + password + '\''
                + '}';
    }
}
