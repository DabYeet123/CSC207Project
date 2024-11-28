package Transaction.DataObject;

import DataAccess.DataAccessController;
import DataAccess.DataAccessInterface;
import DataObjects.UserObject;
import DataObjects.UsersController;

import java.nio.file.FileSystems;
import java.util.List;

public class TransactionDBAccess implements DataAccessInterface<TransactionObject> {
    DataAccessController controller = new DataAccessController();
    UsersController usersController = new UsersController();

    @Override
    public UserObject saveData(int userID, TransactionObject transaction) {
        int senderID = userID;
        int receiverID = transaction.receiverID;
        UserObject sender = usersController.getUser(senderID);
        UserObject receiver = usersController.getUser(receiverID);
        double amount = transaction.amount;

        List<TransactionObject> transactions = controller.readData(sender.getFileDirectory() + FileSystems.getDefault().getSeparator() + "TransactionHistory.json", TransactionObject.class);
        transactions.add(transaction);
        controller.saveData(sender.getFileDirectory() + FileSystems.getDefault().getSeparator() + "TransactionHistory.json", transactions, TransactionObject.class);

        sender = updateSenderBalance(sender, amount);
        receiver = updateReceiverBalance(receiver, amount);

        return sender;
    }

    @Override
    public List<TransactionObject> readData(int userID) {
        UserObject user = usersController.getUser(userID);
        return controller.readData(user.getFileDirectory() + FileSystems.getDefault().getSeparator() + "TransactionHistory.json", TransactionObject.class);
    }

    private UserObject updateSenderBalance(UserObject user, double amount){
        user.setBalance(user.getBalance() - amount);
        usersController.changeUser(user.getUserID(), user);
        return user;
    }

    private UserObject updateReceiverBalance(UserObject user, double amount){
        user.setBalance(user.getBalance() + amount);
        usersController.changeUser(user.getUserID(), user);
        return user;
    }
}
