package maketransaction;

import entity.TransactionFactory;
import interface_adapter.ViewManagerModel;
import loggedin.interface_adapter.LoggedinViewModel;
import maketransaction.interface_adapter.MakeTransactionController;
import maketransaction.interface_adapter.MakeTransactionPresenter;
import maketransaction.interface_adapter.MakeTransactionViewModel;
import maketransaction.use_case.MakeTransactionDataAccessInterface;
import maketransaction.use_case.MakeTransactionInputBoundary;
import maketransaction.use_case.MakeTransactionInteractor;
import maketransaction.use_case.MakeTransactionOutputBoundary;

/**
 * This class contains the static factory function for creating the LoginView.
 */
public class MakeTransactionUseCaseFactory {

    /**
     * This class contains the static factory function for creating the LoginView.
     */
    public MakeTransactionUseCaseFactory() {
    }

    /**
     * Factory function for creating the LoginView.
     * @param viewManagerModel the ViewManagerModel to inject into the LoginView
     * @param loggedinViewModel the LoginViewModel to inject into the LoginView
     * @param makeTransactionViewModel the LoginViewModel to inject into the LoginView
     * @param makeTransactionDataAccessInterface the LoginUserDataAccessInterface to inject into the LoginView
     * @return makeTransactionView the LoginView created for the provided input classes
     */
    public static MakeTransactionView create(ViewManagerModel viewManagerModel,
                                   LoggedinViewModel loggedinViewModel,
                                   MakeTransactionViewModel makeTransactionViewModel,
                                   MakeTransactionDataAccessInterface makeTransactionDataAccessInterface) {

        final MakeTransactionController makeTransactionController = createMakeTransactionUseCase(viewManagerModel,
                loggedinViewModel, makeTransactionViewModel, makeTransactionDataAccessInterface);

        return new MakeTransactionView(makeTransactionController, makeTransactionViewModel);
    }

    private static MakeTransactionController createMakeTransactionUseCase(
            ViewManagerModel viewManagerModel,
            LoggedinViewModel loggedinViewModel,
            MakeTransactionViewModel makeTransactionViewModel,
            MakeTransactionDataAccessInterface makeTransactionDataAccessInterface) {

        final MakeTransactionOutputBoundary makeTransactionOutputBoundary = new MakeTransactionPresenter(
                makeTransactionViewModel, loggedinViewModel, viewManagerModel);

        final TransactionFactory transactionFactory = new TransactionFactory();

        final MakeTransactionInputBoundary makeTransactionInteractor = new MakeTransactionInteractor(
                makeTransactionDataAccessInterface, makeTransactionOutputBoundary, transactionFactory);

        return new MakeTransactionController(makeTransactionInteractor);
    }
}
