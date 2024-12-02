package LogIn.Welcome;

import app.PresenterInterface;

public class WelcomePresenter implements PresenterInterface<WelcomeController> {
    private final WelcomeView welcomeView;

    public WelcomePresenter(WelcomeController controller) {
        this.welcomeView = new WelcomeView(controller);
    }

    @Override
    public void showView(){
        welcomeView.setVisible(true);
    }

    @Override
    public void disposeView(){
        welcomeView.setVisible(false);
        welcomeView.dispose();
    }
}