package loans.applyLoans;

import DataObjects.UserObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplyLoansView extends JFrame {
    UserObject user;

    public ApplyLoansView(ApplyLoansController controller) {
        this.user = controller.loggedInUser;

        setTitle("Apply Loans");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

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

        confirmButton.addActionListener(e -> {
            try {
                final String cardNumber = cardField.getText();
                final double amount = Double.parseDouble(amountField.getText());
                final int term = Integer.parseInt(termField.getText());
                final double rate = Double.parseDouble(interestRateField.getText());
                final boolean success = controller.applyLoansTriggered(amount, term, rate, cardNumber);
                if (success) {
                    controller.onApplyLoansSuccess(amount, term, rate, cardNumber);
                }
                else {
                    JOptionPane.showMessageDialog(this, "Please fill all the fields correctly.", "Invalid Selection", JOptionPane.WARNING_MESSAGE);
                }
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Amount, term and rate must be numbers.\\Card number must be valid.", "Invalid Selection", JOptionPane.WARNING_MESSAGE);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.goBackToBaseView();
            }
        });
    }
}
