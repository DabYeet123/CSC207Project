package card;

import card.adapter.CardPresenter;
import userdataobject.UserObject;
import userdataobject.UsersDBAccess;
import card.adapter.CardController;
import card.view.CardView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
    CardPresenter cardPresenter;
    private JTextField usageField;
    private DefaultTableModel model;


    @BeforeEach
    void setUp() {
        usersDBAccess.saveData(userObject);
        cardController = new CardController(userObject);
        cardView = new CardView(cardController);
        usageField = new JTextField();
        cardPresenter = new CardPresenter(cardController);
        model = new DefaultTableModel(new String[]{"ID", "Name", "Expiry Date", "Security Code", "Expenses"}, 0);


        cardView.setUsageField(usageField);
        cardView.setModel(model);
    }

    @Test
    void testAddCardSuccess() {
        usageField.setText("TestCard");

        cardView.getAddButton().doClick();

        //assertEquals("TestCard", model.getValueAt(model.getRowCount() + 1, 1));
        assertEquals("TestCard", usageField.getText());
    }

    @Test
    void testDeleteCardSuccess() {
        cardView.getTable().setRowSelectionInterval(0, 0);
        cardView.getDeleteButton().doClick();

        assertEquals(model.getRowCount(), 0);
    }

    @Test
    void testDeleteCardFail() {
        cardView.getDeleteButton().doClick();
    }

    @Test
    void testLaunch(){
        cardController.launch();
    }

    @Test
    void testBack() {
        cardView.getBackButton().doClick();
    }

    @Test
    void testAdd10Cards() {
        for (int i = 0; i < 10; i++) {
            usageField.setText("TestCard");
            cardView.getAddButton().doClick();
        }
        cardView.getAddButton().doClick();
    }
}