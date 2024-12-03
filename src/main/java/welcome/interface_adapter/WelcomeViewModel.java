package welcome.interface_adapter;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Welcome View.
 */
public class WelcomeViewModel extends ViewModel<WelcomeState> {
    public static final String TITLE_LABEL = "Welcome View";

    public WelcomeViewModel() {
        super("welcome");
        setState(new WelcomeState());
    }
}