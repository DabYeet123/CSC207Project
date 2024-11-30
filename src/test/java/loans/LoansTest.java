package loans;


import Card.CardController;
import Card.Card;
import Card.CardDBAccess;
import DataObjects.UserObject;
import DataObjects.UsersDBAccess;
import loans.dataObject.LoansDBAccess;
import loans.dataObject.LoansObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LoansTest {
    UserObject user = new UserObject(0, "Yue", "Zheng", "12", 0.0, "CardTest");
    UsersDBAccess usersDBAccess;
    CardController cardController;

    @BeforeEach
    void setUp() {
        usersDBAccess = new UsersDBAccess();
        usersDBAccess.saveData(user);
        cardController = new CardController(user);
    }

//    @Test
//    void getInsurancesByTypeTest() {
//        List<InsuranceObject> insurances = purchaseInsuranceController.getInsurancesByType("Health");
//        System.out.println(insurances);
//    }
//
//    @Test
//    void getAllInsuranceTest() {
//        List<InsuranceObject> insurances = insuranceController.getAllInsurance();
//        System.out.println(insurances);
//    }

    @Test
    void readDataTest() {
        String cardId = "0010011111";
        Card mockCard = new Card(cardId, "Test Card", "12/2025", "123");
        CardDBAccess cardDBAccess = new CardDBAccess();
        cardDBAccess.saveData(0, mockCard);
        LoansDBAccess loansDBAccess = new LoansDBAccess();
        LoansObject loan = new LoansObject(0, 10000, 99, 1, cardId);
        user = loansDBAccess.saveData(0, loan);
        List<LoansObject> loans = loansDBAccess.readData(user.getUserID());
        System.out.println(loans);
    }
}
