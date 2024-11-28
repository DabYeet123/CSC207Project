package Exchange;

import DataObjects.UserObject;
import DataObjects.UsersDBAccess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

/**Before the Test, please delete the file of "Exchange" to make sure the
 * user won't have any issue
 * clean the User repeated many times in Users.json, with UserId 13001
 */

public class ExchangeCurrencyTest {
    UserObject userObject = new UserObject(13001, "Exchange", "Currency", "12", 0.0, "Exchange");
    UsersDBAccess usersDBAccess = new UsersDBAccess();
    CurrencyExchangeController controller = new CurrencyExchangeController(userObject);
    CurrencyExchangeView currencyExchangeView = new CurrencyExchangeView(controller);

    @BeforeEach
    void setUp() {
        usersDBAccess.saveData(userObject);
    }


    @Test
    void failureInvalidInputAmountTest() {
        CurrencyExchangeView mockView = new CurrencyExchangeView(controller);
        JTextField mockInputField = new JTextField();
        JComboBox<String> fromCurrencyBox = new JComboBox<>(new String[]{"USD"});
        JComboBox<String> toCurrencyBox = new JComboBox<>(new String[]{"EUR"});
        mockView.exchangeCurrency(fromCurrencyBox, mockInputField, toCurrencyBox);
    }

    @Test
    void successInputAmountTest() {
        CurrencyExchangeView mockView = new CurrencyExchangeView(controller);
        JTextField mockInputField = new JTextField("100");
        JComboBox<String> fromCurrencyBox = new JComboBox<>(new String[]{"USD"});
        JComboBox<String> toCurrencyBox = new JComboBox<>(new String[]{"EUR"});
        mockView.exchangeCurrency(fromCurrencyBox, mockInputField, toCurrencyBox);
    }

    @Test
    void viewTestForButtonExchange() {
        currencyExchangeView.inputAmountField.setText("100");
        currencyExchangeView.fromCurrencyBox = new JComboBox<>(new String[]{"USD"});
        currencyExchangeView.toCurrencyBox = new JComboBox<>(new String[]{"CAD"});
        currencyExchangeView.exchangeButton.doClick();
    }

    @Test
    void testBack() {
        currencyExchangeView.backButton.doClick();
    }

    @Test
    void testLaunch(){
        controller.launch();
    }
}