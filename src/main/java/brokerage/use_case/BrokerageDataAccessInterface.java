package brokerage.use_case;

import brokerage.entity.Stock;

public interface BrokerageDataAccessInterface {

    int getQuantity(int userID, String stockID);

    void saveTrade(int userID, Stock stock);

}
