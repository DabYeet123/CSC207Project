package insurance.adapter;

import java.util.List;

import app.ControllerInterface;
import card.adapter.CardController;
import card.dataObject.Card;
import insurance.dataObject.UserInsuranceObject;
import login.loggedin.LoggedInController;
import login.welcome.WelcomeController;
import lombok.Getter;
import userdataobject.UserObject;

@Getter
@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class MyInsuranceController implements ControllerInterface {
    private final UserObject loggedInUser;
    private List<UserInsuranceObject> insurances;
    private final MyInsurancePresenter myInsurancePresenter;
    private final WelcomeController welcomeController;
    private final UserInsuranceController userInsuranceController;

    /**
     * Constructs a new MyInsuranceController with the given user.
     *
     * @param user The logged-in user.
     */
    public MyInsuranceController(UserObject user) {
        this.loggedInUser = user;
        this.userInsuranceController = new UserInsuranceController();
        this.insurances = userInsuranceController.getAllInsurance(loggedInUser.getUserID());
        this.welcomeController = new WelcomeController();
        this.myInsurancePresenter = new MyInsurancePresenter(this);
    }

    /**
     * Launches the MyInsurance view.
     */
    @Override
    public void launch() {
        myInsurancePresenter.showView();
    }

    /**
     * Navigates back to the base view for the logged-in user.
     */
    public void goBackToBaseView() {
        myInsurancePresenter.disposeView();
        final LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }

    /**
     * Updates the list of insurance policies associated with the logged-in user.
     */
    public void update() {
        this.insurances = userInsuranceController.getAllInsurance(loggedInUser.getUserID());
    }

    /**
     * Retrieves all insurance policies of a specific type.
     *
     * @param type The type of insurance to filter by.
     * @return A list of insurance policies matching the specified type.
     */
    public List<UserInsuranceObject> getInsurancesByType(String type) {
        return insurances.stream().filter(insurance -> insurance.getInsurance().getType().equals(type)).toList();
    }

    public String showCardInformation(String cardUsed) {
        final CardController cardController = new CardController(loggedInUser);
        Card card = cardController.getCard(cardUsed);
        return card.getId() + " (" + card.getUsage() + ")";
    }
}
