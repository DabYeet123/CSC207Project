package exchange.inferface_adapter;

import javax.swing.JComboBox;

import app.ControllerInterface;
import exchange.use_case.CurrencyInput;
import exchange.use_case.CurrencyOutput;
import exchange.use_case.CurrencyUsecase;
import login.loggedin.LoggedInController;
import lombok.Getter;
import userdataobject.UserObject;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class CurrencyExchangeController implements ControllerInterface {
    private static UserObject loggedInUser;
    @Getter
    private static double rate;
    private final CurrencyUsecase currencyUsecase;
    private final CurrencyExchangePresenter currencyExchangePresenter;

    public CurrencyExchangeController(UserObject user) {
        loggedInUser = user;
        this.currencyExchangePresenter = new CurrencyExchangePresenter(this);
        this.currencyUsecase = new CurrencyUsecase(currencyExchangePresenter);
    }

    public static void setRate(double rate) {
        CurrencyExchangeController.rate = rate;
    }

    @Override
    public void launch() {
        currencyExchangePresenter.showView();
    }

    /**
     * Go back to base view.
     */
    public void goBackToBaseView() {
        currencyExchangePresenter.disposeView();
        final LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }

    /**
     * Act the button.
     * @param amount the amount input.
     * @param fromcurrencybox the From box of currency.
     * @param tocurrencybox the To box of currency.
     */
    public void execute(double amount, JComboBox<String> fromcurrencybox, JComboBox<String> tocurrencybox) {
        final CurrencyInput currencyInput = new CurrencyInput(amount, fromcurrencybox, tocurrencybox);
        final double inputAmount = currencyInput.getInputAmount();
        currencyUsecase.execute(currencyInput);
        currencyExchangePresenter.showData(new CurrencyOutput(inputAmount));
    }
}
