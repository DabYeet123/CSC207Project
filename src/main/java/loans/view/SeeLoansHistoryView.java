package loans.view;

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

import org.jetbrains.annotations.NotNull;

import loans.adapter.SeeLoansHistoryController;
import loans.dataObject.LoansObject;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class SeeLoansHistoryView extends JFrame {
    private static final int WIDTH = 1050;
    private static final int HEIGHT = 400;
    private static final int BUTTON_WIDTH = 80;
    private static final int BUTTON_HEIGHT = 25;
    private static final int FONT_SIZE = 20;
    private static final int LOAN_ID = 100000000;
    private static final String DECIMAL = "%.2f";

    /**
     * Constructs the SeeLoansHistoryView to display loan history.
     *
     * @param controller The controller that manages actions within this view.
     */
    public SeeLoansHistoryView(SeeLoansHistoryController controller) {
        setTitle("Loans History");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        final JLabel titleLabel = new JLabel("Loans History", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        add(titleLabel, BorderLayout.NORTH);

        final JScrollPane scrollPane = getLoansHistory(controller);
        add(scrollPane, BorderLayout.CENTER);

        final JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        final JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.goBackToBaseView();
            }
        });
        backPanel.add(backButton);
        add(backPanel, BorderLayout.SOUTH);
    }

    /**
     * Creates and returns a JScrollPane containing the loans' history.
     *
     * @param controller The controller to provide the list of loans.
     * @return The JScrollPane with the loans history table.
     */
    @NotNull
    private static JScrollPane getLoansHistory(SeeLoansHistoryController controller) {
        final String[] columnNames = {"ID", "Amount ($)", "Start Date", "End Date", "Interest Rate (%)",
            "Repayment ($)", "Card"};
        final DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        final JTable loanTable = new JTable(tableModel);

        final List<LoansObject> loans = controller.getLoans();
        int loanID = LOAN_ID;
        for (LoansObject loan : loans) {
            final String[] rowData = {
                    String.valueOf(loanID),
                    String.format(DECIMAL, loan.getAmount()),
                    String.valueOf(loan.getStartDate()),
                    String.valueOf(loan.getEndDate()),
                    String.format(DECIMAL, loan.getRate()),
                    String.format(DECIMAL, loan.getRepayment()),
                    controller.showCardInformation(loan.getCardUsed()),
            };
            tableModel.addRow(rowData);
            ++loanID;
        }
        return new JScrollPane(loanTable);
    }
}
