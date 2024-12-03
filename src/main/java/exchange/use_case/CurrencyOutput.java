package exchange.use_case;

import exchange.inferface_adapter.CurrencyExchangeController;
import lombok.Getter;

@Getter
public class CurrencyOutput {
    private final double changedAmount;

    public CurrencyOutput(double inputAmount) {
        this.changedAmount = CurrencyExchangeController.getRate() * inputAmount;
    }

}
