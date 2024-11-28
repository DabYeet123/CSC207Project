package HouseMap.DataAccess;

import HouseMap.DataObject.HouseObject;

import java.util.List;

public class HouseData {

    private final List<HouseObject> houses;

    public HouseData() {
        HouseDBAccess houseDBAccess = new HouseDBAccess();
        this.houses = houseDBAccess.readData(0);
    }

    public HouseObject getHouse(String address) {
        for(HouseObject house: houses) {
            if (house.getAddress().equals(address)) {
                return house;
            }
        }
        return null;
    }

    public List<HouseObject> getHouses() {
        return houses;
    }
}
