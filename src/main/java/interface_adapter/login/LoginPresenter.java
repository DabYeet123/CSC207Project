package interface_adapter.login;

import javax.swing.JPanel;

import use_case.login.LoginOutputData;
import view.LoginView;
import view.ViewManager;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter {
    private final ViewManager viewManager;
    private final LoginView loginView;

    public LoginPresenter(ViewManager viewManager, JPanel loginView) {
        this.viewManager = viewManager;
        this.loginView = (LoginView) loginView;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        viewManager.setState("");
    }

    @Override
    public void prepareFailView(String error) {
        loginView.displayMessage(error, false);
    }
}
