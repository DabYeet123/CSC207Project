package house_map.use_case.house_transaction;

import DataObjects.UserObject;
import house_map.data_object.HouseObject;

/**
 * The Output Data for the House Transaction use case.
 */
public class HouseTransactionOutput {

    private final UserObject user;
    private final HouseObject houseObject;

    public HouseTransactionOutput(UserObject user, HouseObject houseObject) {
        this.user = user;
        this.houseObject = houseObject;
    }

    public HouseObject getHouseObject() {
        return houseObject;
    }

    public UserObject getUser() {
        return user;
    }
}
