package House.DataObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Views.LabelMaker;
import Views.PanelMaker;
import Views.ButtonMaker;

public class HousePopUp extends PanelMaker {


    public HousePopUp(HouseObject houseObject, int x, int y, int width, int height, HouseController controller) {
        super(x, y, width, height, new GridLayout(2, 1));
        JPanel labels = new PanelMaker(x, y, width, height / 2, new GridLayout(4, 1));
        JLabel name = new LabelMaker(houseObject.getName(), null);
        JLabel address = new LabelMaker("Address: " + houseObject.getAddress(), null);
        JLabel price = new LabelMaker("Price :" + houseObject.getPrice(), null);
        JLabel owner = new LabelMaker("Owner: " + houseObject.getOwner(), null);
        labels.add(name);
        labels.add(address);
        labels.add(price);
        labels.add(owner);

        this.add(labels);

        JPanel buttons = new PanelMaker(0, 0, width, height / 2, new GridLayout(1, 2));
        JButton purchase = new ButtonMaker("Purchase") {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.buyHouse(houseObject.getAddress());
                owner.setText("Owner: " + controller.getUser().getFirstName());
            }
        };
        JButton deposit = new ButtonMaker("Sell") {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.sellHouse(houseObject.getAddress());
                owner.setText("Owner: ");
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
}
