package Card;

import App.ControllerInterface;
import DataObjects.UserObject;
import LogIn.LoggedIn.LoggedInController;
import LogIn.Welcome.WelcomeController;

import java.util.ArrayList;
import java.util.List;

public class CardController implements ControllerInterface {
    public static List<CardObject> cardList = new ArrayList<>();
    static UserObject loggedInUser;
    private final CardPresenter cardPresenter;
    static CardDBAccess cardDBAccess = new CardDBAccess();

    public CardController(UserObject user) {
        loggedInUser = user;
        this.cardPresenter = new CardPresenter(this);
    }

    @Override
    public void launch() {
        cardPresenter.showView();
    }

    public void goBackToBaseView() {
        cardPresenter.disposeView();
        LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }

    public static void  loadFromFile() {
        cardList = cardDBAccess.readData(loggedInUser.getUserID());
    }

    public static void saveCards(CardObject card) {
        cardDBAccess.saveData(loggedInUser.getUserID(), card);
    }

    public static void saveDeleteCard(int index) {
        cardDBAccess.saveDeleteData(loggedInUser.getUserID(), index);
    }

    public CardObject getCard(String cardID) {
        return cardDBAccess.readDataPoint(loggedInUser.getUserID(), cardID);
    }

    public void updateData(int userID, String cardID, double amount) {
        cardDBAccess.updateData(userID, cardID, amount);
    }

}
