package ATMMap.DataObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class ATMObjectTest {

    private ATMObject atm;

    @BeforeEach
    void init() {
        JPanel panel = new JPanel();
        atm = new ATMObject("The ATM", 1000, 2, 0, 0, panel);
    }

    @Test
    void getName() {
        Assertions.assertEquals("The ATM", atm.getName());
    }

    @Test
    void getRemainingCash() {
        Assertions.assertEquals(1000, atm.getRemainingCash());
    }

    @Test
    void getTransactionFee() {
        Assertions.assertEquals(2, atm.getTransactionFee());
    }


}