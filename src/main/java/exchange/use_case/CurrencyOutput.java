package exchange.use_case;

import exchange.inferface_adapter.CurrencyExchangeController;

public class CurrencyOutput {
    private final double changedAmount;

    public CurrencyOutput(double inputAmount) {
        this.changedAmount = CurrencyExchangeController.getRate() * inputAmount;
    }

    public double getChangedAmount() {
        return changedAmount;
    }
}
