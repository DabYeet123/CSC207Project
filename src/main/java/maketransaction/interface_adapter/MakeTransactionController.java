package maketransaction.interface_adapter;

import entity.User;
import maketransaction.use_case.MakeTransactionInputBoundary;
import maketransaction.use_case.MakeTransactionInputData;

/**
 * The controller for the Make Transaction Use Case.
 */
public class MakeTransactionController {
    private final MakeTransactionInputBoundary makeTransactionInteractor;

    public MakeTransactionController(MakeTransactionInputBoundary makeTransactionInteractor) {
        this.makeTransactionInteractor = makeTransactionInteractor;
    }

    /**
     * Executes the Make Transaction Use Case.
     * @param user the username of the user logging in
     * @param receiverId the password of the user logging in
     * @param card the password of the user logging in
     * @param amount the password of the user logging in
     */
    public void execute(User user, int receiverId, String card, double amount) {
        final MakeTransactionInputData makeTransactionInputData = new MakeTransactionInputData(
                user, receiverId, card, amount);

        makeTransactionInteractor.execute(makeTransactionInputData);
    }

    /**
     * Executes the "switch to WelcomeView" Use Case.
     */
    public void switchToLoggedinView() {
        makeTransactionInteractor.switchToLoggedinView();
    }
}
