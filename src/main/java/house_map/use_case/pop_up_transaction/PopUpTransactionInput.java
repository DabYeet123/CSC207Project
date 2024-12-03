package house_map.use_case.pop_up_transaction;

/**
 * The Input Data for the PopUp Transaction use case.
 */
public class PopUpTransactionInput {

    private final double price;

    public PopUpTransactionInput(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
