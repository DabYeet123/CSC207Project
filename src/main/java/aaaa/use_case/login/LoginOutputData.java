package aaaa.use_case.login;

import aaaa.entity.User;

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
