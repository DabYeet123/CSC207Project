package Card;

import Card.CardController;
import Card.CardDBAccess;
import DataObjects.UserObject;
import DataObjects.UsersDBAccess;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    UserObject userObject = new UserObject(11000, "Yue", "Zheng", "12", 0.0, "file");
    UsersDBAccess usersDBAccess = new UsersDBAccess();
    CardController cardController = new CardController(userObject);
    /*
        The UserId here is all 11000 because save data in 11000 user and in each user we can test the card part
     */

    @Test
    void successCardExistsTestWithChangeExpenses() {

        usersDBAccess.saveData(userObject);

        int userId = 11000;
        String cardId = "0010011111";
        Card mockCard = new Card(cardId, "Test Card", "12/2025", "123");

        CardDBAccess cardDBAccess = new CardDBAccess();
        cardDBAccess.saveData(userId, mockCard);

        CardController controller = new CardController(userObject);
        Card retrievedCard = controller.getCard(cardId);

        assertNotNull(retrievedCard);
        assertEquals(cardId, retrievedCard.getId());
        assertEquals("Test Card", retrievedCard.getUsage());
        assertEquals(0, retrievedCard.getExpenses());
        retrievedCard.updateAmount(1000);
        assertEquals(-1000.0, retrievedCard.getExpenses());
    }

    @Test
    void failureCardDoesNotExistTest() {
        String fakeCardId = "1000000000";

        CardController controller = new CardController(userObject);
        Card retrievedCard = controller.getCard(fakeCardId);

        assertNull(retrievedCard, "Expected no card to be returned.");
    }

    @Test
    void successDeleteCardTest() {
        int userId = 11000;
        String cardId = cardController.newId("11");
        Card testCard = new Card(cardId, "Test Card", "12/2025", "123");

        CardDBAccess cardDBAccess = new CardDBAccess();
        cardDBAccess.saveData(userId, testCard);
        assertEquals(cardId, cardDBAccess.readDataPoint(userId,cardId).getId());

        int index = cardDBAccess.readDataIndex(userId, cardId);
        cardDBAccess.saveDeleteData(userId, index);

        CardController controller = new CardController(userObject);
        Card retrievedCard = controller.getCard(cardId);

        assertNull(retrievedCard, "Expected the card to be deleted.");
    }

    @Test
    void successSaveDataTest() {
        int userId = 11000;
        String cardId = cardController.newId("11");
        Card testCard = new Card(cardId, "Test Card", "12/2025", "123");

        CardDBAccess cardDBAccess = new CardDBAccess();
        cardDBAccess.saveData(userId, testCard);

        List<Card> cards = cardDBAccess.readData(userId);
        int index = cardDBAccess.readDataIndex(userId, cardId);
        Card savedCard = cards.get(index);

        assertEquals(cardId, savedCard.getId());
        assertEquals("Test Card", savedCard.getUsage());
        assertEquals(0.0, savedCard.getExpenses());
    }

    @Test
    void successCardEntity() {
        String cardId = CardController.newId("Test Card");
        String date = CardController.newDate();
        String code = CardController.newCode();
        Card testCard = new Card(cardId, "Test Card", date, code);
        assertEquals(testCard.getId(), cardId);
        assertEquals(testCard.getCode(), code);
        assertEquals(testCard.getDate(), date);
        assertEquals(testCard.getExpenses(), 0);

        testCard.updateAmount(7000);
        assertEquals(testCard.getExpenses(), -7000);
    }
}
