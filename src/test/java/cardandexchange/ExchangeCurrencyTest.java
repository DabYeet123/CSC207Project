package cardandexchange;

import exchange.inferface_adapter.CurrencyExchangeController;
import exchange.CurrencyExchangeView;
import exchange.inferface_adapter.CurrencyExchangePresenter;
import exchange.use_case.CurrencyInput;
import exchange.use_case.CurrencyInputBoundary;
import exchange.use_case.CurrencyUsecase;
import userdataobject.UserObject;
import userdataobject.UsersDBAccess;
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
    CurrencyExchangePresenter currencyExchangePresenter = new CurrencyExchangePresenter(controller);

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
        CurrencyUsecase usecase = new CurrencyUsecase(currencyExchangePresenter);
        CurrencyInput input = new CurrencyInput(Double.parseDouble(mockInputField.getText()), fromCurrencyBox, toCurrencyBox);
        usecase.execute(input);
    }

    @Test
    void successInputAmountTest() {
        CurrencyExchangeView mockView = new CurrencyExchangeView(controller);
        JTextField mockInputField = new JTextField("100");
        JComboBox<String> fromCurrencyBox = new JComboBox<>(new String[]{"USD"});
        JComboBox<String> toCurrencyBox = new JComboBox<>(new String[]{"EUR"});
        CurrencyUsecase usecase = new CurrencyUsecase(currencyExchangePresenter);
        CurrencyInput input = new CurrencyInput(Double.parseDouble(mockInputField.getText()), fromCurrencyBox, toCurrencyBox);
        usecase.execute(input);
    }

    @Test
    void viewTestForButtonExchange() {
        currencyExchangeView.getInputAmountField().setText("100");
        currencyExchangeView.setFromCurrencyBox(new JComboBox<>(new String[]{"USD"}));
        currencyExchangeView.setToCurrencyBox(new JComboBox<>(new String[]{"CAD"}));
        currencyExchangeView.getExchangeButton().doClick();
    }

    @Test
    void testBack() {
        currencyExchangeView.getBackButton().doClick();
    }

    @Test
    void testLaunch(){
        controller.launch();
    }
}
