package brokerage;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.Config;

/**
 * Provides configuration and initialization for the AlphaVantage API.
 * <p>
 * This class is used to set up the AlphaVantage API with the necessary API key
 * and timeout settings, ensuring that it can be used across the application.
 * </p>
 */
public class StockApiConfig {
    /**
     * Initializes the AlphaVantage API with a predefined configuration.
     * <p>
     * The method sets the API key and timeout duration to establish the configuration
     * for interacting with the AlphaVantage API. This should be called before making
     * any requests to the API.
     * </p>
     * Example usage:
     * <pre>
     * {@code
     * StockApiConfig.initialize();
     * }
     * </pre>
     */
    public static void initialize() {
        final int timeOut = 10;
        final Config cfg = Config.builder()
                .key("QD1VO3QMDYPFCTIB")
                .timeOut(timeOut)
                .build();
        AlphaVantage.api().init(cfg);
    }
}
