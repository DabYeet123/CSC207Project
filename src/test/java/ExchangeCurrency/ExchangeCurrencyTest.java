package ExchangeCurrency;

import DataObjects.UserObject;
import Exchange.CurrencyExchangeController;
import Exchange.CurrencyExchangePresenter;
import Exchange.CurrencyExchangeView;
import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.exchangerate.ExchangeRateResponse;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExchangeCurrencyTest {
    UserObject userObject = new UserObject(11001, "Exchange", "Currency", "12", 0.0, "file2");
    @Test
    void failureInvalidInputAmountTest() {
        CurrencyExchangeController controller = new CurrencyExchangeController(userObject);

        String fromCurrencyBox = new JComboBox<>(new String[]{"USD"}).toString();
        String toCurrencyBox = new JComboBox<>(new String[]{"EUR"}).toString();

        try {
            controller.changeInto(fromCurrencyBox, toCurrencyBox);
        } catch (NumberFormatException e) {
            assertEquals("Invalid Input Amount", e.getMessage());
        }
    }

    @Test
    void failureMissingFromCurrencyTest() {
        CurrencyExchangeController controller = new CurrencyExchangeController(userObject);

        CurrencyExchangeView mockView = new CurrencyExchangeView(controller);
        JTextField mockInputField = new JTextField("100");
        JComboBox<String> fromCurrencyBox = new JComboBox<>(new String[]{""});
        JComboBox<String> toCurrencyBox = new JComboBox<>(new String[]{"EUR"});

        try {
            controller.exchangeCurrency(mockView, fromCurrencyBox, mockInputField, toCurrencyBox);
        } catch (Exception e) {
            assertEquals("Please select a valid 'From' currency.", e.getMessage());
        }
    }

    @Test
    void failureConnectionToApiTest() {
        CurrencyExchangeController controller = new CurrencyExchangeController(userObject);

        CurrencyExchangeView mockView = new CurrencyExchangeView(controller);
        JTextField mockInputField = new JTextField("100");
        JComboBox<String> fromCurrencyBox = new JComboBox<>(new String[]{"USD"});
        JComboBox<String> toCurrencyBox = new JComboBox<>(new String[]{"EUR"});

        CurrencyExchangeController.changeInto("USD", "EUR");
        CurrencyExchangeController.rate = 0;

        try {
            controller.exchangeCurrency(mockView, fromCurrencyBox, mockInputField, toCurrencyBox);
        } catch (Exception e) {
            assertEquals("Unable to fetch exchange rate. Please try again later.", e.getMessage());
        }
    }

    @Test
    void apiTest() {
        Config cfg = Config.builder()
                .key("OMY21EWV5Y9FEBUJ")
                .timeOut(1)
                .build();

        AlphaVantage.api().init(cfg);
        AlphaVantage.api()
                .exchangeRate()
                .fromCurrency("USD")
                .toCurrency("CAD")
                .onSuccess((e) -> onData(e))
                .fetch();
    }

    public static void onData(ExchangeRateResponse response){
        assertEquals(Math.round(response.getExchangeRate()), 1);
    }
}
