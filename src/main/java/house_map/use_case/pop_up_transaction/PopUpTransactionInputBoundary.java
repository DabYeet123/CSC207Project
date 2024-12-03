package house_map.use_case.pop_up_transaction;

/**
 * Input Boundary for actions which are related to the Popup Transaction.
 */
public interface PopUpTransactionInputBoundary {

    /**
     * Execute the popup transaction use case.
     * @param popUpTransactionInput the input data
     */
    void execute(PopUpTransactionInput popUpTransactionInput);

    /**
     * Updates the balance of the card.
     * @param price the price being updated
     */
    void updateBalance(double price);
}
