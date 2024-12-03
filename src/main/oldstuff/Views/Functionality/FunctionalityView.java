package oldstuff.Views.Functionality;

import oldstuff.Views.ButtonMaker;
import oldstuff.Views.PanelMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FunctionalityView extends JFrame {


    public FunctionalityView(JPanel panel, FunctionalityController controller) {
        super();
        pack();
        Insets insets = getInsets();
        int frameWidth = panel.getWidth()+ insets.left + insets.right;
        int frameHeight = panel.getHeight() + insets.top + insets.bottom;
        setSize(frameWidth, frameHeight + 50); // Set the size of the frame
        add(panel);

        JPanel buttonPanel = new PanelMaker(0, frameHeight, frameWidth, 50, null);
        JButton backButton = new ButtonMaker("Back",
                frameWidth - 120, frameHeight - 25, 85, 25) {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.back();
            }
        };
        buttonPanel.add(backButton);
        add(buttonPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close Application on clicking X
        setLocationRelativeTo(null); //Puts the Menu in the center
    }
}
