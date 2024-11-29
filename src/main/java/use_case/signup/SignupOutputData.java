package use_case.signup;

import entity.User;

/**
 * Output Data for the Signup Use Case.
 */
public class SignupOutputData {

    private final User user;

    public SignupOutputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
