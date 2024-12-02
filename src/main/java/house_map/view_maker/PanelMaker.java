package house_map.view_maker;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PanelMaker extends JPanel {

    public PanelMaker(int x,  int y, int width, int height, LayoutManager layout, Color color) {
        super();
        setBounds(x, y, width, height);
        setLayout(layout); //Can customize position of panels

        if (color != null) {
            Border outerBorder = BorderFactory.createLineBorder(color, 3); // Main border
            Border innerPadding = BorderFactory.createEmptyBorder(3, 3, 3, 3); // Padding inside
            setBorder(BorderFactory.createCompoundBorder(outerBorder, innerPadding));
        }

    }
}
