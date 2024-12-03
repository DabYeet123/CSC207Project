package login.use_case;

import entity.User;

/**
 * View class for handling the login interface.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final LoginOutputBoundary loginOutputBoundary;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginOutputBoundary = loginOutputBoundary;
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
            loginOutputBoundary.prepareSuccessView(loginOutputData);
        }
        else {
            loginOutputBoundary.prepareFailView(" Incorrect userId or Password. ");
        }
    }

    @Override
    public void switchToWelcomeView() {
        loginOutputBoundary.switchToWelcomeView();
    }
}