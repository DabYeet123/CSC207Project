package HouseMap.UseCase.PopUpTransaction;

public class PopUpTransactionInput {

    private final double price;

    public PopUpTransactionInput(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
