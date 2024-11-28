package login.loggedin;

import app.PresenterInterface;

/**
 * Presenter responsible for managing the logged-in view's visibility and interaction logic.
 */
public class LoggedInPresenter implements PresenterInterface<LoggedInController> {
    private final LoggedInView loggedInView;
    private final LoggedInViewModel viewModel;

    public LoggedInPresenter(LoggedInController controller, LoggedInUseCase loggedInUseCase) {
        this.viewModel = loggedInUseCase.getViewModel();
        this.loggedInView = new LoggedInView(controller);
    }

    @Override
    public void showView() {
        loggedInView.updateView(viewModel);
        loggedInView.setVisible(true);
    }

    @Override
    public void disposeView() {
        loggedInView.setVisible(false);
        loggedInView.dispose();
    }
}
