package house_map.use_case.house_transaction;

/**
 * The output boundary for the Login Use Case.
 */
public interface HouseTransactionOutputBoundary {

    /**
     * Displays a waning message on the current view.
     * @param title the title of the warning message
     * @param text the content of the warning message
     */

    void showMessage(String title, String text);

    /**
     * Updates the current view with the new information.
     * @param houseTransactionOutput the input data
     */
    void updateView(HouseTransactionOutput houseTransactionOutput);

}
