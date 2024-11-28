package Exchange;

import App.ControllerInterface;
import DataObjects.UserObject;
import LogIn.LoggedIn.LoggedInController;

public class CurrencyExchangeController implements ControllerInterface {
    public static double rate;
    static UserObject loggedInUser;
    private final CurrencyExchangePresenter currencyExchangePresenter;

    public CurrencyExchangeController(UserObject user) {
        loggedInUser = user;
        this.currencyExchangePresenter = new CurrencyExchangePresenter(this);
    }

    @Override
    public void launch() {
        currencyExchangePresenter.showView();
    }

    public void goBackToBaseView() {
        currencyExchangePresenter.disposeView();
        LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }

}
