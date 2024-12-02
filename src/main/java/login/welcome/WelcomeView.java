package login.welcome;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * View class for handling the welcome interface.
 */
public class WelcomeView extends JFrame {
    private static final int BUTTON_WIDTH = 80;
    private static final int BUTTON_HEIGHT = 25;
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;
    private static final int FONT_SIZE = 24;

    public WelcomeView(WelcomeController controller) {
        setTitle("Welcome to Crazy Bank");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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
                controller.logInTriggered();
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.signUpTriggered();
            }
        });
    }
}
