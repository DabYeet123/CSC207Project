package LogIn.LogIn;

import LogIn.Welcome.WelcomeController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInView extends JFrame {
    private final JLabel messageLabel = new JLabel();

    public LogInView(LogInController controller) {
        setTitle("Log In");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel userIDLabel = new JLabel("User ID:");
        JTextField userIDField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton backButton = new JButton("Back");
        JButton logInButton = new JButton("Log In");

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
                controller.goBackToWelcomeView();
            }
        });
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int userID = Integer.parseInt(userIDField.getText().trim());
                    String password = new String(passwordField.getPassword());

                    boolean success = controller.logInTriggered(userID, password);
                    if (success) {
                        controller.onLoginSuccess(userID);
                    } else {
                        displayMessage("Invalid User ID or Password.", false);
                    }
                } catch (NumberFormatException ex) {
                    displayMessage("User ID must be a number.", false);
                }
            }
        });
    }

    public void displayMessage(String message, boolean isSuccess) {
        messageLabel.setText(message);
        messageLabel.setForeground(isSuccess ? Color.GREEN : Color.RED);
    }
}
