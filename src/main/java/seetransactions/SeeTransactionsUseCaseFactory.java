package seetransactions;

import interface_adapter.ViewManagerModel;
import loggedin.interface_adapter.LoggedinViewModel;
import seetransactions.interface_adapter.SeeTransactionsController;
import seetransactions.interface_adapter.SeeTransactionsPresenter;
import seetransactions.interface_adapter.SeeTransactionsViewModel;
import seetransactions.use_case.*;

/**
 * This class contains the static factory function for creating the SignupView.
 */
public final class SeeTransactionsUseCaseFactory {

    /** Prevent instantiation. */
    private SeeTransactionsUseCaseFactory() {

    }

    /**
     * Factory function for creating the LoginView.
     * @param viewManagerModel the ViewManagerModel to inject into the LoginView
     * @param loggedinViewModel the LoginViewModel to inject into the LoginView
     * @param seeTransactionsViewModel the LoginViewModel to inject into the LoginView
     * @param seeTransactionsDataAccessInterface the LoginUserDataAccessInterface to inject into the LoginView
     * @return makeTransactionView the LoginView created for the provided input classes
     */
    public static SeeTransactionsView create(ViewManagerModel viewManagerModel,
                                    LoggedinViewModel loggedinViewModel,
                                    SeeTransactionsViewModel seeTransactionsViewModel,
                                    SeeTransactionsDataAccessInterface seeTransactionsDataAccessInterface) {

        final SeeTransactionsController seeTransactionsController = createSeeTransactionsUseCase(viewManagerModel,
                loggedinViewModel, seeTransactionsViewModel, seeTransactionsDataAccessInterface);

        return new SeeTransactionsView(seeTransactionsController, seeTransactionsViewModel);
    }

    private static SeeTransactionsController createSeeTransactionsUseCase(
            ViewManagerModel viewManagerModel,
            LoggedinViewModel loggedinViewModel,
            SeeTransactionsViewModel seeTransactionsViewModel,
            SeeTransactionsDataAccessInterface seeTransactionsDataAccessInterface) {

        final SeeTransactionsOutputBoundary seeTransactionsOutputBoundary = new SeeTransactionsPresenter(
                seeTransactionsViewModel, loggedinViewModel, viewManagerModel);

        final SeeTransactionsInputBoundary seeTransactionsInteractor = new SeeTransactionsInteractor(
                seeTransactionsDataAccessInterface, seeTransactionsOutputBoundary);

        return new SeeTransactionsController(seeTransactionsInteractor);
    }
}
