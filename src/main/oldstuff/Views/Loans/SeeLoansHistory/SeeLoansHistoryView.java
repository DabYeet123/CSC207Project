package oldstuff.Views.Loans.SeeLoansHistory;

import oldstuff.Views.userdataobject.UserObject;
import oldstuff.Views.Loans.DataObject.LoansObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SeeLoansHistoryView extends JFrame {
    UserObject user;

    public SeeLoansHistoryView(SeeLoansHistoryController controller) {
        this.user = controller.loggedInUser;

        setTitle("Loans History");
        setSize(1050, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Loans History", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Amount ($)", "Start Date", "End Date", "Interest Rate (%)", "Repayment ($)", "oldstuff/Views/Card"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable loanTable = new JTable(tableModel);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        List<LoansObject> loans = controller.loans;
        int loanID = 100000000;
        for (LoansObject loan : loans) {
            String[] rowData = {
                    String.valueOf(loanID),
                    String.format("%.2f", loan.getAmount()),
                    String.valueOf(loan.getStartDate()),
                    String.valueOf(loan.getEndDate()),
                    String.format("%.2f", loan.getRate()),
                    String.format("%.2f", loan.getRepayment()),
                    loan.getCardUsed(),
            };
            tableModel.addRow(rowData);
            ++loanID;
        }

        JScrollPane scrollPane = new JScrollPane(loanTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(80, 25));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.goBackToBaseView();
            }
        });
        backPanel.add(backButton);
        add(backPanel, BorderLayout.SOUTH);
    }
}
