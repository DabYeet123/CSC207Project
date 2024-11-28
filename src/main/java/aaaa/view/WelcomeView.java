package aaaa.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * View class for handling the welcome interface.
 */
public class WelcomeView extends JPanel {
    private static final String VIEWNAME = "welcome";
    private static final int BUTTON_WIDTH = 80;
    private static final int BUTTON_HEIGHT = 25;
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;
    private static final int FONT_SIZE = 24;
    private final JLabel messageLabel = new JLabel();

    public WelcomeView(ViewManager viewManager) {
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        final JLabel titleLabel = new JLabel("Welcome to Crazy Bank!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        final JButton loginButton = new JButton("Log In");
        final JButton signUpButton = new JButton("Sign Up");
        loginButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        signUpButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(loginButton);
        buttonPanel.add(signUpButton);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManager.setState("login");
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManager.setState("signup");
            }
        });
    }

    /**
     * Displays a message to the user.
     *
     * @param message   the message to display
     * @param isSuccess whether the message is a success (green) or error (red)
     */
    public void displayMessage(String message, boolean isSuccess) {
        messageLabel.setText(message);
        if (isSuccess) {
            messageLabel.setForeground(Color.GREEN);
        }
        else {
            messageLabel.setForeground(Color.RED);
        }
    }

    public String getViewName() {
        return VIEWNAME;
    }
}
