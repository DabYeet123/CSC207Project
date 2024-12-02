package ATM.DataObject;

import javax.swing.*;

public class ATMObject {

    public static final double CASHRESERVE = 1000;
    public static final double FEE = 2;
    public static final int ZOOM = 15;
    public static final int centerX = 300;
    public static final int centerY = 300;

    private final String name;
    private final double remainingCash;
    private final double transactionFee;
    private final ATMButton atmButton;


    public ATMObject(String name, double remainingCash, double transactionFee, int x, int y, JPanel panel) {
        this.name = name;
        this.remainingCash = remainingCash;
        this.transactionFee = transactionFee;
        this.atmButton = new ATMButton(this, x, y, 20, 20, panel);
        //Maybe not needed
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
    public ATMButton getATMButton() {
        return atmButton;
    }

}
