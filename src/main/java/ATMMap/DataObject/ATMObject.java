package ATMMap.DataObject;

import ATMMap.View.ATMButton;

import javax.swing.*;

public class ATMObject {

    public static final double FEE = 2;
    public static final int ZOOM = 15;
    public static final int centerX = 300;
    public static final int centerY = 300;

    private final String name;
    private final double remainingCash;
    private final double transactionFee;


    public ATMObject(String name, double remainingCash, double transactionFee, int x, int y, JPanel panel) {
        this.name = name;
        this.remainingCash = remainingCash;
        this.transactionFee = transactionFee;
        new ATMButton(this, x, y, 20, 20, panel);
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
