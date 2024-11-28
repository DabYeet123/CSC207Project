package Insurance.PurchaseInsurance;

import DataObjects.UserObject;
import Insurance.DataObject.InsuranceController;
import Insurance.DataObject.InsuranceObject;
import LogIn.LoggedIn.LoggedInController;
import LogIn.Welcome.WelcomeController;

import java.util.List;

public class PurchaseInsuranceController {
    UserObject loggedInUser;
    private PurchaseInsurancePresenter purchaseInsurancePresenter;
    private WelcomeController welcomeController;
    private InsuranceController insuranceController;
    private List<InsuranceObject> availableInsurances;

    public PurchaseInsuranceController(UserObject user) {
        this.loggedInUser = user;
        this.insuranceController = new InsuranceController();
        this.availableInsurances = insuranceController.getAllInsurance(user.getUserID());
        this.welcomeController = new WelcomeController();
        this.purchaseInsurancePresenter = new PurchaseInsurancePresenter(this);
    }

    public void launch() {
        purchaseInsurancePresenter.showView();
    }

    public void logOutTriggered() {
        purchaseInsurancePresenter.disposeView();
        welcomeController.launch();
    }

    public boolean applyInsuranceTriggered(String type, double premium, int term, boolean autoRenew) {
        return type != null && !type.isEmpty() && premium > 0 && (term > 0 || autoRenew);
    }

    public void onApplyInsuranceSuccess(String type, double premium, int term, boolean autoRenew) {
        loggedInUser = insuranceController.addInsurance(loggedInUser.getUserID(), type, premium, term, autoRenew);
        purchaseInsurancePresenter.disposeView();
        LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }

    public List<InsuranceObject> getInsurancesByType(String type) {
        return availableInsurances.stream().filter(insurance -> insurance.getType().equals(type)).toList();
    }
}
