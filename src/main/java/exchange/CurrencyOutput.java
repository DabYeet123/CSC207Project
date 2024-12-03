package exchange;

public class CurrencyOutput {
    private final double changedAmount;

    public CurrencyOutput(double inputAmount) {
        this.changedAmount = CurrencyExchangeController.getRate() * inputAmount;
    }

    public double getChangedAmount() {
        return changedAmount;
    }
}
