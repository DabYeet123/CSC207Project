package Transaction.PopUpTransaction;

import App.ControllerInterface;
import DataObjects.UserObject;
import LogIn.LoggedIn.LoggedInController;


public class PopUpTransactionController implements ControllerInterface {

    UserObject loggedInUser;
    private PopUpTransactionPresenter popUpTransactionPresenter;
    private LoggedInController loggedInController;
    private boolean submitPressed = false;
    private double amount;
    private String type;

    public PopUpTransactionController(UserObject user, LoggedInController loggedInController) {
        this.loggedInUser = user;
        this.loggedInController = loggedInController;
        this.popUpTransactionPresenter = new PopUpTransactionPresenter(this);

    }

    @Override
    public void launch() {
        popUpTransactionPresenter.showView();
    }

    public void closeView() {
        popUpTransactionPresenter.disposeView();
    }

    public void makeTransaction(double amount, String type) {
        setAmount(amount);
        this.type = type;

        if(submitPressed) setSubmitPressed(false);
    }

    public void confirm() {
        if(type.equals("House")) confirmHouseBuying();
    }

    public void confirmHouseBuying() {
        loggedInController.getHouseMapController().confirmHouse();
    }


    public boolean isSubmitPressed() {
        return submitPressed;
    }

    public void setSubmitPressed(boolean submitPressed) {
        this.submitPressed = submitPressed;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
