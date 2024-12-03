package brokerage.use_case;


import brokerage.entity.Stock;
import com.crazzyghost.alphavantage.timeseries.response.StockUnit;
import oldstuff.Views.userdataobject.UserObject;

import java.util.List;

/**
 *  Output data for the brokerage use case
 */
public class BrokerageOutputData {
    private final UserObject user;
    private final String stockSymbol;
    private final int quantity;
    private final Stock stock;
    private final double price;
    private final List<StockUnit> stockPrices;

    public BrokerageOutputData(String stockSymbol, List<StockUnit> stockPrices) {
        this.stockSymbol = stockSymbol;
        this.stockPrices = stockPrices;
    }

    public BrokerageOutputData(UserObject user, String stockSymbol, int quantity) {
        this.user = user;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
    }
}


/*
    ???
*/