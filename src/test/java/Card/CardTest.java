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

    @Test
    void successCardExistsTestWithChangeExpenses() {
        // Mock setup
        usersDBAccess.saveData(userObject);

        int userId = 11000;
        String cardId = "0010011111";
        Card mockCard = new Card(cardId, "Test Card", "12/2025", "123");

        // Save the card using CardDBAccess
        CardDBAccess cardDBAccess = new CardDBAccess();
        cardDBAccess.saveData(userId, mockCard);

        // Retrieve the card using CardController
        CardController controller = new CardController(userObject);
        Card retrievedCard = controller.getCard(cardId);

        // Validate
        assertNotNull(retrievedCard);
        assertEquals(cardId, retrievedCard.getId());
        assertEquals("Test Card", retrievedCard.getUsage());
        assertEquals(0, retrievedCard.getExpenses());
        retrievedCard.updateAmount(1000);
        assertEquals(-1000.0, retrievedCard.getExpenses());
    }

    @Test
    void failureCardDoesNotExistTest() {
        // Mock setup
        String nonExistentCardId = "1000000000";

        // Attempt to retrieve the card using CardController
        CardController controller = new CardController(userObject);
        Card retrievedCard = controller.getCard(nonExistentCardId);

        // Validate
        assertNull(retrievedCard, "Expected no card to be returned.");
    }

    @Test
    void successDeleteCardTest() {
        // Mock setup
        int userId = 11000; // Mock user ID
        String cardId = cardController.newId("11");
        Card testCard = new Card(cardId, "Test Card", "12/2025", "123");

        // Save the card using CardDBAccess
        CardDBAccess cardDBAccess = new CardDBAccess();
        cardDBAccess.saveData(userId, testCard);
        assertEquals(cardId, cardDBAccess.readDataPoint(userId,cardId).getId());

        // Delete the card using CardDBAccess
        int index = cardDBAccess.readDataIndex(userId, cardId);
        cardDBAccess.saveDeleteData(userId, index);

        // Validate the card is removed
        CardController controller = new CardController(userObject);
        Card retrievedCard = controller.getCard(cardId);

        assertNull(retrievedCard, "Expected the card to be deleted.");
    }

    @Test
    void successSaveDataTest() {
        int userId = 11000;
        String cardId = cardController.newId("11");
        Card testCard = new Card(cardId, "Test Card", "12/2025", "123");

        // Create CardDBAccess instance
        CardDBAccess cardDBAccess = new CardDBAccess();
        cardDBAccess.saveData(userId, testCard);

        // Read back the card to ensure it was saved
        List<Card> cards = cardDBAccess.readData(userId);
        int index = cardDBAccess.readDataIndex(userId, cardId);
        Card savedCard = cards.get(index);

        assertEquals(cardId, savedCard.getId());
        assertEquals("Test Card", savedCard.getUsage());
        assertEquals(0.0, savedCard.getExpenses());
    }

}
