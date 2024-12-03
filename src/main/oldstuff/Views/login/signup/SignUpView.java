package oldstuff.Views.login.signup;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * View class for handling the signup interface.
 */
public class SignUpView extends JFrame {
    private static final int ROW_COUNT = 4;
    private static final int COLUMN_COUNT = 2;
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;
    private final JLabel messageLabel = new JLabel();

    public SignUpView(SignUpController controller) {
        setTitle("Sign Up");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        final JPanel signUpPanel = new JPanel();
        signUpPanel.setLayout(new GridLayout(ROW_COUNT, COLUMN_COUNT));

        final JLabel firstNameLabel = new JLabel("First Name:");
        final JTextField firstNameField = new JTextField();
        final JLabel lastNameLabel = new JLabel("Last Name:");
        final JTextField lastNameField = new JTextField();
        final JLabel passwordLabel = new JLabel("Password:");
        final JPasswordField passwordField = new JPasswordField();
        final JButton backButton = new JButton("Back");
        final JButton signUpButton = new JButton("Sign Up");

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
                final String firstName = firstNameField.getText();
                final String lastName = lastNameField.getText();
                final String password = passwordField.getText();

                final boolean success = controller.signUpTriggered(firstName, lastName, password);
                if (success) {
                    controller.onSignUpSuccess(firstName, lastName, password);
                }
                else {
                    displayMessage("Please fill all the fields.", false);
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
