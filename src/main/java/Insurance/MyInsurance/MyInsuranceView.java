package Insurance.MyInsurance;

import DataObjects.UserObject;
import Insurance.DataObject.InsuranceObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MyInsuranceView extends JFrame {
    UserObject user;

    public MyInsuranceView(MyInsuranceController controller) {
        this.user = controller.loggedInUser;

        setTitle("My Insurance Policies");
        setSize(1050, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("My Insurance Policies", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Type", "Premium ($)", "Start Date", "End Date", "Auto Renew"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable insuranceTable = new JTable(tableModel);

        JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"Choose Insurance Type", "Health", "Vehicle", "Home", "Travel", "Life", "Pet", "Business", "Dental"});
        typeComboBox.addActionListener(e -> {
            String selectedType = (String) typeComboBox.getSelectedItem();
            if (selectedType != null && !selectedType.equals("Choose Insurance Type")) {
                List<InsuranceObject> filteredInsurances = controller.getInsurancesByType(selectedType);
                tableModel.setRowCount(0); // Clear the table
                int insuranceID = 200000000;
                for (InsuranceObject insurance : filteredInsurances) {
                    String[] rowData = {
                            String.valueOf(insuranceID),
                            insurance.getType(),
                            String.format("%.2f", insurance.getPremium()),
                            String.valueOf(insurance.getStartDate()),
                            String.valueOf(insurance.getEndDate()),
                            insurance.isAutoRenew() ? "Yes" : "No",
                    };
                    tableModel.addRow(rowData);
                    ++insuranceID;
                }
            }
        });

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(new JLabel("Filter by Type:"));
        filterPanel.add(typeComboBox);
        add(filterPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(insuranceTable);
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
