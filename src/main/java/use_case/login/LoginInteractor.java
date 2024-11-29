package use_case.login;

import entity.User;

/**
 * View class for handling the login interface.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    /**
     * Executes the login process using the provided input data.
     *
     * @param loginInputData the input data containing the user's ID and password
     */
    public void execute(LoginInputData loginInputData) {
        final int userID = loginInputData.getUserId();
        final String password = loginInputData.getPassword();

        final boolean correspondsToUser = userDataAccessObject.correspondsToUser(userID, password);
        if (correspondsToUser) {
            final User user = userDataAccessObject.get(loginInputData.getUserId());

            final LoginOutputData loginOutputData = new LoginOutputData(user);
            loginPresenter.prepareSuccessView(loginOutputData);
        }
        else {
            loginPresenter.prepareFailView(" Incorrect userId or Password. ");
        }

    }

}
