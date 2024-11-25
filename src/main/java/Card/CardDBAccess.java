package Card;

import DataAccess.DataAccessController;
import DataAccess.DataAccessInterface;
import DataObjects.UserObject;
import DataObjects.UsersController;

import java.util.List;
import java.util.Objects;

public class CardDBAccess implements DataAccessInterface<Card> {
    DataAccessController controller = new DataAccessController();
    UsersController usersController = new UsersController();


    @Override
    public UserObject saveData(int userID, Card card) {
        UserObject user = usersController.getUser(userID);
        List<Card> cards = controller.readData(user.getFileDirectory() + "\\CardInformation.json", Card.class);
        cards.add(card);
        controller.saveData(user.getFileDirectory() + "\\CardInformation.json", cards, Card.class);

        return user;
    }

    public UserObject saveDeleteData(int userID, int index) {
        UserObject user = usersController.getUser(userID);
        List<Card> cards = controller.readData(user.getFileDirectory() + "\\CardInformation.json", Card.class);
        cards.remove(index);
        controller.saveData(user.getFileDirectory() + "\\CardInformation.json", cards, Card.class);

        return user;
    }

    @Override
    public List<Card> readData(int userID) {
        UserObject user = usersController.getUser(userID);
        List<Card> cards = controller.readData(user.getFileDirectory() + "\\CardInformation.json", Card.class);
        return cards;
    }

    public Card readDataPoint(int userID, String cardID) {
        List<Card> cards = readData(userID);
        for (Card card : cards) {
            if (card != null && Objects.equals(card.getId(), cardID)) {
                return card;
            }
        }
        return null;
    }
}
