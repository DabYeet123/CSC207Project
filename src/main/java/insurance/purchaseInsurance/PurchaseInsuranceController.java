package insurance.purchaseInsurance;

import Card.CardController;
import DataObjects.UserObject;
import insurance.dataObject.InsuranceController;
import insurance.dataObject.InsuranceObject;
import LogIn.LoggedIn.LoggedInController;
import LogIn.Welcome.WelcomeController;
import insurance.dataObject.UserInsuranceController;

import java.util.List;

public class PurchaseInsuranceController {
    UserObject loggedInUser;
    private PurchaseInsurancePresenter purchaseInsurancePresenter;
    private WelcomeController welcomeController;
    private InsuranceController insuranceController;
    private UserInsuranceController userInsuranceController;
    private List<InsuranceObject> availableInsurances;

    public PurchaseInsuranceController(UserObject user) {
        this.loggedInUser = user;
        this.welcomeController = new WelcomeController();
        this.insuranceController = new InsuranceController();
        this.userInsuranceController = new UserInsuranceController();
        this.availableInsurances = insuranceController.getAllInsurance();

        this.purchaseInsurancePresenter = new PurchaseInsurancePresenter(this);
    }

    public void launch() {
        purchaseInsurancePresenter.showView();
    }

    public void logOutTriggered() {
        purchaseInsurancePresenter.disposeView();
        welcomeController.launch();
    }

    public void goBackToBaseView() {
        purchaseInsurancePresenter.disposeView();
        LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }

    public boolean purchaseInsuranceTriggered(int term, boolean autoRenew, String cardNumber) {
        CardController cardController = new CardController(loggedInUser);
        return (term > 0 && term < 100 || autoRenew) && cardController.getCard(cardNumber) != null;
    }

    public void onPurchaseInsuranceSuccess(InsuranceObject insurance, int term, boolean autoRenew, String cardNumber) {
        loggedInUser = userInsuranceController.addInsurance(loggedInUser.getUserID(), insurance, term, autoRenew, cardNumber);
        purchaseInsurancePresenter.disposeView();
        LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }

    public List<InsuranceObject> getInsurancesByType(String type) {
        return availableInsurances.stream().filter(insurance -> insurance.getType().equals(type)).toList();
    }
}
