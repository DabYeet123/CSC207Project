package HouseMap.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import HouseMap.DataObject.HouseObject;
import HouseMap.Adapter.HouseMapController;
import Views.LabelMaker;
import Views.PanelMaker;
import Views.ButtonMaker;

public class HousePopUp extends PanelMaker {

    public static final int POPUPWIDTH = 200;
    public static final int POPUPHEIGHT = 150;

    private JLabel owner;


    public HousePopUp(HouseObject houseObject, int x, int y, HouseMapController controller) {
        super(x, y, POPUPWIDTH, POPUPHEIGHT, new GridLayout(2, 1), Color.black);
        JPanel labels = new PanelMaker(houseObject.getX(), houseObject.getY(), POPUPWIDTH, POPUPHEIGHT/2,
                new GridLayout(4, 1), Color.black);
        JLabel name = new LabelMaker("Name:" + houseObject.getName(), null);
        JLabel address = new LabelMaker("Address: " + houseObject.getAddress(), null);
        JLabel price = new LabelMaker("Price :" + houseObject.getPrice(), null);
        owner = new LabelMaker("Owner: " + houseObject.getOwner(), null);
        labels.add(name);
        labels.add(address);
        labels.add(price);
        labels.add(owner);

        this.add(labels);

        JPanel buttons = new PanelMaker(0, 0, POPUPWIDTH, POPUPHEIGHT / 2, new GridLayout(1, 2), Color.black);
        JButton purchase = new ButtonMaker("Purchase") {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.execute(houseObject, 0, true);
            }
        };
        JButton deposit = new ButtonMaker("Sell") {
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

                Rectangle bounds = getBounds();
                int x = e.getPoint().x + bounds.x;
                int y = e.getPoint().y + bounds.y;
                if (bounds.contains(x, y)) {
                    setVisible(true); // Hide popup when mouse exits
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Rectangle bounds = getBounds();
                int x = e.getPoint().x + bounds.x;
                int y = e.getPoint().y + bounds.y;
                if (!bounds.contains(x, y)) {
                    setVisible(false); // Hide popup when mouse exits
                }
            }
        });

        this.setVisible(false);

    }

    public void updateView(String text) {
        setOwner(text);
    }

    public void setOwner(String text){
        owner.setText("Owner: " + text);
    }
}
