package atm_map.data_object;

import javax.swing.JPanel;

import atm_map.view.AtmMapView;

/**
 * Factory for creating atms.
 */
public class AtmFactory {

    public static final double FEE = 2;
    public static final int CENTERX = 300;
    public static final int CENTERY = 300;
    public static final int BUTTONSIZE = 20;
    public static final int POPUPWIDTH = 200;
    public static final int POPUPHEIGHT = 150;

    /**
     * Constructs a new AtmObject with the given parameters.
     *
     * @param name           The name of the ATM.
     * @param remainingCash  The amount of cash remaining in the ATM.
     * @param transactionFee The fee for each transaction made at the ATM.
     * @param xCoord      The x-coordinate of the ATM's location on the panel.
     * @param yCoord      The y-coordinate of the ATM's location on the panel.
     * @param panel          The JPanel where the ATM will be rendered.
     *
     * @return the new user
     */
    public AtmObject create(String name, double remainingCash, double transactionFee,
                            int xCoord, int yCoord, JPanel panel) {
        int newX = xCoord;
        int newY = yCoord;
        if (AtmMapView.WIDTH - newX < POPUPWIDTH) {
            newX -= POPUPWIDTH - BUTTONSIZE;
        }
        if (AtmMapView.HEIGHT - newY < POPUPHEIGHT) {
            newY -= POPUPHEIGHT - BUTTONSIZE;
        }
        return new AtmObject(name, remainingCash, transactionFee, newX, newY, panel);
    }
}
