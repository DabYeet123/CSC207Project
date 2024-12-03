package seetransactions.use_case;

/**
 * The output boundary for the Login Use Case.
 */
public interface SeeTransactionsOutputBoundary {
    /**
     * Prepares the success view for the Login Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(SeeTransactionsOutputData outputData);

    /**
     * Switches to the Welcome View.
     */
    void switchToLoggedinView();
}