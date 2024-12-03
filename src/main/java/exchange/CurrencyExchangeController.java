package exchange;

import app.ControllerInterface;
import card.use_case.CardOutput;
import login.loggedin.LoggedInController;
import lombok.Getter;
import userdataobject.UserObject;

import javax.swing.*;

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

    public void execute(double amount, JComboBox<String> fromcurrencybox, JComboBox<String> tocurrencybox) {
        final CurrencyInput currencyInput = new CurrencyInput(amount, fromcurrencybox, tocurrencybox);
        final double inputAmount = currencyInput.getInputAmount();
        currencyUsecase.execute(currencyInput);
        currencyExchangePresenter.showData(new CurrencyOutput(inputAmount));
    }
}
