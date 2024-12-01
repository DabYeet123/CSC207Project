package insurance.myInsurance;

import App.ControllerInterface;
import DataObjects.UserObject;
import insurance.dataObject.InsuranceObject;
import insurance.dataObject.UserInsuranceController;
import insurance.dataObject.UserInsuranceObject;
import LogIn.LoggedIn.LoggedInController;
import LogIn.Welcome.WelcomeController;
import lombok.Getter;

import java.util.List;

public class MyInsuranceController implements ControllerInterface {
    UserObject loggedInUser;
    @Getter
    List<UserInsuranceObject> insurances;
    private final MyInsurancePresenter myInsurancePresenter;
    private final WelcomeController welcomeController;
    private final UserInsuranceController userInsuranceController;

    public MyInsuranceController(UserObject user) {
        this.loggedInUser = user;
        this.userInsuranceController = new UserInsuranceController();
        this.insurances = userInsuranceController.getAllInsurance(loggedInUser.getUserID());
        this.welcomeController = new WelcomeController();
        this.myInsurancePresenter = new MyInsurancePresenter(this);
    }

    @Override
    public void launch() {
        myInsurancePresenter.showView();
    }

    public void logOutTriggered() {
        myInsurancePresenter.disposeView();
        welcomeController.launch();
    }

    public void goBackToBaseView() {
        myInsurancePresenter.disposeView();
        LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }

    public void update() {
        this.insurances = userInsuranceController.getAllInsurance(loggedInUser.getUserID());
    }

    public List<UserInsuranceObject> getInsurancesByType(String type) {
        return insurances.stream().filter(insurance -> insurance.getInsurance().getType().equals(type)).toList();
    }
}
