package brokerage.use_case;

public interface BrokerageOutputBoundary {
    void prepareTradeView(BrokerageOutputData outputData);

    void prepareSuccessView(BrokerageOutputData outputData);

    void prepareFailView(String errorMessage);

    void switchToLoggedinView();
}
