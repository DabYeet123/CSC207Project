package atm_map.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import atm_map.data_object.AtmObject;
import atm_map.view_maker.ButtonMaker;
import atm_map.view_maker.LabelMaker;
import atm_map.view_maker.PanelMaker;

import javax.swing.*;

/**
 * The popup that shows all the information about the atm.
 */
public class AtmPopUp extends PanelMaker {

    public AtmPopUp(AtmObject atmObject, int xCoord, int yCoord, int width, int height) {
        super(xCoord, yCoord, width, height, new GridLayout(2, 1), Color.black);
        final PanelMaker labels = new PanelMaker(xCoord, yCoord, width, height / 2, new GridLayout(3, 1), Color.black);
        final LabelMaker name = new LabelMaker(atmObject.getName(), null);
        final LabelMaker cash = new LabelMaker("Remaining Cash :" + atmObject.getRemainingCash(), null);
        final LabelMaker fee = new LabelMaker("TransactionFee: " + atmObject.getTransactionFee(), null);
        labels.add(name);
        labels.add(cash);
        labels.add(fee);

        this.add(labels);

        final PanelMaker buttons = new PanelMaker(0, 0, width, height / 2, new GridLayout(1, 2), Color.black);
        final ButtonMaker withdraw = new ButtonMaker("Withdraw") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please go to the location to perform such actions!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        };
        final ButtonMaker deposit = new ButtonMaker("Deposit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please go to the location to perform such actions!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        };

        buttons.add(deposit);
        buttons.add(withdraw);
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

}
