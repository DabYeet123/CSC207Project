package brokerage.use_case;

public interface BrokerageInputBoundary {
    void searchStock(BrokerageInputData input);

    void tradeStock(BrokerageInputData input);

    void switchToLoggedinView();
}


/*
definisci le funzioni che vuoi chiamare dal'interactor (search, buy, sell, switchtologgedinview)
 */