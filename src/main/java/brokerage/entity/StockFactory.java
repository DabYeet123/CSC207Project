package brokerage.entity;

public class StockFactory {
    /**
     * Creates a new Transaction.
     * @param senderID is a parameter
     * @param receiverID is a parameter
     * @param cardUsed is a parameter
     * @param amount is a parameter
     * @return the new transaction
     */
    public Stock create(int senderID, int receiverID,
                              String cardUsed, double amount) {

        return new Stock();
    }

}
