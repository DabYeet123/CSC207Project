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
import lombok.Getter;

@Getter
@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class PurchaseInsuranceController {

    private static final int MAX_TERM = 100;
    private UserObject loggedInUser;
    private final PurchaseInsurancePresenter purchaseInsurancePresenter;
    private NewInsuranceController newInsuranceController;
    private final WelcomeController welcomeController;
    private final UserInsuranceController userInsuranceController;
    private final List<InsuranceObject> availableInsurances;

    /**
     * Constructs a new PurchaseInsuranceController with the given user.
     *
     * @param user The logged-in user.
     */
    public PurchaseInsuranceController(UserObject user) {
        this.loggedInUser = user;
        this.welcomeController = new WelcomeController();
        this.userInsuranceController = new UserInsuranceController();
        final InsuranceController insuranceController = new InsuranceController();
        this.availableInsurances = getAvailableInsurances(user.getUserID(), insuranceController.getAllInsurance());
        this.purchaseInsurancePresenter = new PurchaseInsurancePresenter(this);
    }

    /**
     * Launches the PurchaseInsurance view.
     */
    public void launch() {
        purchaseInsurancePresenter.showView();
    }

    /**
     * Triggers the action to add a new insurance policy.
     */
    public void addNewInsuranceTriggered() {
        purchaseInsurancePresenter.disposeView();
        newInsuranceController = new NewInsuranceController(loggedInUser);
        newInsuranceController.launch();
    }

    /**
     * Navigates back to the base view for the logged-in user.
     */
    public void goBackToBaseView() {
        purchaseInsurancePresenter.disposeView();
        final LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }

    /**
     * Handles the action of purchasing insurance.
     *
     * @param term       The term of the insurance in years.
     * @param autoRenew  Whether the insurance should auto-renew.
     * @param cardNumber The card number used for the purchase.
     * @return True if the insurance can be purchased; false otherwise.
     */
    public boolean purchaseInsuranceTriggered(int term, boolean autoRenew, String cardNumber) {
        final CardController cardController = new CardController(loggedInUser);
        return (term > 0 && term < MAX_TERM || autoRenew) && cardController.getCard(cardNumber) != null;
    }

    /**
     * Completes the purchase of insurance.
     *
     * @param insurance  The insurance object to be purchased.
     * @param term       The term of the insurance in years.
     * @param autoRenew  Whether the insurance should auto-renew.
     * @param cardNumber The card number used for the purchase.
     */
    public void onPurchaseInsuranceSuccess(InsuranceObject insurance, int term, boolean autoRenew, String cardNumber) {
        loggedInUser = userInsuranceController.addInsurance(loggedInUser.getUserID(),
                insurance, term, autoRenew, cardNumber);
        purchaseInsurancePresenter.disposeView();
        final LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }

    /**
     * Retrieves all available insurance policies of a specific type.
     *
     * @param type The type of insurance to filter by.
     * @return A list of insurance policies matching the specified type.
     */
    public List<InsuranceObject> getInsurancesByType(String type) {
        return availableInsurances.stream().filter(insurance -> insurance.getType().equals(type)).toList();
    }

    /**
     * Retrieves a list of insurances available for purchase by the user.
     *
     * @param userID     The ID of the user.
     * @param insurances A list of all insurance policies.
     * @return A list of insurance policies that the user has not yet purchased.
     */
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
