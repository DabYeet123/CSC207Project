package Brokerage;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.Config;

public class StockAPIConfig {
    public static void initialize() {
        Config cfg = Config.builder()
                .key("QD1VO3QMDYPFCTIB") // Replace with your key
                .timeOut(10)
                .build();
        AlphaVantage.api().init(cfg);
    }
}
