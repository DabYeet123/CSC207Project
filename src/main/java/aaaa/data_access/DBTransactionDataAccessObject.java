package aaaa.data_access;

import java.nio.file.FileSystems;
import java.util.List;

import aaaa.entity.Transaction;
import aaaa.entity.User;

/**
 * This class provides methods for accessing and updating transaction data in the system. It is responsible
 * for saving transaction data to a file, reading transaction data from a file, and updating user balances
 * after a transaction is processed.
 */
public class DBTransactionDataAccessObject implements DBDataAccessInterface<Transaction> {
    private static final String DIRECTORY = "TransactionHistory.json";
    private DBDataAccessObject controller = new DBDataAccessObject();
    private DBUserDataAccessObject usersController = new DBUserDataAccessObject();

    @Override
    public User saveData(int userID, Transaction transaction) {
        final int senderID = userID;
        final int receiverID = transaction.getReceiverID();
        User sender = usersController.readDataPoint(senderID);
        final double amount = transaction.getAmount();

        final List<Transaction> transactions = controller.readData(sender.getFileDirectory()
                + FileSystems.getDefault().getSeparator() + DIRECTORY, Transaction.class);
        transactions.add(transaction);
        controller.saveData(sender.getFileDirectory() + FileSystems.getDefault().getSeparator()
                + DIRECTORY, transactions, Transaction.class);

        sender = updateSenderBalance(senderID, amount);
        updateReceiverBalance(receiverID, amount);

        return sender;
    }

    @Override
    public List<Transaction> readData(int userID) {
        final User user = usersController.readDataPoint(userID);
        return controller.readData(user.getFileDirectory() + FileSystems.getDefault().getSeparator()
                + DIRECTORY, Transaction.class);
    }

    private User updateSenderBalance(int userID, double amount) {
        final User user = usersController.readDataPoint(userID);
        user.setBalance(user.getBalance() - amount);
        usersController.updateDataPoint(user.getUserID(), user);
        return user;
    }

    private void updateReceiverBalance(int userID, double amount) {
        if (usersController.checkUserExistance(userID)) {
            final User user = usersController.readDataPoint(userID);
            user.setBalance(user.getBalance() + amount);
            usersController.updateDataPoint(user.getUserID(), user);
        }
    }
}
