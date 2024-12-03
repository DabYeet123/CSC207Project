package insurance.adapter;

import app.ControllerInterface;
import login.loggedin.LoggedInController;
import lombok.Getter;
import userdataobject.UserObject;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
@Getter
public class NewInsuranceController implements ControllerInterface {
    private final UserObject loggedInUser;
    private final NewInsurancePresenter newInsurancePresenter;

    /**
     * Constructs a new NewInsuranceController with the given user.
     *
     * @param user The logged-in user.
     */
    public NewInsuranceController(UserObject user) {
        this.loggedInUser = user;
        this.newInsurancePresenter = new NewInsurancePresenter(this);
    }

    /**
     * Launches the NewInsurance view.
     */
    @Override
    public void launch() {
        newInsurancePresenter.showView();
    }

    /**
     * Navigates back to the base view for the logged-in user.
     */
    public void goBackToBaseView() {
        newInsurancePresenter.disposeView();
        final LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }
}
