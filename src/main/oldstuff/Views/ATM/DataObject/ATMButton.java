package oldstuff.Views.ATM.DataObject;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import oldstuff.Views.ATM.ATMMap.ATMMapView;
import oldstuff.Views.PanelMaker;

public class ATMButton extends PanelMaker {


    public static final int POPUPWIDTH = 200;
    public static final int POPUPHEIGHT = 150;
    private final ATMPopUp popUp;

    public ATMButton(ATMObject atmObject, int x, int y, int width, int height, JPanel panel) {
        super(x, y, width, height, null);
        if (ATMMapView.WIDTH - x < POPUPWIDTH) x -= POPUPWIDTH - width;
        if (ATMMapView.HEIGHT - y < POPUPHEIGHT) y -= POPUPHEIGHT - height;

        this.popUp = new ATMPopUp(atmObject, x, y , POPUPWIDTH, POPUPHEIGHT);
        panel.add(popUp);
        panel.add(this);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                popUp.setVisible(true); // Show popup when mouse enters
            }

            @Override
            public void mouseExited(MouseEvent e) {
                popUp.setVisible(false); // Hide popup when mouse exits
            }
        });

    }

}
