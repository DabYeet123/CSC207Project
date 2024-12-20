package exchange.use_case;

public interface CurrencyInputBoundary {
    /**
     * Used to renew the GUI .
     * @param currencyInput input object
     */
    void execute(CurrencyInput currencyInput);
}
