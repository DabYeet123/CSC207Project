package brokerage.use_case;

import brokerage.entity.Stock;
import userdataobject.UserObject;

public class BrokerageInputData {
    private final UserObject user;
    private final String stockSymbol;
    private final int quantity;
    private final Stock stock;

    public BrokerageInputData(UserObject user, String stockSymbol, int quantity, Stock stock) {
        this.user = user;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.stock = stock;
    }

    public BrokerageInputData(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public UserObject getUser() {
        return user;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public Stock getStock() {
        return stock;
    }
}
