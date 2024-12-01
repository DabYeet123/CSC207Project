package app;

import entity.TransactionFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin.LoggedinViewModel;
import interface_adapter.maketransaction.MakeTransactionController;
import interface_adapter.maketransaction.MakeTransactionPresenter;
import interface_adapter.maketransaction.MakeTransactionViewModel;
import use_case.maketransaction.MakeTransactionDataAccessInterface;
import use_case.maketransaction.MakeTransactionInputBoundary;
import use_case.maketransaction.MakeTransactionInteractor;
import use_case.maketransaction.MakeTransactionOutputBoundary;
import view.MakeTransactionView;

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
