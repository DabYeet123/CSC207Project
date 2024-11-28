package Exchange;

import App.PresenterInterface;
import Transaction.MakeTransaction.MakeTransactionController;

public class CurrencyExchangePresenter implements PresenterInterface<MakeTransactionController> {
    private final CurrencyExchangeView currencyExchangeView;

    public CurrencyExchangePresenter(CurrencyExchangeController currencyExchangeController) {
        this.currencyExchangeView = new CurrencyExchangeView(currencyExchangeController);
    }

    @Override
    public void showView() {
        currencyExchangeView.setVisible(true);
    }

    @Override
    public void disposeView() {
        currencyExchangeView.setVisible(false);
        currencyExchangeView.dispose();
    }
}
