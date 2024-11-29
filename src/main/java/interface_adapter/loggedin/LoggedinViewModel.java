package interface_adapter.loggedin;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Welcome View.
 */
public class LoggedinViewModel extends ViewModel<LoggedinState> {
    public static final String TITLE_LABEL = "Loggedin View";

    public LoggedinViewModel() {
        super("loggedin");
        setState(new LoggedinState());
    }
}
