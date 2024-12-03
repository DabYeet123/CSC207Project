package house_map.use_case.pop_up_transaction;

/**
 * The Output Data for the PopUp Transaction use case.
 */
public class PopUpTransactionOutput {

    private final double price;

    public PopUpTransactionOutput(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
