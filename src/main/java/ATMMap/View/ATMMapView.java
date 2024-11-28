package ATMMap.View;

import ATMMap.Adapter.ATMMapController;
import Views.ButtonMaker;
import Views.PanelMaker;
import Views.ViewMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class ATMMapView extends ViewMaker {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private JPanel panel;

    public ATMMapView(ATMMapController controller){
        super("ATM MAP", WIDTH, HEIGHT + 50);

        //JPanel panel = controller.generatePanel("University of Toronto");
        JPanel panel = new PanelMaker(0, 0, WIDTH, HEIGHT, null, Color.black);
        setPanel(panel);
        add(panel);

        JPanel buttonPanel = new PanelMaker(0, HEIGHT, WIDTH, 50, new GridLayout(1,3), Color.black);
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

        JButton backButton = new ButtonMaker("Back",
                WIDTH - 120, HEIGHT - 25, 85, 25) {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.back();
            }
        };
        buttonPanel.add(backButton);

    }

    public void updateView(JPanel panel) {
        setPanel(panel);
    }

    public JPanel getPanel() {
        return panel;
    }
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
