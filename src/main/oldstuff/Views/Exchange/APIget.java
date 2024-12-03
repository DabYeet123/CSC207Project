package oldstuff.Views.Exchange;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.exchangerate.ExchangeRateResponse;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.OutputSize;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;


public class APIget {
    // OMY21EWV5Y9FEBUJ
    // G8PHFOEQL7JRT4WA
    // #&ALPHA10100DEMOKEY
    public static void main(String[] args) {
        Config cfg = Config.builder()
                .key("OMY21EWV5Y9FEBUJ")
                .timeOut(10)
                .build();

        AlphaVantage.api().init(cfg);

//        AlphaVantage.api()
//                .exchangeRate()
//                .fromCurrency("USD")
//                .toCurrency("CAD")
//                .onSuccess((e) -> onData(e))
//                .fetch();
        TimeSeriesResponse response = AlphaVantage.api()
                .timeSeries()
                .intraday()
                .forSymbol("IBM")
                .interval(Interval.FIVE_MIN)
                .outputSize(OutputSize.FULL)
                .fetchSync();
        System.out.println(response);
    }

    public static void onData(ExchangeRateResponse response){
        System.out.println(response.getExchangeRate());
        System.out.println(response.getBidPrice());
        System.out.println(response.getAskPrice());
        System.out.println(response.getFromCurrencyCode());
        System.out.println(response.getFromCurrencyName());
        System.out.println(response.getToCurrencyCode());
        System.out.println(response.getToCurrencyName());
        System.out.println(response.getLastRefreshed());
        System.out.println(response.getTimeZone());
    }
}
