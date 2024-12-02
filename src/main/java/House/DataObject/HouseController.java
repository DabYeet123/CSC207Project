package House.DataObject;

import userdataobject.UserObject;
import House.HouseMap.HouseMapController;

import java.util.List;

public class HouseController {

    private final UserObject user;
    private final HouseDBAccess houseDBAccess;
    private final HouseMapController houseMapController;

    public HouseController(UserObject user, HouseMapController houseMapcontroller) {
        this.user = user;
        //this.user = new UserObject(0, "Joe Dude", "", "", 0, "");
        this.houseDBAccess = new HouseDBAccess();
        this.houseMapController = houseMapcontroller;
    }

    public void buyHouse(String address) {
        List<HouseObject> houses = getHouses();
        for(HouseObject house: houses) {
            if (house.getAddress().equals(address)) {
                houseMapController.buyHouse(house,house.getPrice());
                return;
            }
        }
    }

    public void sellHouse(String address) {
        List<HouseObject> houses = getHouses();
        for(HouseObject house: houses) {
            if (house.getAddress().equals(address)) {
                if(user.getFirstName().equals(house.getOwner())) {
                    house.setOwner("");
                    houseDBAccess.saveData(0, house);
                }
                return;
            }
        }
    }

    public List<HouseObject> getHouses() {
        return houseDBAccess.readData(0);
    }

    public UserObject getUser() {
        return user;
    }

}
