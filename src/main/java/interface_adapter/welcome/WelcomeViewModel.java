package interface_adapter.welcome;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Welcome View.
 */
public class WelcomeViewModel extends ViewModel<WelcomeState> {
    public static final String TITLE_LABEL = "Welcome View";

    public WelcomeViewModel() {
        super("sign up");
        setState(new WelcomeState());
    }
}
