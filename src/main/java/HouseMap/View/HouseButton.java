package HouseMap.View;
import HouseMap.DataObject.HouseObject;
import HouseMap.Adapter.HouseMapController;
import Views.PanelMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HouseButton extends PanelMaker {

    public static final int HOUSEBUTTONWIDTH = 20;
    public static final int HOUSEBUTTONHEIGHT = 20;

    private final HousePopUp housePopUp;

    public HouseButton(HouseObject houseObject, JPanel panel, HouseMapController controller) {
        super(houseObject.getX(), houseObject.getY(), HOUSEBUTTONWIDTH, HOUSEBUTTONHEIGHT, null, Color.red);

        int x = houseObject.getX();
        int y = houseObject.getY();
        if (HouseMapView.WIDTH - houseObject.getX() < HousePopUp.POPUPWIDTH) x -= HousePopUp.POPUPWIDTH - 20;
        if (HouseMapView.HEIGHT - y < HousePopUp.POPUPHEIGHT) y -= HousePopUp.POPUPHEIGHT - 20;
        this.housePopUp = new HousePopUp(houseObject, x, y, controller);
        panel.add(housePopUp);
        panel.add(this);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                housePopUp.setVisible(true); // Show popup when mouse enters
            }

            @Override
            public void mouseExited(MouseEvent e) {
                housePopUp.setVisible(false); // Hide popup when mouse exits
            }
        });
    }

    public HousePopUp getHousePopUp() {
        return housePopUp;
    }

}
