package aaaa.interface_adapter.login;

import aaaa.use_case.login.LoginOutputData;
import aaaa.view.LoginView;
import aaaa.view.ViewManager;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter {
    private final ViewManager viewManager;
    private final LoginView loginView;

    public LoginPresenter(ViewManager viewManager, LoginView loginView) {
        this.viewManager = viewManager;
        this.loginView = loginView;
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
