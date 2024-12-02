package login.login;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * View class for handling the login interface.
 */
public class LogInView extends JFrame {
    private static final int ROW_COUNT = 3;
    private static final int COLUMN_COUNT = 2;
    private static final int HORIZONTAL_GAP = 10;
    private static final int VERTICAL_GAP = 10;
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;
    private final JLabel messageLabel = new JLabel();

    public LogInView(LogInController controller) {
        setTitle("Log In");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

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

        final JFrame view = this;

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.goBackToWelcomeView();
            }
        });

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    final int userID = Integer.parseInt(userIDField.getText().trim());
                    final String password = new String(passwordField.getPassword());

                    final boolean success = controller.logInTriggered(userID, password);
                    if (success) {
                        controller.onLoginSuccess(userID);
                    }
                    else {
                        JOptionPane.showMessageDialog(
                                view,
                                "Incorrect UserID or Password",
                                "Warning",
                                JOptionPane.WARNING_MESSAGE
                        );
                    }
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            view,
                            "User ID must be a number.",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE
                    );
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
}
