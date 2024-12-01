package insurance.newInsurance;

import App.ControllerInterface;
import DataObjects.UserObject;
import LogIn.LoggedIn.LoggedInController;
import lombok.Getter;

@Getter
@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class NewInsuranceController implements ControllerInterface {
    private final UserObject loggedInUser;
    private final NewInsurancePresenter newInsurancePresenter;

    public NewInsuranceController(UserObject user) {
        this.loggedInUser = user;
        this.newInsurancePresenter = new NewInsurancePresenter(this);
    }

    @Override
    public void launch() {
        newInsurancePresenter.showView();
    }

    public void goBackToBaseView() {
        newInsurancePresenter.disposeView();
        final LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }
}
