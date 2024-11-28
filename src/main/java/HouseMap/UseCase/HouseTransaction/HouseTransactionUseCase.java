package HouseMap.UseCase.HouseTransaction;

import DataObjects.UserObject;
import HouseMap.Adapter.HouseMapPresenter;
import HouseMap.Adapter.PopUpTransactionController;
import HouseMap.DataAccess.HouseDBAccess;
import HouseMap.DataObject.HouseObject;

public class HouseTransactionUseCase implements HouseTransactionIB{

    private final PopUpTransactionController popUpTransactionController;
    private final HouseMapPresenter houseMapPresenter;
    private final HouseDBAccess houseDBAccess;

    private HouseObject currentHouse;
    private boolean buying;

    public HouseTransactionUseCase(PopUpTransactionController popUpTransactionController,
                                  HouseMapPresenter houseMapPresenter) {
        this.popUpTransactionController = popUpTransactionController;
        this.houseMapPresenter = houseMapPresenter;
        this.houseDBAccess = new HouseDBAccess();
    }


    @Override
    public void execute(HouseTransactionInput houseTransactionInput) {
        UserObject user = houseTransactionInput.getUser();
        HouseObject house = houseTransactionInput.getHouseObject();
        double price = houseTransactionInput.getPrice();
        boolean buying = houseTransactionInput.isBuying();
        if(price == 0) {
            if(buying) {
                buyHouse(user, house);
            }else{
                sellHouse(user, house);
            }
        }else{
            confirmTransaction(user, price);
        }


    }

    public void buyHouse(UserObject user, HouseObject houseObject) {
        buying = true;
        if(!user.getFirstName().equals(houseObject.getOwner())) {
            currentHouse = houseObject;
            popUpTransactionController.makeTransaction(-houseObject.getPrice());
        }else {
            houseMapPresenter.showMessage("Warning", "You Already Own This House!");
        }
    }

    public void sellHouse(UserObject user, HouseObject houseObject) {
        buying = false;
        if(user.getFirstName().equals(houseObject.getOwner())) {
            currentHouse = houseObject;
            popUpTransactionController.makeTransaction(houseObject.getPrice());
        }else{
            houseMapPresenter.showMessage("Warning", "You Don't Own This House!");
        }
    }

    public void confirmTransaction(UserObject user, double price) {
        if(buying) {
            currentHouse.setOwner(user.getFirstName());
            houseMapPresenter.showMessage("Congrats", "Successfully Brought the House");
        }
        else {
            currentHouse.setOwner("");
            houseMapPresenter.showMessage("Congrats", "Successfully Sold the House");
        }
        popUpTransactionController.updateBalance(price);
        updateInfo(user, currentHouse);

    }

    public void updateInfo(UserObject user, HouseObject house) {
        houseMapPresenter.updateView(new HouseTransactionOutput(user, house));
        houseDBAccess.saveData(user.getUserID(), house);
    }
}
