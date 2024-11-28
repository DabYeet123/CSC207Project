package aaaa.use_case.login;

import aaaa.data_access.DBUserDataAccessObject;
import aaaa.entity.User;
import aaaa.interface_adapter.login.LoginPresenter;

/**
 * View class for handling the login interface.
 */
public class LoginUseCase {

    private final LoginPresenter loginPresenter = new LoginPresenter();
    private final DBUserDataAccessObject dbUserDataAccessObject = new DBUserDataAccessObject();

    public LoginUseCase() {
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
