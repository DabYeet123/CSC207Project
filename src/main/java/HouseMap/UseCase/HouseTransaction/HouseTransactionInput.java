package HouseMap.UseCase.HouseTransaction;

import DataObjects.UserObject;
import HouseMap.DataObject.HouseObject;

public class HouseTransactionInput {

    private final UserObject user;
    private final HouseObject houseObject;
    private final double price;
    private final boolean buying;

    public HouseTransactionInput(UserObject user, HouseObject houseObject, double price, boolean buying) {
        this.user = user;
        this.houseObject = houseObject;
        this.price = price;
        this.buying = buying;
    }

    public boolean isBuying() {
        return buying;
    }

    public double getPrice() {
        return price;
    }

    public HouseObject getHouseObject() {
        return houseObject;
    }

    public UserObject getUser() {
        return user;
    }
}
