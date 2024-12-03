package oldstuff.Views.ATM.ATMMap;

import oldstuff.Views.userdataobject.UserObject;
import oldstuff.Views.PanelMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class ATMMapView extends PanelMaker {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private JPanel panel;
    private UserObject user;

    public ATMMapView(ATMMapController controller){
        super(0, 0, WIDTH, HEIGHT + 50, null);

        this.user = controller.loggedInUser;

        //JPanel panel = controller.generatePanel("University of Toronto");
        JPanel panel = new PanelMaker(0, 0, WIDTH, HEIGHT, null);
        setPanel(panel);
        add(panel);

        JPanel buttonPanel = new PanelMaker(0, HEIGHT, WIDTH, 50, new GridLayout(1,2));
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
        add(buttonPanel);
    }

    public JPanel getPanel() {
        return panel;
    }
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
