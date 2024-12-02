package atm_map.view_maker;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class ButtonMaker extends JButton implements ActionListener {

    /**
     * Creates a JButton Object with the specified parameters but the color is default
     *
     * @param description the title of the JButton
     */
    public ButtonMaker(String description) {
        super(description);

        this.addActionListener(this); //Adds the action when pressed ot the Listener
    }


    /**
     * Creates a JButton Object with the specified parameters.
     *
     * @param description the title of the JButton
     * @param x the amount of shift in the x direction from the top left of the container
     * @param y the amount of shift in the y direction from the top left of the container
     * @param width the width of the JButton
     * @param height the height of the JButton
     */
    public ButtonMaker(String description,
                       int x, int y, int width, int height) {
        super(description);
        setBounds(x, y, width, height);

        this.addActionListener(this); //Adds the action when pressed ot the Listener
    }
}
