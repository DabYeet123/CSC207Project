package house_map.use_case.house_transaction;

import DataObjects.UserObject;
import house_map.data_object.HouseObject;

/**
 * Input Boundary for actions which are related to House Transaction.
 */
public interface HouseTransactionInputBoundary {

    /**
    * Execute the house transaction use case.
    * @param houseTransactionInput the input data
    */
    void execute(HouseTransactionInput houseTransactionInput);

    /**
     * Buys the house for the user.
     * @param user the user buying the house
     * @param houseObject the house the user is buying
     */
    void buyHouse(UserObject user, HouseObject houseObject);

    /**
     * Sells the house for the user.
     * @param user the user selling the house
     * @param houseObject the house the user is selling
     */
    void sellHouse(UserObject user, HouseObject houseObject);

    /**
     * Confirms the transaction.
     * @param user the user confirming the transaction
     * @param price the price of the house
     */
    void confirmTransaction(UserObject user, double price);

}
