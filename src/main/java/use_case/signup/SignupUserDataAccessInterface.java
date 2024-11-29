package use_case.signup;

import entity.User;

/**
 * DAO for the Signup Use Case.
 */
public interface SignupUserDataAccessInterface {

    /**
     * Saves the user.
     * @param user the user to save
     */
    void save(User user);
}
