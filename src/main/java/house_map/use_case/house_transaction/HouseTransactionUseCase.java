package house_map.use_case.house_transaction;

import DataObjects.UserObject;
import house_map.adapter.HouseMapPresenter;
import house_map.adapter.PopUpTransactionController;
import house_map.data_access.HouseDBAccess;
import house_map.data_object.HouseObject;
import house_map.use_case.pop_up_transaction.PopUpTransactionInput;
import house_map.use_case.pop_up_transaction.PopUpTransactionOutput;
import house_map.use_case.pop_up_transaction.PopUpTransactionOutputBoundary;

/**
 * The usecase for the house transactions.
 */
public class HouseTransactionUseCase implements HouseTransactionInputBoundary {

    private final PopUpTransactionOutputBoundary popUpTransactionPresenter;
    private final HouseTransactionOutputBoundary houseMapPresenter;
    private final HouseDBAccess houseDBAccess;

    private HouseObject currentHouse;
    private boolean buying;

    public HouseTransactionUseCase(PopUpTransactionOutputBoundary popUpTransactionPresenter,
                                   HouseTransactionOutputBoundary houseMapPresenter) {
        this.popUpTransactionPresenter = popUpTransactionPresenter;
        this.houseMapPresenter = houseMapPresenter;
        this.houseDBAccess = new HouseDBAccess();
    }


    @Override
    public void execute(HouseTransactionInput houseTransactionInput) {
        final UserObject user = houseTransactionInput.getUser();
        final HouseObject house = houseTransactionInput.getHouseObject();
        final double price = houseTransactionInput.getPrice();
        final boolean buying = houseTransactionInput.isBuying();
        if (price == 0) {
            if (buying) {
                buyHouse(user, house);
            }
            else {
                sellHouse(user, house);
            }
        }
        else {
            confirmTransaction(user, price);
        }
    }

    @Override
    public void buyHouse(UserObject user, HouseObject houseObject) {
        buying = true;
        if (!user.getFirstName().equals(houseObject.getOwner())) {
            currentHouse = houseObject;
            popUpTransactionPresenter.updateView(new PopUpTransactionOutput(-houseObject.getPrice()));
            popUpTransactionPresenter.showView();
        }
        else {
            houseMapPresenter.showMessage("Warning", "You Already Own This House!");
        }
    }

    @Override
    public void sellHouse(UserObject user, HouseObject houseObject) {
        buying = false;
        if (user.getFirstName().equals(houseObject.getOwner())) {
            currentHouse = houseObject;
            popUpTransactionPresenter.updateView(new PopUpTransactionOutput(-houseObject.getPrice()));
            popUpTransactionPresenter.showView();
        }
        else {
            houseMapPresenter.showMessage("Warning", "You Don't Own This House!");
        }
    }

    @Override
    public void confirmTransaction(UserObject user, double price) {
        if (buying) {
            currentHouse.setOwner(user.getFirstName());
            houseMapPresenter.showMessage("Congrats", "Successfully Brought the House");
        }
        else {
            currentHouse.setOwner("");
            houseMapPresenter.showMessage("Congrats", "Successfully Sold the House");
        }
        updateInfo(user, currentHouse, price);

    }

    /**
     * Updates the information on the view and in database
     * @param user the user
     * @param house the house being modified
     * @param price the price being updated
     */
    public void updateInfo(UserObject user, HouseObject house, double price) {
        houseMapPresenter.updateView(new HouseTransactionOutput(user, house));
        houseDBAccess.saveData(user.getUserID(), house);
        popUpTransactionPresenter.updateView(new PopUpTransactionOutput(price));
    }
}
