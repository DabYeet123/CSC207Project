package maketransaction.interface_adapter;

import interface_adapter.ViewModel;

/**
 * The View Model for the MakeTransaction View.
 */
public class MakeTransactionViewModel extends ViewModel<MakeTransactionState> {
    public static final String TITLE_LABEL = "Make Transaction View";

    public MakeTransactionViewModel() {
        super("maketransaction");
        setState(new MakeTransactionState());
    }

}