package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin.LoggedinController;
import interface_adapter.loggedin.LoggedinPresenter;
import interface_adapter.loggedin.LoggedinViewModel;
import interface_adapter.maketransaction.MakeTransactionViewModel;
import interface_adapter.seetransactions.SeeTransactionsViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.loggedin.LoggedinInputBoundary;
import use_case.loggedin.LoggedinInteractor;
import use_case.loggedin.LoggedinOutputBoundary;
import view.LoggedinView;

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
