package atm_map.data_object;

import javax.swing.JPanel;

import atm_map.view.AtmButton;

/**
 * The Atm Object associated with the displayed atm on the .
 */
public class AtmObject {

    private final String name;
    private final double remainingCash;
    private final double transactionFee;

    public AtmObject(String name, double remainingCash, double transactionFee,
                     int xCoordinate, int yCoordinate, JPanel panel) {
        this.name = name;
        this.remainingCash = remainingCash;
        this.transactionFee = transactionFee;
        new AtmButton(this, xCoordinate, yCoordinate, AtmFactory.BUTTONSIZE, AtmFactory.BUTTONSIZE, panel);
    }

    public String getName() {
        return name;
    }

    public double getRemainingCash() {
        return remainingCash;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

}
