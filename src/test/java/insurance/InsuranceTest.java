package insurance;

import userdataobject.UserObject;
import insurance.adapter.InsuranceController;
import insurance.dataAccess.InsuranceDBAccess;
import insurance.dataObject.InsuranceObject;
import insurance.adapter.PurchaseInsuranceController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class InsuranceTest {
    UserObject user = new UserObject(0, "Yue", "Zheng", "12", 0.0, "CardTest");

    InsuranceController insuranceController;
    PurchaseInsuranceController purchaseInsuranceController;

    @BeforeEach
    void setUp() {
        purchaseInsuranceController = new PurchaseInsuranceController(user);
        insuranceController = new InsuranceController();
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
        InsuranceDBAccess insuranceDBAccess = new InsuranceDBAccess();
        List<InsuranceObject> insurances = insuranceDBAccess.readData(0);
        System.out.println(insurances.size());
    }
}