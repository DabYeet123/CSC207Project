package brokerage.interface_adapter;

import java.util.List;

import brokerage.app.StockApi;
import brokerage.entity.Stock;
import brokerage.use_case.BrokerageInputData;
import com.crazzyghost.alphavantage.timeseries.response.StockUnit;
import oldstuff.Views.login.loggedin.LoggedInController;
import oldstuff.Views.userdataobject.UserObject;
import brokerage.use_case.BrokerageInputBoundary;

/**
 * Controls the brokerage functionality for the application, including handling stock searches,
 * buying and selling stocks, and managing user data.
 */
public class BrokerageController {
    private final BrokerageInputBoundary brokerageInteractor;

    public BrokerageController(BrokerageInputBoundary brokerageInteractor) {
        this.brokerageInteractor = brokerageInteractor;
    }

    /**
     * Action for search.
     * @param stockSymbol stock
     */
    public void searchStock(String stockSymbol) {
        final BrokerageInputData brokerageInputData = new BrokerageInputData(stockSymbol);
        brokerageInteractor.searchStock(brokerageInputData);
    }

    /**
     * Action for trade.
     * @param user user
     * @param stockSymbol symbol
     * @param quantity quantity
     * @param stock stock
     */
    public void tradeStock(UserObject user, String stockSymbol, int quantity, Stock stock) {
        final BrokerageInputData brokerageInputData = new BrokerageInputData(user, stockSymbol, quantity, stock);
        brokerageInteractor.tradeStock(brokerageInputData);
    }

    /**
     * Switches to LoggedinView.
     */
    public void switchToLoggedinView() {
        brokerageInteractor.switchToLoggedinView();
    }

}
