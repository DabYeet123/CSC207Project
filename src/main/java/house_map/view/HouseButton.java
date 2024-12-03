package house_map.view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import house_map.adapter.HouseMapController;
import house_map.data_object.HouseObject;
import atm_map.view_maker.PanelMaker;

/**
 * The hoverable button on the house map screen.
 */
public class HouseButton extends PanelMaker {

    public static final int HOUSEBUTTONSIZE = 20;

    private final HousePopUp housePopUp;

    public HouseButton(HouseObject houseObject, JPanel panel, HouseMapController controller) {
        super(houseObject.getX(), houseObject.getY(), HOUSEBUTTONSIZE, HOUSEBUTTONSIZE, null, Color.red);

        int x = houseObject.getX();
        int y = houseObject.getY();
        if (HouseMapView.WIDTH - houseObject.getX() < HousePopUp.POPUPWIDTH) {
            x -= HousePopUp.POPUPWIDTH - HOUSEBUTTONSIZE;
        }
        if (HouseMapView.HEIGHT - y < HousePopUp.POPUPHEIGHT) {
            y -= HousePopUp.POPUPHEIGHT - HOUSEBUTTONSIZE;
        }
        this.housePopUp = new HousePopUp(houseObject, x, y, controller);
        panel.add(housePopUp);
        panel.add(this);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                housePopUp.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                housePopUp.setVisible(false);
            }
        });
    }

    public HousePopUp getHousePopUp() {
        return housePopUp;
    }

}
