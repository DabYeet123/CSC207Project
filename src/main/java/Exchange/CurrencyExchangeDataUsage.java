package Exchange;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.exchangerate.ExchangeRateResponse;

public class CurrencyExchangeDataUsage {
    public static void changeInto(String input, String output) {
        // OMY21EWV5Y9FEBUJ
        // G8PHFOEQL7JRT4WA
        CurrencyExchangeAPIConfig.initialize();

        AlphaVantage.api()
                .exchangeRate()
                .fromCurrency(input)
                .toCurrency(output)
                .onSuccess(CurrencyExchangeDataUsage::onData)
                .fetch();
    }

    public static void onData(ExchangeRateResponse response){
        CurrencyExchangeController.rate = response.getExchangeRate();
    }
}
