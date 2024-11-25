package Brokerage;


public class StockObject {
    private String stockID;
    private double price;
    private int quantity;

    public StockObject() {}

    public StockObject(String stockID, double price, int quantity) {
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
