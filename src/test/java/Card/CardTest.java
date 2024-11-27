package Card;

import DataObjects.UserObject;
import DataObjects.UsersDBAccess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**Before the Test, please delete the file of "CardTestView"; "CardTest"; "CardEmptyTest" to make sure the
 * card won't have any issue
 * clean the User repeated many times in Users.json, with UserId 12001, 11000, 11001, 11002
 */

class CardTest {
    UserObject userObject = new UserObject(11000, "Yue", "Zheng", "12", 0.0, "CardTest");
    UsersDBAccess usersDBAccess;
    CardController cardController;

    /*
        The UserId here is all 11000 because save data in 11000 user and in each user we can test the card part
     */
    @BeforeEach
    void setUp(){
        usersDBAccess = new UsersDBAccess();
        usersDBAccess.saveData(userObject);
        cardController = new CardController(userObject);
    }

    @Test
    void successCardExistsTestWithChangeExpenses() {
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
        String cardId = CardMethods.newId("11");
        Card testCard = new Card(cardId, "Test Card", "12/2025", "123");

        CardDBAccess cardDBAccess = new CardDBAccess();
        cardDBAccess.saveData(userId, testCard);

        int index = cardDBAccess.readDataIndex(userId, cardId);
        cardDBAccess.saveDeleteData(userId, index);

        CardController controller = new CardController(userObject);
        Card retrievedCard = controller.getCard(cardId);

        assertNull(retrievedCard, "Expected the card to be deleted.");
    }

    @Test
    void successUpdate() {
        int userId = 11000;
        double amount = 7000;
        String cardId = CardMethods.newId("11");
        Card testCard = new Card(cardId, "Test Card", "12/2025", "123");

        CardDBAccess cardDBAccess = new CardDBAccess();
        cardDBAccess.saveData(userId, testCard);

        cardController.updateData(userId, cardId, amount);

        CardController controller = new CardController(userObject);
        Card retrievedCard = controller.getCard(cardId);

        assertEquals(retrievedCard.getExpenses(), - amount);
    }

    @Test
    void successSaveDataTest() {
        int userId = 11000;
        String cardId = CardMethods.newId("11");
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
    void noindexFound(){
        int userId = 11000;
        String cardId1 = "1000010000";
        String cardId2 = "2000020000";
        CardDBAccess cardDBAccess = new CardDBAccess();
        Card testCard1 = new Card(cardId1, "Test Card", "12/2025", "123");
        Card testCard2 = new Card(cardId2, "Test Card", "12/2025", "123");
        cardDBAccess.saveData(userId, testCard1);
        cardDBAccess.saveData(userId, testCard2);

        int index = cardDBAccess.readDataIndex(userId, "3000030000");
        assertEquals(index, -1);
    }

    @Test
    void noCardIndex(){
        UserObject userEmptyCard = new UserObject(11001, "noCardIndex","1", "12", 0.0, "CardTestForEmptyCard");
        usersDBAccess.saveData(userEmptyCard);
        cardController = new CardController(userEmptyCard);
        int userId = 11001;
        CardDBAccess cardDBAccess = new CardDBAccess();

        int index = cardDBAccess.readDataIndex(userId, "3000030000");
        assertEquals(index, -1);
    }

    @Test
    void noCardGetCard(){
        UserObject userEmptyCard = new UserObject(11001, "noCardIndex","1", "12", 0.0, "CardTestForEmptyCard");
        usersDBAccess.saveData(userEmptyCard);
        cardController = new CardController(userEmptyCard);
        int userId = 11001;
        CardDBAccess cardDBAccess = new CardDBAccess();

        assertNull(cardDBAccess.readDataPoint(userId, "3000030000"));
    }

    @Test
    void successCardEntityTest() {
        String cardId = CardMethods.newId("Test Card");
        String date = CardMethods.newDate();
        String code = CardMethods.newCode();
        Card testCard = new Card(cardId, "Test Card", date, code);
        assertEquals(testCard.getId(), cardId);
        assertEquals(testCard.getCode(), code);
        assertEquals(testCard.getDate(), date);
        assertEquals(testCard.getExpenses(), 0);

        testCard.updateAmount(7000);
        assertEquals(testCard.getExpenses(), -7000);
    }

    @Test
    void checkIdRepeatTest() {
        UserObject userEmptyCard = new UserObject(11002, "CheckIdRepeat","1", "12", 0.0, "CardTestForEmptyCard");
        usersDBAccess.saveData(userEmptyCard);
        cardController = new CardController(userEmptyCard);
        int userId = 11002;
        Card card = new Card("1000210002", "CheckIdRepeat", "11/26", "234");
        CardDBAccess cardDBAccess = new CardDBAccess();
        cardDBAccess.saveData(userId,card);

        assertFalse(CardMethods.checkId("1000210002"));
    }

    @Test
    void getIdLoopTest() {
        String id = CardMethods.getIdForTest("100110001", "100110");
        assertEquals(10, id.length());
    }

    @Test
    void testCaseLength1() {
        String id = "123456789";
        String result = CardMethods.threeCaseFor0(id,1);
        assertEquals(1, result.length() );
        assertTrue(result.matches("\\d") );
    }

    @Test
    void testCaseLength1Index2() {
        String id = "12345678";
        String result = CardMethods.threeCaseFor0(id,1);
        assertEquals(2, result.length());
        assertTrue(result.matches("\\d{2}"));
        assertTrue(Integer.parseInt(result) >= 0 && Integer.parseInt(result) <= 10);
    }

    @Test
    void testCaseLength2Index2() {
        String id = "12345678";
        String result = CardMethods.threeCaseFor0(id,11);
        assertEquals(2, result.length());
        assertTrue(result.matches("\\d{2}"));
        assertTrue(Integer.parseInt(result) >= 10 && Integer.parseInt(result) <= 99);
    }

    @Test
    void testCaseLength1Index3() {
        String id = "1234567";
        String result = CardMethods.threeCaseFor0(id,1);
        assertEquals(3, result.length());
        assertTrue(result.matches("\\d{3}"));
        assertTrue(Integer.parseInt(result) >= 0 && Integer.parseInt(result) <= 10);
    }

    @Test
    void testCaseLength2Index3() {
        String id = "1234567";
        String result = CardMethods.threeCaseFor0(id,11);
        assertEquals(3, result.length());
        assertTrue(result.matches("\\d{3}"));
        assertTrue(Integer.parseInt(result) >= 10 && Integer.parseInt(result) <= 99);
    }

    @Test
    void testCaseLength3Index3() {
        String id = "1234567";
        String result = CardMethods.threeCaseFor0(id,111);
        assertEquals(3, result.length());
        assertTrue(result.matches("\\d{3}"));
        assertTrue(Integer.parseInt(result) >= 100 && Integer.parseInt(result) <= 999);
    }

    @Test
    void newDateTest1Index() {
        String date = CardMethods.getDateForTest(1, 2025);
        assertEquals("01/2030", date);
    }

    @Test
    void newDateTest2Index() {
        String date = CardMethods.getDateForTest(11, 2025);
        assertEquals("11/2030", date);
    }

    @Test
    void newCodeTest1Index() {
        String code = CardMethods.getNewCodeForTest(1);
        assertTrue(code.matches("\\d{3}"));
        assertTrue(Integer.parseInt(code) >= 0 && Integer.parseInt(code) <= 10);
        assertEquals("001", code);
    }

    @Test
    void newCodeTest2Index() {
        String code = CardMethods.getNewCodeForTest(11);
        assertTrue(code.matches("\\d{3}"));
        assertTrue(Integer.parseInt(code) >= 10 && Integer.parseInt(code) <= 99);
        assertEquals("011", code);
    }

    @Test
    void newCodeTest3Index() {
        String code = CardMethods.getNewCodeForTest(111);
        assertTrue(code.matches("\\d{3}"));
        assertTrue(Integer.parseInt(code) >= 100 && Integer.parseInt(code) <= 999);
        assertEquals("111", code);
    }
}
