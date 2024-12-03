package transaction.seeTransactionHistory;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import transaction.dataObject.TransactionObject;
import userdataobject.UserObject;

/**
 * View for displaying the transaction history of a user. This class provides the user interface
 * to show a table with transaction details, including transaction ID, card used, receiver ID,
 * and amount. It also provides an option to navigate back to the previous screen.
 */
public class SeeTransactionHistoryView extends JFrame {
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;
    private static final int BUTTON_WIDTH = 80;
    private static final int BUTTON_HEIGHT = 25;
    private static final int FONT_SIZE = 20;
    private static final String FORMAT = "%.2f";
    private UserObject user;

    public SeeTransactionHistoryView(SeeTransactionHistoryController controller) {
        this.user = controller.getLoggedInUser();

        setTitle("Transaction History");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        final JLabel titleLabel = new JLabel("Transaction History", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        add(titleLabel, BorderLayout.NORTH);

        final String[] columnNames = {"ID", "card", "Receiver", "Amount $"};
        final DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        final JTable transactionTable = new JTable(tableModel);

        final List<TransactionObject> transactions = controller.getTransactions();
        for (TransactionObject transaction : transactions) {
            final String[] rowData = {
                    String.valueOf(transaction.getTransactionID()),
                    transaction.getCardUsed(),
                    String.valueOf(transaction.getReceiverID()),
                    String.format(FORMAT, transaction.getAmount()),
            };
            tableModel.addRow(rowData);
        }

        final JScrollPane scrollPane = new JScrollPane(transactionTable);
        add(scrollPane, BorderLayout.CENTER);

        final JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        final JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.backTriggered();
            }
        });
        logoutPanel.add(backButton);
        add(logoutPanel, BorderLayout.SOUTH);
    }
}
