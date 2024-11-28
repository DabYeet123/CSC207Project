package aaaa.data_access;

import java.nio.file.FileSystems;
import java.util.List;

import dataaccess.DataAccessController;
import dataaccess.DataAccessInterface;
import transaction.dataObject.TransactionObject;
import userdataobject.UserObject;
import userdataobject.UsersController;

/**
 * This class provides methods for accessing and updating transaction data in the system. It is responsible
 * for saving transaction data to a file, reading transaction data from a file, and updating user balances
 * after a transaction is processed.
 */
public class DBTransactionDataAccessObject implements DataAccessInterface<TransactionObject> {
    private static final String DIRECTORY = "TransactionHistory.json";
    private DataAccessController controller = new DataAccessController();
    private UsersController usersController = new UsersController();

    @Override
    public UserObject saveData(int userID, TransactionObject transaction) {
        final int senderID = userID;
        final int receiverID = transaction.getReceiverID();
        UserObject sender = usersController.getUser(senderID);
        final double amount = transaction.getAmount();

        final List<TransactionObject> transactions = controller.readData(sender.getFileDirectory()
                + FileSystems.getDefault().getSeparator() + DIRECTORY, TransactionObject.class);
        transactions.add(transaction);
        controller.saveData(sender.getFileDirectory() + FileSystems.getDefault().getSeparator()
                + DIRECTORY, transactions, TransactionObject.class);

        sender = updateSenderBalance(senderID, amount);
        updateReceiverBalance(receiverID, amount);

        return sender;
    }

    @Override
    public List<TransactionObject> readData(int userID) {
        final UserObject user = usersController.getUser(userID);
        return controller.readData(user.getFileDirectory() + FileSystems.getDefault().getSeparator()
                + DIRECTORY, TransactionObject.class);
    }

    private UserObject updateSenderBalance(int userID, double amount) {
        final UserObject user = usersController.getUser(userID);
        user.setBalance(user.getBalance() - amount);
        usersController.changeUser(user.getUserID(), user);
        return user;
    }

    private void updateReceiverBalance(int userID, double amount) {
        if (usersController.checkUserExistance(userID)) {
            final UserObject user = usersController.getUser(userID);
            user.setBalance(user.getBalance() + amount);
            usersController.changeUser(user.getUserID(), user);
        }
    }
}
