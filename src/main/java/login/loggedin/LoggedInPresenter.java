package LogIn.LoggedIn;

import app.PresenterInterface;

public class LoggedInPresenter implements PresenterInterface<LoggedInController> {
    private final LoggedInView loggedInView;

    public LoggedInPresenter(LoggedInController controller){
        this.loggedInView = new LoggedInView(controller);
    }

    @Override
    public void showView(){
        loggedInView.setVisible(true);
    }

    @Override
    public void disposeView(){
        loggedInView.setVisible(false);
        loggedInView.dispose();
    }
}
