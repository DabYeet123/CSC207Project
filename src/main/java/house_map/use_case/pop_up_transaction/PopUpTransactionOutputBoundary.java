package house_map.use_case.pop_up_transaction;

/**
 * The output boundary for the Login Use Case.
 */
public interface PopUpTransactionOutputBoundary {

    /**
     * Updates the current view with the new information.
     * @param popUpTransactionOutput the input data
     */
    void updateView(PopUpTransactionOutput popUpTransactionOutput);

    /**
     * Shows the view.
     */
    void showView();
}
