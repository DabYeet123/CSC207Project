package login.login;

import app.PresenterInterface;

/**
 * Presenter class for managing the login view.
 */
public class LogInPresenter implements PresenterInterface<LogInController> {
    private final LogInView logInView;

    public LogInPresenter(LogInController controller) {
        this.logInView = new LogInView(controller);
    }

    @Override
    public void showView() {
        logInView.setVisible(true);
    }

    @Override
    public void disposeView() {
        logInView.setVisible(false);
        logInView.dispose();
    }
}
