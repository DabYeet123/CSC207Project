package Card;

import DataObjects.UserObject;
import DataObjects.UsersDBAccess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**Before the Test, please delete the file of "CardTestView"; "CardTest"; "CardEmptyTest" to make sure the
 * card won't have any issue
 * clean the User repeated many times in Users.json, with UserId 12001, 11000, 11001, 11002
 */

class CardViewTest {
    UserObject userObject = new UserObject(12001, "Yue", "Zheng", "12", 0.0, "CardTestview");
    UsersDBAccess usersDBAccess = new UsersDBAccess();

    CardController cardController;
    CardView cardView;
    private JTextField usageField;
    private DefaultTableModel model;


    @BeforeEach
    void setUp() {
        usersDBAccess.saveData(userObject);
        cardController = new CardController(userObject);
        cardView = new CardView(cardController);
        usageField = new JTextField();
        model = new DefaultTableModel(new String[]{"ID", "Name", "Expiry Date", "Security Code", "Expenses"}, 0);


        cardView.usageField = usageField;
        cardView.model = model;
    }

    @Test
    void testAddCardSuccess() {
        usageField.setText("TestCard");

        cardView.addButton.doClick();

        assertEquals("TestCard", model.getValueAt(model.getRowCount() - 1, 1));
        assertEquals("", usageField.getText());
    }

    @Test
    void testDeleteCardSuccess() {
        cardView.table.setRowSelectionInterval(0, 0);
        cardView.deleteButton.doClick();

        assertEquals(model.getRowCount(), 0);
    }

    @Test
    void testDeleteCardFail() {
        cardView.deleteButton.doClick();
    }

    @Test
    void testLaunch(){
        cardController.launch();
    }

    @Test
    void testBack() {
        cardView.backButton.doClick();
    }

    @Test
    void testAdd10Cards() {
        for (int i = 0; i < 10; i++) {
            usageField.setText("TestCard");
            cardView.addButton.doClick();
        }
        cardView.addButton.doClick();
    }
}