package cardandexchange.adapter;

import App.ControllerInterface;
import DataObjects.UserObject;
import LogIn.LoggedIn.LoggedInController;
import lombok.Getter;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class CurrencyExchangeController implements ControllerInterface {
    private static UserObject loggedInUser;
    @Getter
    private static double rate;
    private final CurrencyExchangePresenter currencyExchangePresenter;

    public CurrencyExchangeController(UserObject user) {
        loggedInUser = user;
        this.currencyExchangePresenter = new CurrencyExchangePresenter(this);
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

}
