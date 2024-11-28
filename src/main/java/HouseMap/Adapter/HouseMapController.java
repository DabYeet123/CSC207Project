package HouseMap.Adapter;

import DataObjects.UserObject;
import HouseMap.DataAccess.HouseData;
import HouseMap.DataObject.*;
import HouseMap.UseCase.HouseTransaction.HouseTransactionIB;
import HouseMap.UseCase.HouseTransaction.HouseTransactionInput;
import HouseMap.UseCase.HouseTransaction.HouseTransactionUseCase;
import LogIn.LoggedIn.LoggedInController;

import java.util.List;

public class HouseMapController {

    UserObject loggedInUser;
    private final HouseMapPresenter houseMapPresenter;
    private final LoggedInController loggedInController;
    private final HouseTransactionIB houseTransactionUseCase;
    private final HouseData houseData;

    public HouseMapController(UserObject user, LoggedInController loggedInController) {
        this.loggedInUser = user;
        PopUpTransactionController popUpTransactionController = new PopUpTransactionController(user,this);
        this.houseData = new HouseData();
        this.houseMapPresenter = new HouseMapPresenter(this);
        this.houseTransactionUseCase = new HouseTransactionUseCase(popUpTransactionController, houseMapPresenter);
        this.loggedInController = loggedInController;
    }

    public void launch(){
        houseMapPresenter.showView();
    }

    public void back() {
        houseMapPresenter.disposeView();
        loggedInController.launch();
    }

    public void execute(HouseObject houseObject, double price, boolean buying) {
        HouseTransactionInput houseTransactionInput = new HouseTransactionInput(loggedInUser, houseObject, price, buying);
        houseTransactionUseCase.execute(houseTransactionInput);
    }

    public List<HouseObject> getHouses() {
        return houseData.getHouses();
    }


}
