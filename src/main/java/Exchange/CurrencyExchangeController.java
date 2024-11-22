package Exchange;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.exchangerate.ExchangeRateResponse;

import javax.swing.*;

public class CurrencyExchangeController {
    private static double rate;

    public static void changeInto(String input, String output) {
        // OMY21EWV5Y9FEBUJ
        // G8PHFOEQL7JRT4WA
        Config cfg = Config.builder()
                .key("OMY21EWV5Y9FEBUJ")
                .timeOut(10)
                .build();

        AlphaVantage.api().init(cfg);

        AlphaVantage.api()
                .exchangeRate()
                .fromCurrency(input)
                .toCurrency(output)
                .onSuccess((e) -> onData(e))
                .fetch();
    }

    public static void onData(ExchangeRateResponse response){
        rate = response.getExchangeRate();
    }

    public void exchangeCurrency(CurrencyExchangeView currencyExchangeView,
                                 JComboBox<String> fromCurrencyBox,
                                 JTextField inputAmountField,
                                 JTextField outputAmountField,
                                 JComboBox<String> toCurrencyBox) {
        try {
            double inputAmount = Double.parseDouble(inputAmountField.getText());
            String fromCurrency = (String) fromCurrencyBox.getSelectedItem();
            String toCurrency = (String) toCurrencyBox.getSelectedItem();

            changeInto(fromCurrency, toCurrency);
            double outputAmount = inputAmount * rate;
            CurrencyExchangeView.outputAmountField.setText(String.format("%.2f", outputAmount));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(currencyExchangeView, "Invalid Input Amount", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}