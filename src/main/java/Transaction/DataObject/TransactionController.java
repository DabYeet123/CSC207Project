package Transaction.DataObject;

import DataObjects.UserObject;

import java.util.List;

public class TransactionController {
    TransactionDBAccess transactionDBAccess = new TransactionDBAccess();
    NewTransactionController newTransactionController = new NewTransactionController();

    public UserObject addTransaction(int senderID, int receiverID, String cardUsed, double amount) {
        TransactionObject newTransaction = newTransactionController.createNewTransaction(senderID, receiverID, cardUsed, amount);
        UserObject user = transactionDBAccess.saveData(senderID, newTransaction);

        return user;
    }

    public List<TransactionObject> getAllTransactions(int userID) {
        return transactionDBAccess.readData(userID);
    }
}
