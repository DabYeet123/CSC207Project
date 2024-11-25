package LogIn.LogIn;

import App.ControllerInterface;
import DataObjects.UserObject;
import DataObjects.UsersController;
import LogIn.LoggedIn.LoggedInController;
import LogIn.Welcome.WelcomeController;
import LogIn.Welcome.WelcomePresenter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LogInController implements ControllerInterface {
    private LogInPresenter logInPresenter = new LogInPresenter(this);
    private LoggedInController loggedInController;
    UsersController usersController = new UsersController();

    @Override
    public void launch(){
        logInPresenter.showView();
    }

    public boolean logInTriggered(int userID, String password){
        UserObject user = usersController.getUser(userID);
        if (user != null && user.getPasswordHash().equals(password)){
            return true;
        }
        return false;
    }

    public void onLoginSuccess(int userID) {
        UserObject user = usersController.getUser(userID);
        logInPresenter.disposeView();
        loggedInController = new LoggedInController(user);
        loggedInController.launch();
    }

    public void goBackToWelcomeView() {
        logInPresenter.disposeView();
        WelcomeController controller = new WelcomeController();
        controller.launch();
    }
}
