package LogIn.Welcome;

import App.ControllerInterface;
import LogIn.LogIn.LogInController;
import LogIn.SignUp.SignUpController;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class WelcomeController implements ControllerInterface {
    private WelcomePresenter welcomePresenter = new WelcomePresenter(this);
    private LogInController logInController = new LogInController();
    private SignUpController signUpController = new SignUpController();

    @Override
    public void launch(){
        welcomePresenter.showView();
    }

    public void logInTriggered(){
        welcomePresenter.disposeView();
        logInController.launch();
    }

    public void signUpTriggered(){
        welcomePresenter.disposeView();
        signUpController.launch();
    }
}
