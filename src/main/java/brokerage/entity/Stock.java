package brokerage.entity;

/**
 * Represents a stock object containing details about a stock's ID, price, and quantity.
 * <p>
 * This class is a simple data structure for managing stock-related information,
 * including stock identifier, current price, and quantity held. It provides
 * getter and setter methods to access and modify the stock details.
 * </p>
 */
public class Stock {
    private String stockID;
    private double price;
    private int quantity;

    /**
     * Default constructor for creating a blank stock object.
     * <p>
     * This constructor initializes the stock object without setting any fields.
     * Fields can be set later using setter methods.
     * </p>
     */
    public Stock() {

    }

    /**
     * Constructs a StockObject with specified stock ID, price, and quantity.
     *
     * @param stockID  the unique identifier of the stock
     * @param price    the current price of the stock
     * @param quantity the quantity of the stock held
     */
    public Stock(String stockID, double price, int quantity) {
        this.stockID = stockID;
        this.price = price;
        this.quantity = quantity;
    }

    public String getStockID() {
        return stockID;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

}
