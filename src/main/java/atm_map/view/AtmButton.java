package atm_map.view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import atm_map.data_object.AtmFactory;
import atm_map.data_object.AtmObject;
import atm_map.view_maker.PanelMaker;

/**
 * The hoverable button on the atm map screen.
 */
public class AtmButton extends PanelMaker {

    public AtmButton(AtmObject atmObject, int xCord, int yCord, int width, int height, JPanel panel) {
        super(xCord, yCord, width, height, null, Color.red);

        final AtmPopUp popUp = new AtmPopUp(atmObject, xCord, yCord, AtmFactory.POPUPWIDTH, AtmFactory.POPUPHEIGHT);
        panel.add(popUp);
        panel.add(this);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                popUp.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                popUp.setVisible(false);
            }
        });

    }

}
