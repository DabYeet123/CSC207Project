package brokerage.interface_adapter;

import java.util.List;

import brokerage.app.StockApi;
import brokerage.entity.Stock;
import brokerage.use_case.BrokerageInputData;
import com.crazzyghost.alphavantage.timeseries.response.StockUnit;
import login.loggedin.LoggedInController;
import userdataobject.UserObject;
import brokerage.use_case.BrokerageInputBoundary;

/**
 * Controls the brokerage functionality for the application, including handling stock searches,
 * buying and selling stocks, and managing user data.
 */
public class BrokerageController {
    private final BrokerageInputBoundary brokerageInteractor;

    public BrokerageController(BrokerageInputBoundary brokerageInteractor) {
        this.brokerageInteractor = brokerageInteractor;
    }

    public void searchStock(String stockSymbol) {
        final BrokerageInputData brokerageInputData = new BrokerageInputData(stockSymbol);
        brokerageInteractor.searchStock(brokerageInputData);
    }

    public void tradeStock(UserObject user, String stockSymbol, int quantity, Stock stock) {
        final BrokerageInputData brokerageInputData = new BrokerageInputData(user, stockSymbol, quantity, stock);
        brokerageInteractor.tradeStock(brokerageInputData);
    }

    public void switchToLoggedinView() {
        brokerageInteractor.switchToLoggedinView();
    }







    public boolean isStockFound(String stockSymbol) {
        final List<StockUnit> stocks = fetchStockData(stockSymbol);
        return !stocks.isEmpty();
    }

    /**
     * Fetches stock data for the given symbol using the relevant use case.
     *
     * @param stockSymbol the stock symbol to search
     * @return a list of {@code StockUnit} objects for the given symbol
     */

    public List<StockUnit> fetchStockData(String stockSymbol) {
        return StockApi.execute(stockSymbol);
    }

    /**
     * Retrieves stock data for a given symbol. Acts as an alias for {@code fetchStockData}.
     *
     * @param stockSymbol the stock symbol to search
     * @return a list of {@code StockUnit} objects for the given symbol
     */
    public List<StockUnit> retrieveStocks(String stockSymbol) {
        return fetchStockData(stockSymbol);
    }

    /**
     * Handles the success of a stock search by retrieving the latest closing price.
     *
     * @param stockSymbol the stock symbol that was searched
     * @return the latest closing price of the stock
     */
    public double onSearchStockSuccess(String stockSymbol) {
        final List<StockUnit> stocks = fetchStockData(stockSymbol);
        final StockUnit latestStock = stocks.get(0);
        return latestStock.getClose();
    }

    /**
     * Retrieves the quantity of a specific stock held by the logged-in user.
     *
     * @param stockSymbol the stock symbol to check
     * @return the quantity of the stock owned by the user
     */
    public int getQuantity(String stockSymbol) {
        return brokerageDBAccess.getQuantity(loggedInUser.getUserID(), stockSymbol);
    }

    /**
     * Checks if the user has sufficient balance to buy a stock at a given quantity and price.
     *
     * @param quantity the quantity of the stock to buy
     * @param price the price per stock unit
     * @return {@code true} if the user can afford the purchase, {@code false} otherwise
     */
    public boolean onBuyTriggered(int quantity, double price) {
        return quantity * price <= loggedInUser.getBalance();
    }

    /**
     * Adds a stock to the user's portfolio and updates the user's data.
     *
     * @param stockID the ID of the stock to add
     * @param quantity the quantity of the stock to add
     * @param price the price per stock unit
     */
    public void addStock(String stockID, int quantity, double price) {
        final Stock boughtStock = new Stock(stockID, price, quantity);
        loggedInUser = brokerageDBAccess.saveData(loggedInUser.getUserID(), boughtStock);
    }

    /**
     * Checks if the user has enough of a stock to sell a specified quantity.
     *
     * @param stockID the ID of the stock to sell
     * @param quantity the quantity of the stock to sell
     * @return {@code true} if the user has sufficient stock to sell, {@code false} otherwise
     */
    public boolean onSellTriggered(String stockID, int quantity) {
        return quantity <= brokerageDBAccess.getQuantity(loggedInUser.getUserID(), stockID);
    }

    /**
     * Navigates back to the base view by disposing of the current view and launching the main
     * controller for the logged-in user.
     */
    public void goBackToBaseView() {
        brokeragePresenter.disposeView();
        final LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }
}
