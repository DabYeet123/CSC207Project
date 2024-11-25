package House.HouseMap;

import ATM.ATMMap.ATMMapPresenter;
import DataObjects.UserObject;
import LogIn.Welcome.WelcomeController;

public class HouseMapController {

    UserObject loggedInUser;
    private WelcomeController welcomeController;
    private final HouseMapPresenter houseMapPresenter;

    public HouseMapController(UserObject user) throws Exception {
        this.loggedInUser = user;
        this.welcomeController = new WelcomeController();
        this.houseMapPresenter = new HouseMapPresenter(this);
    }

    //Temporary
    public HouseMapController() throws Exception {
        this.houseMapPresenter = new HouseMapPresenter(this);
    }

    public void launch(){
        houseMapPresenter.showView();
    }

    public void logOutTriggered(){
        houseMapPresenter.disposeView();
        welcomeController.launch();
    }
}
