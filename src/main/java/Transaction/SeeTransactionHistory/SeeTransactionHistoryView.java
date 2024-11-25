package Transaction.SeeTransactionHistory;

import DataObjects.UserObject;
import Transaction.DataObject.TransactionObject;
import Transaction.MakeTransaction.MakeTransactionController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SeeTransactionHistoryView extends JFrame {
    UserObject user;

    public SeeTransactionHistoryView(SeeTransactionHistoryController controller) {
        this.user = controller.loggedInUser;

        setTitle("Transaction History");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Transaction History", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Card", "Receiver", "Amount $"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable transactionTable = new JTable(tableModel);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        List<TransactionObject> transactions = controller.transactions;
        for (TransactionObject transaction : transactions) {
            String[] rowData = {
                    String.valueOf(transaction.getTransactionID()),
                    transaction.getCardUsed(),
                    String.valueOf(transaction.getReceiverID()),
                    String.format("%.2f", transaction.getAmount())
            };
            tableModel.addRow(rowData);
        }

        JScrollPane scrollPane = new JScrollPane(transactionTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(80, 25));
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.logOutTriggered();
            }
        });
        logoutPanel.add(logoutButton);
        add(logoutPanel, BorderLayout.SOUTH);
    }
}
