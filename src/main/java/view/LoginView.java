package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

/**
 * View class for handling the login interface.
 */
public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;

    private final String viewName = "login";
    private final LoginViewModel loginViewModel;
    private final LoginController loginController;

    private final JTextField userIdInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    private final JButton logIn;
    private final JButton cancel;

    public LoginView(LoginViewModel loginViewModel, LoginController controller) {
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.loginController = controller;
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Login Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel userIdInfo = new LabelTextPanel(
                new JLabel("UserID"), userIdInputField);
        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        final JPanel buttons = new JPanel();
        logIn = new JButton("Log in");
        buttons.add(logIn);
        cancel = new JButton("Cancel");
        buttons.add(cancel);

        logIn.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    if (event.getSource().equals(logIn)) {
                        final LoginState currentState = loginViewModel.getState();
                        loginController.execute(
                                currentState.getUserId(),
                                currentState.getPassword()
                        );
                    }
                }
            }
        );

        cancel.addActionListener(this);

        userIdInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setUserId(Integer.parseInt(userIdInputField.getText()));
                loginViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setPassword(new String(passwordInputField.getPassword()));
                loginViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        this.add(title);
        this.add(userIdInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
        if ("Cancel".equals(evt.getActionCommand())) {
            loginController.switchToWelcomeView();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
        usernameErrorField.setText(state.getLoginError());
    }

    private void setFields(LoginState state) {
        userIdInputField.setText(String.valueOf(state.getUserId()));
    }

    public String getViewName() {
        return viewName;
    }

}
