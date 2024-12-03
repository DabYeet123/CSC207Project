package house_map.adapter;

import java.util.List;

import app.ControllerInterface;
import userdataobject.UserObject;
import login.loggedin.LoggedInController;
import house_map.data_access.HouseData;
import house_map.data_object.HouseObject;
import house_map.use_case.house_transaction.HouseTransactionInput;
import house_map.use_case.house_transaction.HouseTransactionInputBoundary;
import house_map.use_case.house_transaction.HouseTransactionUseCase;

/**
 * The Controller for the House Map.
 */
public class HouseMapController implements ControllerInterface {

    private final UserObject loggedInUser;
    private final HouseMapPresenter houseMapPresenter;
    private final LoggedInController loggedInController;
    private final HouseTransactionInputBoundary houseTransactionUseCase;
    private final HouseData houseData;

    public HouseMapController(UserObject user, LoggedInController loggedInController) {
        this.loggedInUser = user;
        final PopUpTransactionController popUpTransactionController =
                new PopUpTransactionController(user, this);
        final PopUpTransactionPresenter popUpTransactionPresenter =
                popUpTransactionController.getPopUpTransactionPresenter();
        this.houseData = new HouseData();
        this.houseMapPresenter = new HouseMapPresenter(this);
        this.houseTransactionUseCase = new HouseTransactionUseCase(popUpTransactionPresenter, houseMapPresenter);
        this.loggedInController = loggedInController;
    }

    @Override
    public void launch() {
        houseMapPresenter.showView();
    }

    /**
     * Navigates back to the main view and closes the current view.
     */
    public void back() {
        houseMapPresenter.disposeView();
        loggedInController.launch();
    }

    /**
     * Executes the use case given the parameters.
     * @param houseObject the house object the user is buying
     * @param price the price of the house
     * @param buying whether the user is buying or selling
     */
    public void execute(HouseObject houseObject, double price, boolean buying) {
        final HouseTransactionInput houseTransactionInput =
                new HouseTransactionInput(loggedInUser, houseObject, price, buying);
        houseTransactionUseCase.execute(houseTransactionInput);
    }

    public List<HouseObject> getHouses() {
        return houseData.getHouses();
    }

}
