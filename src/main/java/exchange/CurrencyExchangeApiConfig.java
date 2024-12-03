package exchange;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.Config;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class CurrencyExchangeApiConfig {
    /**
     * Offer an initialize.
     */
    public static void initialize() {
        final Config cfg = Config.builder()
                .key("OMY21EWV5Y9FEBUJ")
                .timeOut(1)
                .build();

        AlphaVantage.api().init(cfg);
    }
}
