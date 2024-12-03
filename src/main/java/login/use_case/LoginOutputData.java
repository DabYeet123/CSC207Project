package login.use_case;

import entity.User;

/**
 * Output Data for the Login Use Case.
 */
public class LoginOutputData {

    private final User user;

    public LoginOutputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}