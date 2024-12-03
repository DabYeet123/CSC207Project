package seetransactions;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import entity.Transaction;
import entity.User;
import seetransactions.interface_adapter.SeeTransactionsController;
import seetransactions.interface_adapter.SeeTransactionsState;
import seetransactions.interface_adapter.SeeTransactionsViewModel;

/**
 * The View for when the user is logged into the program.
 */
public class SeeTransactionsView extends JPanel implements ActionListener, PropertyChangeListener {
    private static final int ROW_COUNT = 4;
    private static final int COLUMN_COUNT = 2;
    private static final int HORIZONTAL_GAP = 10;
    private static final int VERTICAL_GAP = 10;
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;
    private static final String FORMAT = "%.2f";

    private final String viewName = "seetransactions";
    private final SeeTransactionsViewModel seeTransactionsViewModel;
    private final SeeTransactionsController seeTransactionsController;

    private User loggedinUser;
    private final JButton cancel;
    private final DefaultTableModel tableModel;
    private JTable transactionTable;
    private JScrollPane scrollPane;

    public SeeTransactionsView(SeeTransactionsController seeTransactionsController,
                               SeeTransactionsViewModel seeTransactionsViewModel) {
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.seeTransactionsController = seeTransactionsController;
        this.seeTransactionsViewModel = seeTransactionsViewModel;
        seeTransactionsViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("See Transactions in Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final String[] columnNames = {"ID", "Card", "Receiver", "Amount $"};
        tableModel = new DefaultTableModel(columnNames, 0);
        transactionTable = new JTable(tableModel);

        scrollPane = new JScrollPane(transactionTable);
        add(scrollPane, BorderLayout.CENTER);

        final JPanel buttons = new JPanel();
        cancel = new JButton("Cancel");
        buttons.add(cancel);

        cancel.addActionListener(this);

        this.add(title);
        this.add(scrollPane);
        this.add(buttons, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
        if ("Cancel".equals(evt.getActionCommand())) {
            seeTransactionsController.switchToLoggedinView();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SeeTransactionsState state = (SeeTransactionsState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(SeeTransactionsState state) {
        final List<Transaction> transactions = state.getTransactions();
        for (Transaction transaction : transactions) {
            final String[] rowData = {
                    String.valueOf(transaction.getTransactionID()),
                    transaction.getCardUsed(),
                    String.valueOf(transaction.getReceiverID()),
                    String.format(FORMAT, transaction.getAmount()),
            };
            this.tableModel.addRow(rowData);
        }
        this.scrollPane = new JScrollPane(transactionTable);
    }

    public String getViewName() {
        return viewName;
    }
}