package Brokerage;

import App.ControllerInterface;
import DataObjects.UserObject;
import com.crazzyghost.alphavantage.timeseries.response.StockUnit;

import java.util.List;

public class BrokerageController implements ControllerInterface {
    UserObject loggedInUser;
    private final BrokeragePresenter brokeragePresenter;
    private final BrokerageDBAccess brokerageDBAccess = new BrokerageDBAccess();

    public BrokerageController(UserObject loggedInUser) {
        this.loggedInUser = loggedInUser;
        this.brokeragePresenter = new BrokeragePresenter(this);
    }

    @Override
    public void launch(){
        brokeragePresenter.showView();
    }

    public boolean onSearchStockTriggered(String stockSymbol){
        return !stockSymbol.isEmpty();
    }

    public boolean isStockFound(String stockSymbol){
        List<StockUnit> stocks = fetchStockData(stockSymbol);
        return !stocks.isEmpty();
    }

    public List<StockUnit> fetchStockData(String stockSymbol) {
        return FetchStockDataUseCase.execute(stockSymbol);
    }

    public List<StockUnit> retrieveStocks(String stockSymbol){
        return fetchStockData(stockSymbol);
    }

    public double onSearchStockSuccess(String stockSymbol) {
        List<StockUnit> stocks = fetchStockData(stockSymbol);
        StockUnit latestStock = stocks.get(0);
        return latestStock.getClose();
    }

    public int getQuantity(String stockSymbol) {
        return brokerageDBAccess.getQuantity(loggedInUser.getUserID(), stockSymbol);
    }

    public boolean onBuyTriggered(int quantity, double price) {
        return quantity * price <= loggedInUser.getBalance();
    }

    public void addStock(String stockID, int quantity, double price) {
        StockObject boughtStock = new StockObject(stockID, price, quantity);
        loggedInUser = brokerageDBAccess.saveData(loggedInUser.getUserID(), boughtStock);
    }

    public boolean onSellTriggered(String stockID, int quantity) {
        return quantity <= brokerageDBAccess.getQuantity(loggedInUser.getUserID(), stockID);
    }

}