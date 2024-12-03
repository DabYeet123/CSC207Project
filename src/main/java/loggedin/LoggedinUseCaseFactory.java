
package loggedin;

import interface_adapter.ViewManagerModel;
import loggedin.interface_adapter.LoggedinController;
import loggedin.interface_adapter.LoggedinPresenter;
import loggedin.interface_adapter.LoggedinViewModel;
import maketransaction.interface_adapter.MakeTransactionViewModel;
import seetransactions.interface_adapter.SeeTransactionsViewModel;
import welcome.interface_adapter.WelcomeViewModel;
import loggedin.use_case.LoggedinInputBoundary;
import loggedin.use_case.LoggedinInteractor;
import loggedin.use_case.LoggedinOutputBoundary;

/**
 * This class contains the static factory function for creating the SignupView.
 */
public final class LoggedinUseCaseFactory {
    /** Prevent instantiation. */
    private LoggedinUseCaseFactory() {

    }

    /**
     * Factory function for creating the WelcomeView.
     * @param viewManagerModel the ViewManagerModel to inject into the SignupView
     * @param makeTransactionViewModel the LoginViewModel to inject into the SignupView
     * @param seeTransactionsViewModel the LoginViewModel to inject into the SignupView
     * @param welcomeViewModel the LoginViewModel to inject into the SignupView
     * @param loggedinViewModel the LoginViewModel to inject into the SignupView
     * @return the WelcomeView created for the provided input classes
     */
    public static LoggedinView create(
            ViewManagerModel viewManagerModel, WelcomeViewModel welcomeViewModel,
            MakeTransactionViewModel makeTransactionViewModel, SeeTransactionsViewModel seeTransactionsViewModel,
            LoggedinViewModel loggedinViewModel) {

        final LoggedinController loggedinController = createLoggedinUseCase(viewManagerModel, makeTransactionViewModel,
                seeTransactionsViewModel, welcomeViewModel, loggedinViewModel);

        return new LoggedinView(loggedinController, loggedinViewModel);
    }

    private static LoggedinController createLoggedinUseCase(ViewManagerModel viewManagerModel,
                                                            MakeTransactionViewModel makeTransactionViewModel,
                                                            SeeTransactionsViewModel seeTransactionsViewModel,
                                                            WelcomeViewModel welcomeViewModel,
                                                            LoggedinViewModel loggedinViewModel) {
        final LoggedinOutputBoundary loggedinOutputBoundary = new LoggedinPresenter(viewManagerModel,
                makeTransactionViewModel, seeTransactionsViewModel, welcomeViewModel, loggedinViewModel);

        final LoggedinInputBoundary loggedinInputBoundary = new LoggedinInteractor(loggedinOutputBoundary);

        return new LoggedinController(loggedinInputBoundary);
    }

}
