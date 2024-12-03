package oldstuff.Views.Exchange;

public class CurrencyExchangePresenter {
    private final CurrencyExchangeView currencyExchangeView;

    public CurrencyExchangePresenter(CurrencyExchangeController currencyExchangeController) {
        this.currencyExchangeView = new CurrencyExchangeView(currencyExchangeController);
    }

    public void showView() {
        currencyExchangeView.setVisible(true);
    }

    public void disposeView() {
        currencyExchangeView.setVisible(false);
        currencyExchangeView.dispose();
    }
}
