package loggedin.interface_adapter;

import entity.User;
import interface_adapter.ViewManagerModel;
import maketransaction.interface_adapter.MakeTransactionState;
import maketransaction.interface_adapter.MakeTransactionViewModel;
import seetransactions.interface_adapter.SeeTransactionsState;
import seetransactions.interface_adapter.SeeTransactionsViewModel;
import welcome.interface_adapter.WelcomeViewModel;
import loggedin.use_case.LoggedinOutputBoundary;

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
    public void switchToMakeTransactionView(User user) {

        final MakeTransactionState makeTransactionState = makeTransactionViewModel.getState();
        makeTransactionState.setUser(user);
        this.makeTransactionViewModel.setState(makeTransactionState);
        this.makeTransactionViewModel.firePropertyChanged();

        viewManagerModel.setState(makeTransactionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToSeeTransactionHistoryView(User user) {

        final SeeTransactionsState seeTransactionsState = seeTransactionsViewModel.getState();
        seeTransactionsState.setUser(user);
        this.seeTransactionsViewModel.setState(seeTransactionsState);
        this.seeTransactionsViewModel.firePropertyChanged();

        viewManagerModel.setState(seeTransactionsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToWelcomeView() {
        viewManagerModel.setState(welcomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
