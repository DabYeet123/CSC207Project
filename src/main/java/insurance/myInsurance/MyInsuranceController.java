package insurance.myInsurance;

import java.util.List;

import App.ControllerInterface;
import DataObjects.UserObject;
import LogIn.LoggedIn.LoggedInController;
import LogIn.Welcome.WelcomeController;
import insurance.dataObject.UserInsuranceController;
import insurance.dataObject.UserInsuranceObject;
import lombok.Getter;

@Getter
@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class MyInsuranceController implements ControllerInterface {
    private final UserObject loggedInUser;
    private List<UserInsuranceObject> insurances;
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
        final LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }

    public void update() {
        this.insurances = userInsuranceController.getAllInsurance(loggedInUser.getUserID());
    }

    public List<UserInsuranceObject> getInsurancesByType(String type) {
        return insurances.stream().filter(insurance -> insurance.getInsurance().getType().equals(type)).toList();
    }
}
