package maketransaction.use_case;

import entity.User;

/**
 * Output Data for the Login Use Case.
 */
public class MakeTransactionOutputData {
    private final User user;

    public MakeTransactionOutputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
