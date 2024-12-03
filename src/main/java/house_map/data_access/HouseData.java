package house_map.data_access;

import java.util.List;

import house_map.data_object.HouseObject;

/**
 * Holds all the house data to be accessed.
 */
public class HouseData {

    private final List<HouseObject> houses;

    public HouseData() {
        final HouseDBAccess houseDBAccess = new HouseDBAccess();
        this.houses = houseDBAccess.readData(0);
    }

    public List<HouseObject> getHouses() {
        return houses;
    }
}
