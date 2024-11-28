package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import interface_adapter.login.LoginController;

/**
 * View class for handling the login interface.
 */
public class LoginView extends JPanel {
    private static final String VIEWNAME = "login";
    private static final int ROW_COUNT = 3;
    private static final int COLUMN_COUNT = 2;
    private static final int HORIZONTAL_GAP = 10;
    private static final int VERTICAL_GAP = 10;
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;
    private final JLabel messageLabel = new JLabel();

    public LoginView(ViewManager viewManager) {
        final LoginController loginController = viewManager.getLoginController();
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        final JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(ROW_COUNT, COLUMN_COUNT, HORIZONTAL_GAP, VERTICAL_GAP));

        final JLabel userIDLabel = new JLabel("User ID:");
        final JTextField userIDField = new JTextField();
        final JLabel passwordLabel = new JLabel("Password:");
        final JPasswordField passwordField = new JPasswordField();
        final JButton backButton = new JButton("Back");
        final JButton logInButton = new JButton("Log In");

        loginPanel.add(userIDLabel);
        loginPanel.add(userIDField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(backButton);
        loginPanel.add(logInButton);

        add(loginPanel);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManager.setState("welcome");
            }
        });

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    final int userID = Integer.parseInt(userIDField.getText().trim());
                    final String password = new String(passwordField.getPassword());
                    loginController.execute(userID, password);
                }
                catch (NumberFormatException ex) {
                    displayMessage("User ID must be a number.", false);
                }
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
