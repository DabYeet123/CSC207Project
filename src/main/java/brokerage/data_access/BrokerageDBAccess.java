package brokerage.data_access;

import java.nio.file.FileSystems;
import java.util.List;

import brokerage.entity.Stock;
import oldstuff.Views.dataaccess.DataAccessController;
import oldstuff.Views.dataaccess.DataAccessInterface;
import oldstuff.Views.userdataobject.UserObject;
import oldstuff.Views.userdataobject.UsersController;

/**
 * Provides data access functionality for brokerage operations, such as saving and retrieving
 * stock data, updating user balances, and managing stock history.
 */
public class BrokerageDBAccess implements DataAccessInterface<Stock> {

    private final String directory = "StockHistory.json";
    private final DataAccessController controller = new DataAccessController();
    private final UsersController usersController = new UsersController();

    /**
     * Saves stock data for a user. Updates the stock history file, adjusting quantity and price
     * if the stock already exists. Also updates the user's balance.
     *
     * @param userID the ID of the user whose data is being saved
     * @param stock the stock object to save
     * @return the updated {@code UserObject} after the balance has been modified
     */
    @Override
    public UserObject saveData(int userID, Stock stock) {
        final UserObject user = usersController.getUser(userID);
        final List<Stock> stocks = controller.readData(user.getFileDirectory()
                + FileSystems.getDefault().getSeparator() + directory, Stock.class);
        final boolean success = checkExistence(stock.getStockID(), stocks);
        if (success) {
            final List<Stock> newStocks = changeQuantityPrice(stock, stocks);
            controller.saveData(user.getFileDirectory()
                    + FileSystems.getDefault().getSeparator() + directory, newStocks, Stock.class);
        }
        else {
            stocks.add(stock);
            controller.saveData(user.getFileDirectory()
                    + FileSystems.getDefault().getSeparator() + directory, stocks, Stock.class);
        }
        return updateBalance(user, stock);
    }

    /**
     * Reads the stock data for a specific user from their stock history file.
     *
     * @param userID the ID of the user whose data is being read
     * @return a list of {@code StockObject} representing the user's stock data
     */
    @Override
    public List<Stock> readData(int userID) {
        final UserObject user = usersController.getUser(userID);
        return controller.readData(user.getFileDirectory()
                + FileSystems.getDefault().getSeparator() + directory, Stock.class);
    }

    /**
     * Checks whether a stock with a given ID exists in a list of stocks.
     *
     * @param stockID the stock ID to check
     * @param stocks the list of stocks to search
     * @return {@code true} if the stock exists, {@code false} otherwise
     */
    private boolean checkExistence(String stockID, List<Stock> stocks) {
        boolean success = false;
        for (Stock stock : stocks) {
            if (stock.getStockID().equals(stockID)) {
                success = true;
                break;
            }
        }
        return success;
    }

    /**
     * Updates the quantity and price of an existing stock in a list.
     *
     * @param newStock the stock with updated quantity and price
     * @param stocks the list of existing stocks
     * @return the updated list of stocks
     */
    private List<Stock> changeQuantityPrice(Stock newStock, List<Stock> stocks) {
        for (Stock stock : stocks) {
            if (stock.getStockID().equals(newStock.getStockID())) {
                final int oldQuantity = stock.getQuantity();
                final int deltaQuantity = newStock.getQuantity();
                stock.setQuantity(oldQuantity + deltaQuantity);
                stock.setPrice(newStock.getPrice());
            }
        }
        return stocks;
    }

    /**
     * Retrieves the quantity of a specific stock owned by a user.
     *
     * @param userID the ID of the user
     * @param stockID the ID of the stock to retrieve
     * @return the quantity of the stock owned by the user, or 0 if the stock does not exist
     */
    public int getQuantity(int userID, String stockID) {
        final UserObject user = usersController.getUser(userID);
        final List<Stock> stocks = controller.readData(user.getFileDirectory()
                + FileSystems.getDefault().getSeparator() + directory, Stock.class);
        final boolean success = checkExistence(stockID, stocks);
        int quantity = 0;
        if (success) {
            for (Stock stock : stocks) {
                if (stock.getStockID().equals(stockID)) {
                    quantity = stock.getQuantity();
                    break;
                }
            }
        }
        return quantity;
    }

    /**
     * Updates a user's balance after a stock transaction. Deducts the cost of the stock from the balance.
     *
     * @param user the user whose balance is being updated
     * @param stock the stock involved in the transaction
     * @return the updated {@code UserObject} with the new balance
     */
    private UserObject updateBalance(UserObject user, Stock stock) {
        user.setBalance(user.getBalance() - stock.getPrice() * stock.getQuantity());
        usersController.changeUser(user.getUserID(), user);
        return user;
    }
}
