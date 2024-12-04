package insurance;

import card.dataAccess.CardDBAccess;
import card.dataObject.Card;
import userdataobject.UserObject;
import insurance.adapter.InsuranceController;
import insurance.dataAccess.InsuranceDBAccess;
import insurance.dataObject.InsuranceObject;
import insurance.adapter.PurchaseInsuranceController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import userdataobject.UsersDBAccess;

import java.util.List;

public class InsuranceTest {
    UserObject user = new UserObject(0, "Yue", "Zheng", "12", 0.0, "InsuranceTest");

    UsersDBAccess usersDBAccess;
    InsuranceController insuranceController;
    PurchaseInsuranceController purchaseInsuranceController;

    @BeforeEach
    void setUp() {
        usersDBAccess = new UsersDBAccess();
        usersDBAccess.saveData(user);
        insuranceController = new InsuranceController();
        purchaseInsuranceController = new PurchaseInsuranceController(user);
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
        InsuranceDBAccess insuranceDBAccess = new InsuranceDBAccess();
        List<InsuranceObject> insurances = insuranceDBAccess.readData(0);
        System.out.println((purchaseInsuranceController.getInsurancesByType("Health")).size());
    }
}
