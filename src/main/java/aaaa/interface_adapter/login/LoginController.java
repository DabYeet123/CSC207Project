package aaaa.interface_adapter.login;

import aaaa.use_case.login.LoginInputData;
import aaaa.use_case.login.LoginUseCase;

/**
 * The controller for the Login Use Case.
 */
public class LoginController {
    private final LoginUseCase loginUseCase = new LoginUseCase();

    public LoginController() {
    }

    /**
     * Executes the Login Use Case.
     * @param userId the username of the user logging in
     * @param password the password of the user logging in
     */
    public void execute(int userId, String password) {
        final LoginInputData loginInputData = new LoginInputData(
                userId, password);

        loginUseCase.execute(loginInputData);
    }
}
