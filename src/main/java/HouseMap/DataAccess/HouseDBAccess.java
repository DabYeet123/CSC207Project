package HouseMap.DataAccess;

import DataAccess.DataAccessController;
import DataAccess.DataAccessInterface;
import DataObjects.UserObject;
import DataObjects.UsersController;
import HouseMap.DataObject.HouseObject;

import java.util.ArrayList;
import java.util.List;

public class HouseDBAccess implements DataAccessInterface<HouseObject> {

    private static String directory = "";
    DataAccessController controller = new DataAccessController();
    UsersController usersController = new UsersController();

    @Override
    public UserObject saveData(int userID, HouseObject house) {

        List<HouseObject> houses = readData(0);
        List<HouseObject> newHouses = new ArrayList<>();
        for(HouseObject h: houses) {
            if(h.getAddress().equals(house.getAddress())) {
                newHouses.add(house);
            }else {
                newHouses.add(h);
            }
        }
        controller.saveData("\\Houses.json", newHouses, HouseObject.class);
        return null;
    }

    @Override
    public List<HouseObject> readData(int userID) {
        return controller.readData("\\Houses.json", HouseObject.class);
    }
}
