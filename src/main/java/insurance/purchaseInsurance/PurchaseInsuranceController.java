package insurance.purchaseInsurance;

import java.util.ArrayList;
import java.util.List;

import Card.CardController;
import DataObjects.UserObject;
import LogIn.LoggedIn.LoggedInController;
import LogIn.Welcome.WelcomeController;
import insurance.dataObject.InsuranceController;
import insurance.dataObject.InsuranceObject;
import insurance.dataObject.UserInsuranceController;

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
        this.availableInsurances = getAvailableInsurances(user.getUserID(), insuranceController.getAllInsurance());

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

    public List<InsuranceObject> getAvailableInsurances(int userID, List<InsuranceObject> insurances) {
        final List<InsuranceObject> newInsurances = new ArrayList<>();
        for (InsuranceObject insurance : insurances) {
            if (!userInsuranceController.isPurchased(userID, insurance)) {
                newInsurances.add(insurance);
            }
        }
        return newInsurances;
    }
}
