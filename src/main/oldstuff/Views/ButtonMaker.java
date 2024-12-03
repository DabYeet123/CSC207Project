package oldstuff.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class ButtonMaker extends JButton implements ActionListener {

    /**
     * Creates a JButton Object with the specified parameters.
     *
     * @param description the title of the JButton
     * @param textColor the author of the JButton
     * @param backgroundColor the price of the JButton
     * @param x the amount of shift in the x direction from the top left of the container
     * @param y the amount of shift in the y direction from the top left of the container
     * @param width the width of the JButton
     * @param height the height of the JButton
     */
    public ButtonMaker(String description, Color textColor, Color backgroundColor,
                       int x, int y, int width, int height) {
        super(description);
        if(textColor != null) setForeground(textColor); // Sets Text color
        if(textColor != null) setBackground(backgroundColor); // Sets Background color
        setBounds(x, y, width, height);

        this.addActionListener(this); //Adds the action when pressed ot the Listener
    }

    /**
     * Creates a JButton Object with the specified parameters but the color is default
     *
     * @param description the title of the JButton
     * @param textColor the author of the JButton
     * @param backgroundColor the price of the JButton
     * @param width the width of the JButton
     * @param height the height of the JButton
     */
    public ButtonMaker(String description,Color textColor, Color backgroundColor, int width, int height) {
        super(description);
        if (width != 0 && height != 0) setPreferredSize(new Dimension(width, height));// Sets size of button
        if(textColor != null) setForeground(textColor); // Sets Text color
        if(textColor != null) setBackground(backgroundColor); // Sets Background color

        this.addActionListener(this); //Adds the action when pressed ot the Listener
    }

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
