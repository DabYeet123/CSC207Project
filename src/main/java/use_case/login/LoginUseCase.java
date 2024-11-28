package use_case.login;

import data_access.DBUserDataAccessObject;
import entity.User;
import interface_adapter.login.LoginPresenter;
import view.ViewManager;

/**
 * View class for handling the login interface.
 */
public class LoginUseCase {

    private final ViewManager viewManager;
    private final LoginPresenter loginPresenter;
    private final DBUserDataAccessObject dbUserDataAccessObject = new DBUserDataAccessObject();

    public LoginUseCase(ViewManager viewManager) {
        this.viewManager = viewManager;
        this.loginPresenter = viewManager.getLoginPresenter();
    }

    /**
     * Executes the login process using the provided input data.
     *
     * @param loginInputData the input data containing the user's ID and password
     */
    public void execute(LoginInputData loginInputData) {
        final int userID = loginInputData.getUserId();
        final String password = loginInputData.getPassword();

        final boolean correspondsToUser = dbUserDataAccessObject.correspondsToUser(userID, password);
        if (correspondsToUser) {
            final User user = dbUserDataAccessObject.readDataPoint(loginInputData.getUserId());

            final LoginOutputData loginOutputData = new LoginOutputData(user);
            loginPresenter.prepareSuccessView(loginOutputData);
        }
        else {
            loginPresenter.prepareFailView(" Incorrect userId or Password. ");
        }

    }

}
