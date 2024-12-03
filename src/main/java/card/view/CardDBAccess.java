package card.view;

import java.util.List;
import java.util.Objects;

import dataaccess.DataAccessController;
import dataaccess.DataAccessInterface;
import userdataobject.UserObject;
import userdataobject.UsersController;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class CardDBAccess implements DataAccessInterface<Card> {
    private static final String LOCATION = "\\CardInformation.json";
    private final DataAccessController controller = new DataAccessController();
    private final UsersController usersController = new UsersController();

    @Override
    public UserObject saveData(int userID, Card card) {
        final UserObject user = usersController.getUser(userID);
        final List<Card> cards = controller.readData(user.getFileDirectory() + LOCATION, Card.class);
        cards.add(card);
        controller.saveData(user.getFileDirectory() + LOCATION, cards, Card.class);

        return user;
    }

    @Override
    public List<Card> readData(int userID) {
        final UserObject user = usersController.getUser(userID);
        return controller.readData(user.getFileDirectory() + LOCATION, Card.class);
    }

    /**
     * Go back to base view.
     * @param userID given user ID
     * @param cardID given Card ID
     * @param amount given amount num
     */
    public void updateData(int userID, String cardID, double amount) {
        final UserObject user = usersController.getUser(userID);
        final List<Card> cards = controller.readData(user.getFileDirectory() + LOCATION, Card.class);
        final int index = readDataIndex(userID, cardID);
        final Card card = readDataPoint(userID, cardID);
        card.updateAmount(amount);
        cards.set(index, card);
        controller.saveData(user.getFileDirectory() + LOCATION, cards, Card.class);

    }

    /**
     * Save data to DAO.
     * @param userID given user ID
     * @param index given index
     */
    public void saveDeleteData(int userID, int index) {
        final UserObject user = usersController.getUser(userID);
        final List<Card> cards = controller.readData(user.getFileDirectory() + LOCATION, Card.class);
        cards.remove(index);
        controller.saveData(user.getFileDirectory() + LOCATION, cards, Card.class);

    }

    /**
     * Get the point card.
     * @param userID given user ID
     * @param cardID given index
     * @return The card needed
     */
    public Card readDataPoint(int userID, String cardID) {
        Card result = null;
        final List<Card> cards = readData(userID);
        for (Card card : cards) {
            if (Objects.equals(card.getId(), cardID)) {
                result = card;
                break;
            }
        }
        return result;
    }

    /**
     * Save data to DAO.
     * @param userID given user ID
     * @param cardID given CardId
     * @return index of int
     */
    public int readDataIndex(int userID, String cardID) {
        int result = -1;
        final List<Card> cards = readData(userID);
        for (int i = 0; i < cards.size(); ++i) {
            if (Objects.equals(cards.get(i).getId(), cardID)) {
                result = i;
                break;
            }
        }
        return result;
    }
}
