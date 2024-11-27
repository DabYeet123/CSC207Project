package Card;

import DataAccess.DataAccessController;
import DataAccess.DataAccessInterface;
import DataObjects.UserObject;
import DataObjects.UsersController;

import java.util.List;
import java.util.Objects;

public class CardDBAccess implements DataAccessInterface<CardObject> {
    DataAccessController controller = new DataAccessController();
    UsersController usersController = new UsersController();


    @Override
    public UserObject saveData(int userID, CardObject card) {
        UserObject user = usersController.getUser(userID);
        List<CardObject> cards = controller.readData(user.getFileDirectory() + "\\CardInformation.json", CardObject.class);
        cards.add(card);
        controller.saveData(user.getFileDirectory() + "\\CardInformation.json", cards, CardObject.class);

        return user;
    }

    public void updateData(int userID, String cardID, double amount) {
        UserObject user = usersController.getUser(userID);
        List<CardObject> cards = controller.readData(user.getFileDirectory() + "\\CardInformation.json", CardObject.class);
        int index = readDataIndex(userID, cardID);
        CardObject card = readDataPoint(userID, cardID);
        card.updateAmount(amount);
        cards.set(index, card);
        controller.saveData(user.getFileDirectory() + "\\CardInformation.json", cards, CardObject.class);

    }



    public void saveDeleteData(int userID, int index) {
        UserObject user = usersController.getUser(userID);
        List<CardObject> cards = controller.readData(user.getFileDirectory() + "\\CardInformation.json", CardObject.class);
        cards.remove(index);
        controller.saveData(user.getFileDirectory() + "\\CardInformation.json", cards, CardObject.class);

    }

    @Override
    public List<CardObject> readData(int userID) {
        UserObject user = usersController.getUser(userID);
        return controller.readData(user.getFileDirectory() + "\\CardInformation.json", CardObject.class);
    }

    public CardObject readDataPoint(int userID, String cardID) {
        List<CardObject> cards = readData(userID);
        for (CardObject card : cards) {
            if (Objects.equals(card.getId(), cardID)) {
                return card;
            }
        }
        return null;
    }

    public int readDataIndex(int userID, String cardID) {
        List<CardObject> cards = readData(userID);
        for (int i = 0; i < cards.size(); ++i) {
            if (Objects.equals(cards.get(i).getId(), cardID)) {
                return i;
            }
        }
        return -1;
    }
}
