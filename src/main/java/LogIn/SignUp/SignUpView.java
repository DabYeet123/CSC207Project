package LogIn.SignUp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpView extends JFrame {
    private final JLabel messageLabel = new JLabel();

    public SignUpView(SignUpController controller) {
        setTitle("Sign Up");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel signUpPanel = new JPanel();
        signUpPanel.setLayout(new GridLayout(4, 2));

        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField();
        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton backButton = new JButton("Back");
        JButton signUpButton = new JButton("Sign Up");

        signUpPanel.add(firstNameLabel);
        signUpPanel.add(firstNameField);
        signUpPanel.add(lastNameLabel);
        signUpPanel.add(lastNameField);
        signUpPanel.add(passwordLabel);
        signUpPanel.add(passwordField);
        signUpPanel.add(backButton);
        signUpPanel.add(signUpButton);

        add(signUpPanel);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.goBackToWelcomeView();
            }
        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String password = passwordField.getText();

                boolean success = controller.signUpTriggered(firstName, lastName, password);
                if (success) {
                    controller.onSignUpSuccess(firstName, lastName, password);
                } else {
                    displayMessage("Please fill all the fields.", false);
                }
            }
        });
    }

    public void displayMessage(String message, boolean isSuccess) {
        messageLabel.setText(message);
        messageLabel.setForeground(isSuccess ? Color.GREEN : Color.RED);
    }
}