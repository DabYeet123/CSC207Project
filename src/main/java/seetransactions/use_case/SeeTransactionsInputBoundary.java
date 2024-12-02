package seetransactions.use_case;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface SeeTransactionsInputBoundary {

    /**
     * Executes the login use case.
     * @param seeTransactionsInputData the input data
     */
    void execute(SeeTransactionsInputData seeTransactionsInputData);

    /**
     * Executes the welcome use case.
     */
    void switchToLoggedinView();
}
