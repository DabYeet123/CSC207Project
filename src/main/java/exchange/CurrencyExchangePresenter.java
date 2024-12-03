package exchange;

import app.PresenterInterface;
import exchange.CurrencyExchangeView;
import transaction.makeTransaction.MakeTransactionController;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class CurrencyExchangePresenter implements PresenterInterface<MakeTransactionController>, CurrencyOutputBoundary {
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

    @Override
    public void showData(CurrencyOutput currencyOutput) {
        currencyExchangeView.getOutputAmountField().setText(String.format("%.2f", currencyOutput.getChangedAmount()));
    }
}
