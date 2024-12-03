package exchange.use_case;

import exchange.inferface_adapter.CurrencyExchangeController;
import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.exchangerate.ExchangeRateResponse;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class CurrencyExchangeDataUsage {
    /**
     * Change.
     * @param output output currency
     * @param input input currency
     */
    public static void execute(String input, String output) {
        // OMY21EWV5Y9FEBUJ
        // G8PHFOEQL7JRT4WA
        CurrencyExchangeApiConfig.initialize();

        AlphaVantage.api()
                .exchangeRate()
                .fromCurrency(input)
                .toCurrency(output)
                .onSuccess(CurrencyExchangeDataUsage::onData)
                .fetch();
    }

    /**
     * Response.
     * @param response given response
     */
    public static void onData(ExchangeRateResponse response) {
        CurrencyExchangeController.setRate(response.getExchangeRate());
    }
}
