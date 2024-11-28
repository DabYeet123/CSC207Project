package Insurance.MyInsurance;

import App.ControllerInterface;
import DataObjects.UserObject;
import Insurance.DataObject.InsuranceController;
import Insurance.DataObject.InsuranceObject;
import LogIn.LoggedIn.LoggedInController;
import LogIn.Welcome.WelcomeController;

import java.util.List;

public class MyInsuranceController implements ControllerInterface {
    UserObject loggedInUser;
    List<InsuranceObject> insurances;
    private MyInsurancePresenter myInsurancePresenter;
    private WelcomeController welcomeController;
    private InsuranceController insuranceController;

    public MyInsuranceController(UserObject user) {
        this.loggedInUser = user;
        this.insuranceController = new InsuranceController();
        this.insurances = insuranceController.getAllInsurance(loggedInUser.getUserID());
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
        this.insurances = insuranceController.getAllInsurance(loggedInUser.getUserID());
    }

    public List<InsuranceObject> getInsurancesByType(String type) {
        return insurances.stream().filter(insurance -> insurance.getType().equals(type)).toList();
    }
}
