package maketransaction.use_case;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface MakeTransactionInputBoundary {

    /**
     * Executes the login use case.
     * @param makeTransactionInputData the input data
     */
    void execute(MakeTransactionInputData makeTransactionInputData);

    /**
     * Executes the welcome use case.
     */
    void switchToLoggedinView();
}