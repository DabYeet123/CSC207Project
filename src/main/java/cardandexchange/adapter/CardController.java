package cardandexchange.adapter;

import java.util.ArrayList;
import java.util.List;

import app.ControllerInterface;
import cardandexchange.dataAccess.CardDBAccess;
import cardandexchange.dataObject.Card;
import login.loggedin.LoggedInController;
import lombok.Getter;
import userdataobject.UserObject;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class CardController implements ControllerInterface {
    private static final CardDBAccess CARD_DB_ACCESS = new CardDBAccess();
    @Getter
    private static List<Card> cardList = new ArrayList<>();
    private static UserObject loggedInUser;
    private final CardPresenter cardPresenter;

    /**
     * CardController.
     * @param user input the user.
     */
    public CardController(UserObject user) {
        loggedInUser = user;
        this.cardPresenter = new CardPresenter(this);
    }

    public static void setCardList(List<Card> cardList) {
        CardController.cardList = cardList;
    }

    @Override
    public void launch() {
        cardPresenter.showView();
    }

    /**
     * Back to base view.
     */
    public void goBackToBaseView() {
        cardPresenter.disposeView();
        final LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }

    /**
     * Back to base view.
     */
    public static void loadFromFile() {
        setCardList(CARD_DB_ACCESS.readData(loggedInUser.getUserID()));
    }

    /**
     * Save card.
     * @param card offer card
     */
    public static void saveCards(Card card) {
        CARD_DB_ACCESS.saveData(loggedInUser.getUserID(), card);
    }

    /**
     * Delete card.
     * @param index given as index of card
     */
    public static void saveDeleteCard(int index) {
        CARD_DB_ACCESS.saveDeleteData(loggedInUser.getUserID(), index);
    }

    /**
     * Get the card from id.
     * @param cardID given id
     * @return Card
     */
    public Card getCard(String cardID) {
        return CARD_DB_ACCESS.readDataPoint(loggedInUser.getUserID(), cardID);
    }

    /**
     * UpdateData.
     * @param amount renew amount
     * @param cardID card id
     * @param userID user id
     */
    public void updateData(int userID, String cardID, double amount) {
        CARD_DB_ACCESS.updateData(userID, cardID, amount);
    }

}
