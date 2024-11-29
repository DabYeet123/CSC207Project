package interface_adapter.loggedin;

import entity.User;

/**
 * The state for the Welcome View Model.
 */
public class LoggedinState {
    private User user = new User();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "LoggedinState{"
                + "user=" + user
                + '}';
    }
}
