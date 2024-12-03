package brokerage.use_case;

import brokerage.entity.Stock;
import brokerage.entity.StockFactory;
import com.crazzyghost.alphavantage.timeseries.response.StockUnit;
import brokerage.app.StockApi;
import oldstuff.Views.userdataobject.UserObject;

import java.util.List;

public class BrokerageInteractor implements BrokerageInputBoundary {

    private BrokerageDataAccessInterface brokerageDataAccessInterface;
    private BrokerageOutputBoundary brokerageOutputBoundary;
    private StockFactory stockFactory;
    private StockApi stockApi;

    public BrokerageInteractor(BrokerageDataAccessInterface brokerageDataAccessInterface,
                               BrokerageOutputBoundary brokerageOutputBoundary) {
        this.brokerageDataAccessInterface = brokerageDataAccessInterface;
        this.brokerageOutputBoundary = brokerageOutputBoundary;
    }

    @Override
    public void searchStock(BrokerageInputData input) {
        final String stockSymbol = input.getStockSymbol();
        if (stockSymbol.isEmpty()) {
            brokerageOutputBoundary.prepareFailView("Insert Stock Symbol");
        }
        else {
            final List<StockUnit> stocks = stockApi.execute(stockSymbol);
            final BrokerageOutputData brokerageOutputData = new BrokerageOutputData(stockSymbol, stocks);
            brokerageOutputBoundary.prepareTradeView(brokerageOutputData);
        }
    }

    @Override
    public void tradeStock(BrokerageInputData input) {
        final UserObject user = input.getUser();
        final String stockSymbol = input.getStockSymbol();
        final int quantity = input.getQuantity();
        final Stock stock = input.getStock();

        final int quantityOwned = brokerageDataAccessInterface.getQuantity(user.getUserID(), stockSymbol);
        if (-1 * quantity > quantityOwned) {
            brokerageOutputBoundary.prepareFailView("Not enough stock");
        }
        else {
            brokerageDataAccessInterface.saveTrade(user.getUserID(), stock);
            final BrokerageOutputData brokerageOutputData = new BrokerageOutputData(user, stockSymbol, quantity);
            brokerageOutputBoundary.prepareSuccessView(brokerageOutputData);
        }
    }

    @Override
    public void switchToLoggedinView() {
        brokerageOutputBoundary.switchToLoggedinView();
    }
}

