package loans.view;

import java.util.List;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cardandexchange.dataObject.Card;
import loans.adapter.ApplyLoansController;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class ApplyLoansView extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static final int ROWS = 5;
    private static final int COLS = 2;
    private static final int H_GAP = 10;
    private static final int V_GAP = 10;

    /**
     * Constructs a new ApplyLoansView for applying for loans.
     *
     * @param controller The controller to manage actions within the view.
     */
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
        final JLabel cardLabel = new JLabel("Card for Repayment:");
        final JComboBox<String> cardComboBox = createCardComboBox(controller);
        final JButton confirmButton = new JButton("Confirm");
        final JButton cancelButton = new JButton("Cancel");

        loansPanel.add(amountLabel);
        loansPanel.add(amountField);
        loansPanel.add(termLabel);
        loansPanel.add(termField);
        loansPanel.add(interestRateLabel);
        loansPanel.add(interestRateField);
        loansPanel.add(cardLabel);
        loansPanel.add(cardComboBox);
        loansPanel.add(confirmButton);
        loansPanel.add(cancelButton);

        add(loansPanel, BorderLayout.CENTER);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirm(cardComboBox, amountField, termField, interestRateField, controller);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.goBackToBaseView();
            }
        });
    }

    private JComboBox<String> createCardComboBox(ApplyLoansController controller) {
        JComboBox<String> cardComboBox = new JComboBox<>(new String[]{"Choose your Card"});
        List<Card> cards = controller.getCards();
        for (Card card : cards) {
            cardComboBox.addItem(card.getId() + " (" + card.getUsage() + ")");
        }
        return cardComboBox;
    }

    /**
     * Handles the confirmation process for applying for a loan.
     *
     * @param cardComboBox     The combo box containing the card for repayment.
     * @param amountField      The text field containing the loan amount.
     * @param termField        The text field containing the loan term in years.
     * @param interestRateField The text field containing the interest rate.
     * @param controller       The controller to manage the loan application process.
     */
    private void confirm(JComboBox<String> cardComboBox, JTextField amountField, JTextField termField,
                         JTextField interestRateField, ApplyLoansController controller) {
        try {
            if (cardComboBox.getSelectedIndex() > 0) {
                final String cardNumber = controller.getCards().get(cardComboBox.getSelectedIndex() - 1).getId();
                final Double amount = (Double) Double.parseDouble(amountField.getText());
                final int term = Integer.parseInt(termField.getText());
                final Double rate = (Double) Double.parseDouble(interestRateField.getText());
                final boolean success = controller.applyLoansTriggered(amount, term, rate);
                if (success) {
                    if (JOptionPane.showConfirmDialog(ApplyLoansView.this,
                            "Are you sure to apply the loan?",
                            "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        controller.onApplyLoansSuccess(amount, term, rate, cardNumber);
                        JOptionPane.showMessageDialog(ApplyLoansView.this,
                                "You have successfully applied the loan.",
                                "Success", JOptionPane.PLAIN_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(ApplyLoansView.this,
                            "Please fill all the fields correctly.", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
            else {
                JOptionPane.showMessageDialog(ApplyLoansView.this,
                        "Please choose a valid Card.", "Invalid Selection",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(ApplyLoansView.this,
                    "Amount, term, and rate must be numbers. Card number must be valid.",
                    "Invalid Selection", JOptionPane.WARNING_MESSAGE);
        }
    }
}
