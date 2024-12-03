package atm_map.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import atm_map.adapter.AtmMapController;
import atm_map.view_maker.ButtonMaker;
import atm_map.view_maker.PanelMaker;
import atm_map.view_maker.ViewMaker;

/**
 * The View to show the atm map.
 */
public class AtmMapView extends ViewMaker {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    public static final int SEARCHROOM = 50;
    public static final Rectangle SEARCHAREA = new Rectangle(250, 10, 100, 30);
    public static final Rectangle BACKBUTTON = new Rectangle(WIDTH - 120, HEIGHT - 25, 85, 25);
    private JPanel panel;

    public AtmMapView(AtmMapController controller) {
        super("ATM MAP", WIDTH, HEIGHT + SEARCHROOM);

        panel = new PanelMaker(0, 0, WIDTH, HEIGHT, null, Color.black);
        setPanel(panel);
        add(panel);

        final JPanel buttonPanel = new PanelMaker(0, HEIGHT, WIDTH, SEARCHROOM, new GridLayout(1, 3), Color.black);
        final JTextField input = new JTextField(1);
        input.setBounds(SEARCHAREA);
        final JButton submit = new JButton("Enter");
        submit.addActionListener((ActionEvent event) -> {
            submitFunction(input, controller);
        });
        buttonPanel.add(input);
        buttonPanel.add(submit);
        add(buttonPanel);
        final JButton backButton = new ButtonMaker("Back",
                BACKBUTTON.x, BACKBUTTON.y, BACKBUTTON.width, BACKBUTTON.height) {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.back();
            }
        };
        buttonPanel.add(backButton);

    }

    /**
     * Updates the view to show the new map.
     * @param newPanel the new panel to be displayed
     */
    public void updateView(JPanel newPanel) {
        remove(getPanel());
        setPanel(newPanel);
        add(getPanel());
        revalidate();
        repaint();
    }

    /**
     * Updates the map to show the new map given the typed address.
     * @param input the address for the map
     * @param controller the controller that manages all the functionality
     */
    public void submitFunction(JTextField input, AtmMapController controller) {
        if (!input.getText().isEmpty()) {
            if (controller.checkValidity(input.getText())) {
                JOptionPane.showMessageDialog(
                        this,
                        "Address not found!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );
            }
            else {
                controller.generatePanel(input.getText());
            }
        }
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
