package seetransactions.interface_adapter;

import interface_adapter.ViewModel;

/**
 * The View Model for the SeeTransactions View.
 */
public class SeeTransactionsViewModel extends ViewModel<SeeTransactionsState> {
    public static final String TITLE_LABEL = "See Transactions View";

    public SeeTransactionsViewModel() {
        super("seetransactions");
        setState(new SeeTransactionsState());
    }
}