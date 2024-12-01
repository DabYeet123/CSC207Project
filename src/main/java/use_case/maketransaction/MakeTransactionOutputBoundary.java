package use_case.maketransaction;

/**
 * The output boundary for the Login Use Case.
 */
public interface MakeTransactionOutputBoundary {
    /**
     * Prepares the success view for the Login Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(MakeTransactionOutputData outputData);

    /**
     * Prepares the failure view for the Login Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Welcome View.
     */
    void switchToLoggedinView();
}
