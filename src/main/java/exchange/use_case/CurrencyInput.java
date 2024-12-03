package exchange.use_case;

import lombok.Getter;

import javax.swing.JComboBox;

@Getter
public class CurrencyInput {
    private final double inputAmount;
    private final JComboBox<String> fromcurrencybox;
    private final JComboBox<String> tocurrencybox;

    public CurrencyInput(double amount, JComboBox<String> fromcurrencybox, JComboBox<String> tocurrencybox) {
        this.inputAmount = amount;
        this.fromcurrencybox = fromcurrencybox;
        this.tocurrencybox = tocurrencybox;
    }

}
