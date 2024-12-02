
package Views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PanelMaker extends JPanel {

    public PanelMaker(int x,  int y, int width, int height, LayoutManager layout) {
        super();
        setBounds(x, y, width, height);
        setLayout(layout); //Can customize position of panels

        Border outerBorder = BorderFactory.createLineBorder(Color.BLUE, 3); // Main border
        Border innerPadding = BorderFactory.createEmptyBorder(3, 3, 3, 3); // Padding inside
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerPadding));
    }
}
