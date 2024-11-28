package Exchange;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.Config;

public class CurrencyExchangeAPIConfig {
    public static void initialize() {
        Config cfg = Config.builder()
                .key("OMY21EWV5Y9FEBUJ")
                .timeOut(1)
                .build();

        AlphaVantage.api().init(cfg);
    }
}
