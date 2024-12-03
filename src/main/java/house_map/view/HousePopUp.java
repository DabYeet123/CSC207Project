package house_map.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import house_map.adapter.HouseMapController;
import house_map.data_object.HouseObject;
import atm_map.view_maker.ButtonMaker;
import atm_map.view_maker.LabelMaker;
import atm_map.view_maker.PanelMaker;

/**
 * The popup that shows all the information about the atm.
 */
public class HousePopUp extends PanelMaker {

    public static final int POPUPWIDTH = 200;
    public static final int POPUPHEIGHT = 150;

    private LabelMaker owner;

    public HousePopUp(HouseObject houseObject, int xCoord, int yCoord, HouseMapController controller) {
        super(xCoord, yCoord, POPUPWIDTH, POPUPHEIGHT, new GridLayout(2, 1), Color.black);
        final PanelMaker labels = new PanelMaker(houseObject.getX(), houseObject.getY(),
                POPUPWIDTH, POPUPHEIGHT / 2, new GridLayout(4, 1), Color.black);
        final LabelMaker name = new LabelMaker("Name:" + houseObject.getName(), null);
        final LabelMaker address = new LabelMaker("Address: " + houseObject.getAddress(), null);
        final LabelMaker price = new LabelMaker("Price :" + houseObject.getPrice(), null);
        owner = new LabelMaker("Owner: " + houseObject.getOwner(), null);
        labels.add(name);
        labels.add(address);
        labels.add(price);
        labels.add(owner);

        this.add(labels);

        final PanelMaker buttons = new PanelMaker(0, 0, POPUPWIDTH, POPUPHEIGHT / 2, new GridLayout(1, 2), Color.black);
        final ButtonMaker purchase = new ButtonMaker("Purchase") {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.execute(houseObject, 0, true);
            }
        };
        final ButtonMaker deposit = new ButtonMaker("Sell") {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.execute(houseObject, 0, false);
            }
        };

        buttons.add(purchase);
        buttons.add(deposit);
        this.add(buttons);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {

                final Rectangle bounds = getBounds();
                final int x = e.getPoint().x + bounds.x;
                final int y = e.getPoint().y + bounds.y;
                if (bounds.contains(x, y)) {
                    setVisible(true);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                final Rectangle bounds = getBounds();
                final int x = e.getPoint().x + bounds.x;
                final int y = e.getPoint().y + bounds.y;
                if (!bounds.contains(x, y)) {
                    setVisible(false);
                }
            }
        });

        this.setVisible(false);

    }

    /**
     * Updates the owner in the map view.
     * @param text the name to be updated
     */
    public void updateView(String text) {
        owner.setText("Owner: " + text);
    }

}
