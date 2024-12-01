package use_case.maketransaction;

import entity.Transaction;
import entity.TransactionFactory;
import entity.User;

/**
 * View class for handling the maketransaction interface.
 */
public class MakeTransactionInteractor implements MakeTransactionInputBoundary {
    private final MakeTransactionDataAccessInterface makeTransactionDataAccessInterface;
    private final MakeTransactionOutputBoundary makeTransactionOutputBoundary;
    private final TransactionFactory transactionFactory;

    public MakeTransactionInteractor(MakeTransactionDataAccessInterface makeTransactionDataAccessInterface,
                                     MakeTransactionOutputBoundary makeTransactionOutputBoundary,
                                     TransactionFactory transactionFactory) {
        this.makeTransactionDataAccessInterface = makeTransactionDataAccessInterface;
        this.makeTransactionOutputBoundary = makeTransactionOutputBoundary;
        this.transactionFactory = transactionFactory;
    }

    @Override
    public void execute(MakeTransactionInputData makeTransactionInputData) {
        User user = makeTransactionInputData.getUser();
        final int receiverId = makeTransactionInputData.getReceiverId();
        final String card = makeTransactionInputData.getCard();
        final double amount = makeTransactionInputData.getAmount();

        final User receiver = makeTransactionDataAccessInterface.getUser(receiverId);
        final boolean enoughBalance = user.getBalance() >= amount;
        if (enoughBalance) {
            final Transaction transaction = transactionFactory.create(user.getUserID(), receiver.getUserID(), card, amount);
            user = makeTransactionDataAccessInterface.save(transaction);

            final MakeTransactionOutputData makeTransactionOutputData = new MakeTransactionOutputData(user);
            makeTransactionOutputBoundary.prepareSuccessView(makeTransactionOutputData);
        }
        else {
            makeTransactionOutputBoundary.prepareFailView(" Not enough balance. ");
        }

    }

    @Override
    public void switchToLoggedinView() {
        makeTransactionOutputBoundary.switchToLoggedinView();
    }
}
