package loans.applyLoans;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class ApplyLoansView extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static final int ROWS = 5;
    private static final int COLS = 2;
    private static final int H_GAP = 10;
    private static final int V_GAP = 10;

    public ApplyLoansView(ApplyLoansController controller) {
        setTitle("Apply Loans");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        final JPanel loansPanel = new JPanel();
        loansPanel.setLayout(new GridLayout(ROWS, COLS, H_GAP, V_GAP));

        final JLabel amountLabel = new JLabel("Amount ($):");
        final JTextField amountField = new JTextField();
        final JLabel termLabel = new JLabel("Term (Yrs):");
        final JTextField termField = new JTextField();
        final JLabel interestRateLabel = new JLabel("Interest Rate (%):");
        final JTextField interestRateField = new JTextField();
        final JLabel cardLabel = new JLabel("Number of Card for Repayment:");
        final JTextField cardField = new JTextField();
        final JButton confirmButton = new JButton("Confirm");
        final JButton cancelButton = new JButton("Cancel");

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
                    final String cardNumber = cardField.getText();
                    final double amount = Double.parseDouble(amountField.getText());
                    final int term = Integer.parseInt(termField.getText());
                    final double rate = Double.parseDouble(interestRateField.getText());
                    final boolean success = controller.applyLoansTriggered(amount, term, rate, cardNumber);
                    if (success) {
                        controller.onApplyLoansSuccess(amount, term, rate, cardNumber);
                    }
                    else {
                        JOptionPane.showMessageDialog(ApplyLoansView.this,
                                "Please fill all the fields correctly.", "Invalid Selection",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ApplyLoansView.this,
                            "Amount, term and rate must be numbers.\\Card number must be valid.",
                            "Invalid Selection", JOptionPane.WARNING_MESSAGE);
                }
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
