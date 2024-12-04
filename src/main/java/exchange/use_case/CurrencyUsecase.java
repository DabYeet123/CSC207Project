package exchange.use_case;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.exchangerate.ExchangeRateResponse;
import exchange.inferface_adapter.CurrencyExchangeController;
import exchange.inferface_adapter.CurrencyExchangePresenter;

public class CurrencyUsecase implements CurrencyInputBoundary{
    private final CurrencyExchangePresenter currencyExchangePresenter;

    public CurrencyUsecase(CurrencyExchangePresenter currencyExchangePresenter) {
        this.currencyExchangePresenter = currencyExchangePresenter;
    }

    /**
     * Change.
     * @param currencyInput currency Input
     */
    public void execute(CurrencyInput currencyInput) {
        // OMY21EWV5Y9FEBUJ
        // G8PHFOEQL7JRT4WA
        CurrencyExchangeApiConfig.initialize();

        AlphaVantage.api()
                .exchangeRate()
                .fromCurrency((String) currencyInput.getFromcurrencybox().getSelectedItem())
                .toCurrency((String) currencyInput.getTocurrencybox().getSelectedItem())
                .onSuccess(CurrencyUsecase::onData)
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
