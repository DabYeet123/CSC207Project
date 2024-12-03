package brokerage.interface_adapter;

import org.jfree.chart.plot.XYPlot;
import oldstuff.Views.userdataobject.UserObject;

public class BrokerageState {
    private UserObject user;
    private String stockSymbol;
    private int quantity;
    private XYPlot graph;

    public UserObject getUser() {
        return user;
    }

    public void setUser(UserObject user) {
        this.user = user;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public XYPlot getGraph() {
        return graph;
    }

    public void setGraph(XYPlot graph) {
        this.graph = graph;
    }

    @Override
    public String toString() {
        return "BrokerageState{"
                + "user=" + user + ", stockSymbol='" + stockSymbol + '\'' + ", quantity=" + quantity
                + ", graph=" + graph + '}';
    }
}
