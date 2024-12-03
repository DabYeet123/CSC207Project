package brokerage.app;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.OutputSize;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.timeseries.response.StockUnit;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;

import java.util.List;

public class StockApi {

    public static List<StockUnit> execute(String stockSymbol) {

        StockApiConfig.initialize();

        final TimeSeriesResponse response = AlphaVantage.api()
                .timeSeries()
                .intraday()
                .forSymbol(stockSymbol)
                .interval(Interval.THIRTY_MIN)
                .outputSize(OutputSize.FULL)
                .dataType(DataType.JSON)
                .fetchSync();

        //List<StockUnit> stockData = new ArrayList<>();

        //if (stockUnits != null) {
        //    for (StockUnit unit : response.getStockUnits()) {
        //        stockData.add(new StockUnit(unit.getDate(), unit.getClose()));
        //    }
        //}
        //return stockData;
        return response.getStockUnits();
    }
}
