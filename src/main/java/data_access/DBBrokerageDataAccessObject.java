package data_access;

import java.nio.file.FileSystems;
import java.util.List;

import brokerage.app.StockApi;
import brokerage.entity.Stock;
import brokerage.use_case.BrokerageDataAccessInterface;
import com.crazzyghost.alphavantage.timeseries.response.StockUnit;
import entity.Transaction;
import entity.TransactionFactory;
import entity.User;

/**
 * This class provides methods for accessing and updating transaction data in the system. It is responsible
 * for saving transaction data to a file, reading transaction data from a file, and updating user balances
 * after a transaction is processed.
 */
public class DBBrokerageDataAccessObject implements BrokerageDataAccessInterface {
    private static final String DIRECTORY = "BrokerageHistory.json";
    private DBDataAccessObject controller = new DBDataAccessObject();
    private DBUserDataAccessObject usersController = new DBUserDataAccessObject();
    private DBTransactionDataAccessObject transactionController = new DBTransactionDataAccessObject();
    private TransactionFactory transactionFactory = new TransactionFactory();

    public DBBrokerageDataAccessObject() {
    }

    /**
     * Reads datapoints.
     * @param userID identifier
     * @return list
     */
    public List<Stock> readData(int userID) {
        final User user = usersController.readDataPoint(userID);
        return controller.readData(user.getFileDirectory() + FileSystems.getDefault().getSeparator()
                + DIRECTORY, Stock.class);
    }

    @Override
    public int getQuantity(int userID, int stockID) {
        int quantity = 0;
        final List<Stock> stocks = readData(userID);
        for (Stock stock : stocks) {
            if (stock.getStockID().equals(stockID)) {
                quantity = stock.getQuantity();
                break;
            }
        }
        return quantity;
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
     * Checks if the user already has the stock.
     * @param stock stock
     * @param stocks list of stocks
     * @return if found
     */
    public boolean doesUserHaveStock(Stock stock, List<Stock> stocks) {
        boolean success = false;
        for (Stock stock2 : stocks) {
            if (stock.getStockID().equals(stock2.getStockID())) {
                success = true;
                break;
            }
        }
        return success;
    }

    @Override
    public void saveTrade(int userID, Stock newStock) {
        final User user = usersController.readDataPoint(userID);

        final List<Stock> stocks = controller.readData(user.getFileDirectory()
                + FileSystems.getDefault().getSeparator() + DIRECTORY, Stock.class);

        if (doesUserHaveStock(newStock, stocks)) {
            for (Stock stock : stocks) {
                if (stock.getStockID().equals(newStock.getStockID())) {
                    stock.setQuantity(stock.getQuantity() + newStock.getQuantity());
                }
            }
        }
        else {
            stocks.add(newStock);
        }

        controller.saveData(user.getFileDirectory() + FileSystems.getDefault().getSeparator()
                + DIRECTORY, stocks, Stock.class);

        final Transaction transaction = transactionFactory.create(user.getUserID(), 99999, "Stocks",
                newStock.getPrice() * newStock.getQuantity());
        transactionController.save(transaction);
    }

    /**
     * Checks if the stock symbol is found.
     * @param stockSymbol symbol
     * @return if stock is found
     */
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

}
