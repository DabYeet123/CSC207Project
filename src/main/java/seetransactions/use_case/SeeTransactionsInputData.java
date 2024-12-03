package seetransactions.use_case;

import entity.User;

/**
 * Represents the input data required for the login use case.
 * Contains a user's ID and password.
 */
public class SeeTransactionsInputData {
    private final User user;

    public SeeTransactionsInputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}