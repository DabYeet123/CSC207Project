package Loans.ApplyLoans;

import userdataobject.UserObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplyLoansView extends JFrame {
    private final JLabel messageLabel = new JLabel();
    UserObject user;

    public ApplyLoansView(ApplyLoansController controller) {
        this.user = controller.loggedInUser;

        setTitle("Apply Loans");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(80, 25)); // Small size
        logoutPanel.add(logoutButton);
        add(logoutPanel, BorderLayout.NORTH);

        JPanel loansPanel = new JPanel();
        loansPanel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel amountLabel = new JLabel("Amount ($):");
        JTextField amountField = new JTextField();
        JLabel termLabel = new JLabel("Term (Yrs):");
        JTextField termField = new JTextField();
        JLabel interestRateLabel = new JLabel("Interest Rate (%):");
        JTextField interestRateField = new JTextField();
        JLabel cardLabel = new JLabel("Number of Card for Repayment:");
        JTextField cardField = new JTextField();
        JButton confirmButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Cancel");

        loansPanel.add(amountLabel);
        loansPanel.add(amountField);
        loansPanel.add(termLabel);
        loansPanel.add(termField);
        loansPanel.add(interestRateLabel);
        loansPanel.add(interestRateField);
        loansPanel.add(cardLabel);
        loansPanel.add(cardField);
        loansPanel.add(confirmButton);
        loansPanel.add(cancelButton);

        add(loansPanel, BorderLayout.CENTER);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String cardNumber = cardField.getText();
                    int userID = user.getUserID();
                    double amount = Double.parseDouble(amountField.getText());
                    int term = Integer.parseInt(termField.getText());
                    double rate = Double.parseDouble(interestRateField.getText());
                    boolean success = controller.applyLoansTriggered(amount, term, rate, cardNumber);
                    if (success) {
                        controller.onApplyLoansSuccess(amount, term, rate, cardNumber);
                    } else {
                        displayMessage("Please fill all the fields.", false);
                    }
                } catch (NumberFormatException ex) {
                    displayMessage("Amount, term and rate must be numbers.\\Card number must be valid.", false);
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.goBackToBaseView();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.logOutTriggered();
            }
        });
    }

    public void displayMessage(String message, boolean isSuccess) {
        messageLabel.setText(message);
        messageLabel.setForeground(isSuccess ? Color.GREEN : Color.RED);
    }
}
