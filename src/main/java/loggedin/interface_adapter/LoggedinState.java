package loggedin.interface_adapter;

import entity.User;

/**
 * The state for the Welcome View Model.
 */
public class LoggedinState {
    private User user;
    private String loggedinError;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLoggedinError() {
        return loggedinError;
    }

    public void setLoggedinError(String loggedInError) {
        this.loggedinError = loggedInError;
    }

    @Override
    public String toString() {
        return "LoggedinState{"
                + "user=" + user
                + '}';
    }
}