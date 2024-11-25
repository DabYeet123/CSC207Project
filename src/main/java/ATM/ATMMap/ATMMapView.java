package ATM.ATMMap;

import DataObjects.UserObject;
import Views.PanelMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class ATMMapView extends JFrame {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private JPanel panel;
    private UserObject user;

    public ATMMapView(ATMMapController controller) throws Exception {
        super("ATM Locations");

        this.user = controller.loggedInUser;
        pack();
        Insets insets = getInsets();
        int frameWidth = WIDTH + insets.left + insets.right;
        int frameHeight = HEIGHT + insets.top + insets.bottom;

        setSize(frameWidth, frameHeight + 50); // Set the size of the frame
        setLayout(null);
        JPanel panel = controller.generatePanel("University of Toronto");
        setPanel(panel);
        add(panel);

        JPanel buttonPanel = new PanelMaker(0, 600, 600, 50, new GridLayout(1,2));
        JTextField input = new JTextField(1);
        input.setBounds(250, 10, 100, 30);
        JButton submit = new JButton("Enter");
        submit.addActionListener((ActionEvent e) -> {
            try {
                if(!input.getText().isEmpty()) {
                    System.out.println(input.getText());

                    remove(getPanel());
                    setPanel(controller.generatePanel(input.getText()));
                    add(getPanel());
                    revalidate();
                    repaint();
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }                           // Repaint to reflect changes
        });
        buttonPanel.add(input);
        buttonPanel.add(submit);
        this.add(buttonPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close Application on clicking X
        setLocationRelativeTo(null); //Puts the Menu in the center
    }

    public JPanel getPanel() {
        return panel;
    }
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
