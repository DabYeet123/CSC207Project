package HouseMap.UseCase.HouseTransaction;

import DataObjects.UserObject;
import HouseMap.DataObject.HouseObject;

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
