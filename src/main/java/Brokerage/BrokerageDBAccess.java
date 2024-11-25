package Brokerage;

import DataAccess.DataAccessController;
import DataAccess.DataAccessInterface;
import DataObjects.UserObject;
import DataObjects.UsersController;

import java.nio.file.FileSystems;
import java.util.List;

public class BrokerageDBAccess implements DataAccessInterface<StockObject> {
    DataAccessController controller = new DataAccessController();
    UsersController usersController = new UsersController();

    @Override
    public UserObject saveData(int userID, StockObject stock) {
        UserObject user = usersController.getUser(userID);
        List<StockObject> stocks = controller.readData(user.getFileDirectory() + FileSystems.getDefault().getSeparator() + "StockHistory.json", StockObject.class);
        boolean success = checkExistence(stock.getStockID(), stocks);
        if (success) {
            List<StockObject> newStocks = changeQuantityPrice(stock, stocks);
            controller.saveData(user.getFileDirectory() + FileSystems.getDefault().getSeparator()+ "StockHistory.json", newStocks, StockObject.class);
        } else {
            stocks.add(stock);
            controller.saveData(user.getFileDirectory() + FileSystems.getDefault().getSeparator() + "StockHistory.json", stocks, StockObject.class);
        }
        return updateBalance(user, stock);
    }

    @Override
    public List<StockObject> readData(int userID) {
        UserObject user = usersController.getUser(userID);
        return controller.readData(user.getFileDirectory() + FileSystems.getDefault().getSeparator() + "StockHistory.json", StockObject.class);
    }

    private boolean checkExistence(String stockID, List<StockObject> stocks){
        for(StockObject stock : stocks){
            if (stock.getStockID().equals(stockID)){
                return true;
            }
        }
        return false;
    }

    private List<StockObject> changeQuantityPrice(StockObject newStock, List<StockObject> stocks){
        for(StockObject stock : stocks){
            if (stock.getStockID().equals(newStock.getStockID())){
                int oldQuantity = stock.getQuantity();
                int deltaQuantity = newStock.getQuantity();
                stock.setQuantity(oldQuantity + deltaQuantity);
                stock.setPrice(newStock.getPrice());
            }
        }
        return stocks;
    }

    public int getQuantity(int userID, String stockID){
        UserObject user = usersController.getUser(userID);
        List<StockObject> stocks = controller.readData( user.getFileDirectory() + FileSystems.getDefault().getSeparator() + "StockHistory.json", StockObject.class);
        boolean success = checkExistence(stockID, stocks);
        if (success) {
            for(StockObject stock : stocks){
                if (stock.getStockID().equals(stockID)){
                    return stock.getQuantity();
                }
            }
        }
        return 0;
    }

    private UserObject updateBalance(UserObject user, StockObject stock) {
        user.setBalance(user.getBalance() - stock.getPrice()*stock.getQuantity());
        usersController.changeUser(user.getUserID(), user);
        return user;
    }
}
