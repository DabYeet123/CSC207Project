package oldstuff.Views.login.welcome;

import oldstuff.Views.appold.PresenterInterface;

/**
 * Presenter for the Welcome view. This class is responsible for managing the interaction
 * between the Welcome view and the WelcomeController, handling actions to display or dispose
 * of the view.
 */
public class WelcomePresenter implements PresenterInterface<WelcomeController> {
    private final WelcomeView welcomeView;

    public WelcomePresenter(WelcomeController controller) {
        this.welcomeView = new WelcomeView(controller);
    }

    @Override
    public void showView() {
        welcomeView.setVisible(true);
    }

    @Override
    public void disposeView() {
        welcomeView.setVisible(false);
        welcomeView.dispose();
    }
}
