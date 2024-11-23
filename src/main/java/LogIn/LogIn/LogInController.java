package LogIn.LogIn;

import App.ControllerInterface;
import DataObjects.UserObject;
import DataObjects.UsersController;
import LogIn.LoggedIn.LoggedInController;
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
        return (user != null && user.getPasswordHash().equals(password));
    }

    public void onLoginSuccess(int userID) {
        UserObject user = usersController.getUser(userID);
        logInPresenter.disposeView();
        loggedInController = new LoggedInController(user);
        loggedInController.launch();
    }
}
