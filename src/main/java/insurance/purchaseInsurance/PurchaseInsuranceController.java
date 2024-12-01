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
import insurance.newInsurance.NewInsuranceController;
import insurance.newInsurance.NewInsurancePresenter;
import lombok.Getter;

@Getter
@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class PurchaseInsuranceController {

    private static final int MAX_TERM = 100;
    private UserObject loggedInUser;
    private final PurchaseInsurancePresenter purchaseInsurancePresenter;
    private final NewInsurancePresenter newInsurancePresenter;
    private final WelcomeController welcomeController;
    private final UserInsuranceController userInsuranceController;
    private final List<InsuranceObject> availableInsurances;

    public PurchaseInsuranceController(UserObject user) {
        this.loggedInUser = user;
        this.welcomeController = new WelcomeController();
        this.userInsuranceController = new UserInsuranceController();
        final InsuranceController insuranceController = new InsuranceController();
        this.availableInsurances = getAvailableInsurances(user.getUserID(), insuranceController.getAllInsurance());

        this.purchaseInsurancePresenter = new PurchaseInsurancePresenter(this);
        final NewInsuranceController newInsuranceController = new NewInsuranceController(user);
        this.newInsurancePresenter = new NewInsurancePresenter(newInsuranceController);
    }

    public void launch() {
        purchaseInsurancePresenter.showView();
    }

    public void addNewInsuranceTriggered() {
        purchaseInsurancePresenter.disposeView();
        newInsurancePresenter.showView();
    }

    public void goBackToBaseView() {
        purchaseInsurancePresenter.disposeView();
        final LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }

    public boolean purchaseInsuranceTriggered(int term, boolean autoRenew, String cardNumber) {
        final CardController cardController = new CardController(loggedInUser);
        return (term > 0 && term < MAX_TERM || autoRenew) && cardController.getCard(cardNumber) != null;
    }

    public void onPurchaseInsuranceSuccess(InsuranceObject insurance, int term, boolean autoRenew, String cardNumber) {
        loggedInUser = userInsuranceController.addInsurance(loggedInUser.getUserID(),
                insurance, term, autoRenew, cardNumber);
        purchaseInsurancePresenter.disposeView();
        final LoggedInController controller = new LoggedInController(loggedInUser);
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
