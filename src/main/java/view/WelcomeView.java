package view;

import interface_adapter.signup.SignupController;
import interface_adapter.welcome.WelcomeController;
import interface_adapter.welcome.WelcomeViewModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

/**
 * View class for handling the welcome interface.
 */
public class WelcomeView extends JPanel implements ActionListener, PropertyChangeListener {
    private static final int BUTTON_WIDTH = 80;
    private static final int BUTTON_HEIGHT = 25;
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;
    private static final int FONT_SIZE = 24;
    private final WelcomeController welcomeController;
    private final String viewName = "welcome";
    private final JButton signUp;
    private final JButton login;

    public WelcomeView(WelcomeController controller) {
        this.welcomeController = controller;

        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        final JLabel title = new JLabel(WelcomeViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        final JLabel titleLabel = new JLabel("Welcome to Crazy Bank!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        login = new JButton("Log In");
        login.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        signUp = new JButton("Sign Up");
        signUp.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        final JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        buttons.add(login);
        buttons.add(signUp);

        signUp.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        welcomeController.switchToSignupView();
                    }
                }
        );

        login.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        welcomeController.switchToLoginView();
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public String getViewName() {
        return viewName;
    }
}
