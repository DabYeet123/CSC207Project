package data_access;

import java.nio.file.FileSystems;
import java.util.List;

import entity.Transaction;
import entity.User;
import maketransaction.use_case.MakeTransactionDataAccessInterface;

/**
 * This class provides methods for accessing and updating transaction data in the system. It is responsible
 * for saving transaction data to a file, reading transaction data from a file, and updating user balances
 * after a transaction is processed.
 */
public class DBTransactionDataAccessObject implements MakeTransactionDataAccessInterface {
    private static final String DIRECTORY = "TransactionHistory.json";
    private DBDataAccessObject controller = new DBDataAccessObject();
    private DBUserDataAccessObject usersController = new DBUserDataAccessObject();

    public DBTransactionDataAccessObject() {
    }

    public List<Transaction> readData(int userID) {
        final User user = usersController.readDataPoint(userID);
        return controller.readData(user.getFileDirectory() + FileSystems.getDefault().getSeparator()
                + DIRECTORY, Transaction.class);
    }

    @Override
    public User save(Transaction transaction) {
        User sender = usersController.readDataPoint(transaction.getSenderID());

        final List<Transaction> transactions = controller.readData(sender.getFileDirectory()
                + FileSystems.getDefault().getSeparator() + DIRECTORY, Transaction.class);
        transactions.add(transaction);
        controller.saveData(sender.getFileDirectory() + FileSystems.getDefault().getSeparator()
                + DIRECTORY, transactions, Transaction.class);

        sender.setBalance(sender.getBalance() - transaction.getAmount());
        sender = setCurrentUser(transaction.getSenderID(), sender);
        if (usersController.checkUserExistance(transaction.getReceiverID())) {
            final User receiver = usersController.readDataPoint(transaction.getSenderID());
            receiver.setBalance(receiver.getBalance() + transaction.getAmount());
            setCurrentUser(transaction.getReceiverID(), receiver);
        }
        return sender;
    }

    @Override
    public User setCurrentUser(int userId, User user) {
        usersController.updateDataPoint(userId, user);
        return user;
    }

    @Override
    public User getUser(int userId) {
        return usersController.readDataPoint(userId);
    }
}
