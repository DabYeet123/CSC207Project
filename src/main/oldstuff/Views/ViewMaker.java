package oldstuff.Views;

import javax.swing.*;
import java.awt.*;

public abstract class ViewMaker extends JFrame {

    public ViewMaker(String name, int width, int height) {
        super(name);

        // Calculate the total frame size needed for the desired content size
        pack();
        Insets insets = getInsets();
        int frameWidth = width + insets.left + insets.right;
        int frameHeight = height + insets.top + insets.bottom;

        setSize(frameWidth, frameHeight); // Set the size of the frame

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close Application on clicking X
        setLocationRelativeTo(null); //Puts the Menu in the center
        setLayout(null); //Can customize position of panels
        setResizable(false); //Disable RESIZING
    }
}