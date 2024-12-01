package transaction.makeTransaction;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import userdataobject.UserObject;

/**
 * View for making a transaction. This class provides the user interface for processing transactions
 * including selecting a card, entering a receiver's user ID, and specifying the transaction amount.
 */
public class MakeTransactionView extends JFrame {

    private static final int ROW_COUNT = 4;
    private static final int COLUMN_COUNT = 2;
    private static final int HORIZONTAL_GAP = 10;
    private static final int VERTICAL_GAP = 10;
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;
    private static final int BUTTON_WIDTH = 80;
    private static final int BUTTON_HEIGHT = 25;

    private final JLabel messageLabel = new JLabel();
    private final UserObject user;

    public MakeTransactionView(MakeTransactionController controller) {
        this.user = controller.getLoggedInUser();
        setupFrame();
        final JPanel transactionPanel = createTransactionPanel(controller);
        add(transactionPanel, BorderLayout.CENTER);
        add(createBackButtonPanel(controller), BorderLayout.SOUTH);
    }

    private void setupFrame() {
        setTitle("Make Transaction");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private JPanel createTransactionPanel(MakeTransactionController controller) {
        final JPanel transactionPanel = new JPanel();
        transactionPanel.setLayout(new GridLayout(ROW_COUNT, COLUMN_COUNT, HORIZONTAL_GAP, VERTICAL_GAP));

        final JLabel cardLabel = new JLabel("Card:");
        final String[] cardOptions = controller.getCards();
        final JComboBox<String> cardDropdown = new JComboBox<>(cardOptions);

        final JLabel receiverIDLabel = new JLabel("Receiver UserID:");
        final JTextField receiverIDField = new JTextField();
        final JLabel amountLabel = new JLabel("Amount:");
        final JTextField amountField = new JTextField();
        final JButton processButton = new JButton("Process Transaction");

        transactionPanel.add(cardLabel);
        transactionPanel.add(cardDropdown);
        transactionPanel.add(receiverIDLabel);
        transactionPanel.add(receiverIDField);
        transactionPanel.add(amountLabel);
        transactionPanel.add(amountField);
        transactionPanel.add(new JLabel());
        transactionPanel.add(processButton);

        processButton.addActionListener(event
                -> processTransaction(controller, cardDropdown, receiverIDField, amountField));
        return transactionPanel;
    }

    private JPanel createBackButtonPanel(MakeTransactionController controller) {
        final JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        final JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        backButton.addActionListener(event -> controller.backTriggered());
        panel.add(backButton);
        return panel;
    }

    private void processTransaction(MakeTransactionController controller, JComboBox<String> cardDropdown,
                                    JTextField receiverIDField, JTextField amountField) {
        try {
            final int receiverID = Integer.parseInt(receiverIDField.getText());
            final int amount = Integer.parseInt(amountField.getText());
            if (controller.makeTransactionTriggered((String) cardDropdown.getSelectedItem(), receiverID, amount)) {
                controller.onMakeTransactonSuccess((String) cardDropdown.getSelectedItem(), receiverID, amount);
            }
            else {
                displayMessage("Please fill all the fields.", false);
            }
        }
        catch (NumberFormatException ex) {
            displayMessage("UserID and Amount must be numbers.", false);
        }
    }

    /**
     * Displays a message to the user. The message is shown in green for success
     * or red for an error.
     *
     * @param message the message to display
     * @param isSuccess true if the message is a success (green) or false for error (red)
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
