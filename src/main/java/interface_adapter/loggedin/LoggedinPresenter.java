package interface_adapter.loggedin;

import interface_adapter.ViewManagerModel;
import interface_adapter.maketransaction.MakeTransactionViewModel;
import interface_adapter.seetransactions.SeeTransactionsViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.loggedin.LoggedinOutputBoundary;

/**
 * The Presenter for the Welcome Use Case.
 */
public class LoggedinPresenter implements LoggedinOutputBoundary {
    private final LoggedinViewModel loggedinViewModel;
    private final ViewManagerModel viewManagerModel;
    private final MakeTransactionViewModel makeTransactionViewModel;
    private final SeeTransactionsViewModel seeTransactionsViewModel;
    private final WelcomeViewModel welcomeViewModel;

    public LoggedinPresenter(ViewManagerModel viewManagerModel, MakeTransactionViewModel makeTransactionViewModel,
                             SeeTransactionsViewModel seeTransactionsViewModel, WelcomeViewModel welcomeViewModel,
                             LoggedinViewModel loggedinViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.makeTransactionViewModel = makeTransactionViewModel;
        this.seeTransactionsViewModel = seeTransactionsViewModel;
        this.welcomeViewModel = welcomeViewModel;
        this.loggedinViewModel = loggedinViewModel;
    }

    @Override
    public void switchToMakeTransactionView() {
        // viewManagerModel.setState(makeTransactionViewModel.getViewName());
        // viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToSeeTransactionHistoryView() {
        // viewManagerModel.setState(seeTransactionsViewModel.getViewName());
        // viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToWelcomeView() {
        viewManagerModel.setState(welcomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
