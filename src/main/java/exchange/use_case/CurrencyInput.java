package exchange.use_case;

import javax.swing.JComboBox;

public class CurrencyInput {
    private final double inputAmount;
    private final JComboBox<String> fromcurrencybox;
    private final JComboBox<String> tocurrencybox;

    public CurrencyInput(double amount, JComboBox<String> fromcurrencybox, JComboBox<String> tocurrencybox) {
        this.inputAmount = amount;
        this.fromcurrencybox = fromcurrencybox;
        this.tocurrencybox = tocurrencybox;
    }

    public JComboBox<String> getFromcurrencybox() {
        return fromcurrencybox;
    }

    public JComboBox<String> getTocurrencybox() {
        return tocurrencybox;
    }

    public double getInputAmount() {
        return inputAmount;
    }
}
