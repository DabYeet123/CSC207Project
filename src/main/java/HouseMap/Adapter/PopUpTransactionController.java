package HouseMap.Adapter;

import App.ControllerInterface;
import Card.Card;
import DataObjects.UserObject;
import HouseMap.UseCase.PopUpTransaction.PopUpTransactionInput;
import HouseMap.UseCase.PopUpTransaction.PopUpTransactionUseCase;

import java.text.DecimalFormat;
import java.util.Map;


public class PopUpTransactionController implements ControllerInterface {

    UserObject loggedInUser;
    public DecimalFormat df = new DecimalFormat("#.00");

    private PopUpTransactionPresenter popUpTransactionPresenter;
    private PopUpTransactionUseCase popUpTransactionUseCase;
    private HouseMapController houseMapController;

    public PopUpTransactionController(UserObject user, HouseMapController houseMapController) {
        this.loggedInUser = user;
        this.houseMapController = houseMapController;
        this.popUpTransactionPresenter = new PopUpTransactionPresenter(user,this);
        this.popUpTransactionUseCase = new PopUpTransactionUseCase(user, popUpTransactionPresenter);
    }

    @Override
    public void launch() {
        popUpTransactionPresenter.showView();
    }

    public void closeView() {
        popUpTransactionPresenter.disposeView();
    }


    public void makeTransaction(double price) {
        popUpTransactionUseCase.execute(new PopUpTransactionInput(price));
    }

    public void updateBalance(double price) {
        popUpTransactionUseCase.updateBalance(price);
    }

    public void confirmTransaction(double price) {
        houseMapController.execute(null, price, false);
    }

    public void setCurrentCard(Card card) {
        popUpTransactionUseCase.setCurrentCard(card);
    }

}
