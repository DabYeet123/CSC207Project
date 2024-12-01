package House.HouseMap;

import userdataobject.UserObject;
import Functionality.FunctionalityController;
import House.DataObject.HouseDBAccess;
import House.DataObject.HouseObject;
import login.loggedin.LoggedInController;

public class HouseMapController extends FunctionalityController {

    private final HouseDBAccess houseDBAccess;
    UserObject loggedInUser;
    private final HouseMapPresenter houseMapPresenter;
    private final LoggedInController loggedInController;
    private HouseObject house;
    private double amount;

    public HouseMapController(UserObject user, LoggedInController loggedInController) {
        this.loggedInUser = user;
        this.houseMapPresenter = new HouseMapPresenter(user,this);
        this.loggedInController = loggedInController;
        this.houseDBAccess = new HouseDBAccess();
    }

    public void launch(){
        houseMapPresenter.showView();
    }

    @Override
    public void back() {
        houseMapPresenter.disposeView();
        loggedInController.launch();
    }

    public void buyHouse(HouseObject house, double amount) {
        //loggedInController.popUpTransaction(amount, "House");
        setHouse(house, amount);
    }

    public void confirmHouse() {
        house.setOwner(loggedInUser.getFirstName());
        houseDBAccess.saveData(0, house);
        System.out.println("house brought");
    }

    public void setHouse(HouseObject house, double amount) {
        this.house = house;
        this.amount = amount;
    }
}
