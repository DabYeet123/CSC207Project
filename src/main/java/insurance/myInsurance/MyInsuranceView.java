package insurance.myInsurance;

import DataObjects.UserObject;
import insurance.dataObject.UserInsuranceObject;

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
        setSize(1200, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("My Insurance Policies", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"Type", "Name", "ID", "Premium ($)", "Start Date", "End Date", "Auto Renew", "Card"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable insuranceTable = new JTable(tableModel);

        JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"Choose Insurance Type", "Health", "Vehicle", "Home", "Travel", "Life", "Pet", "Business", "Dental"});
        updateTable(tableModel, controller.getInsurances());
        typeComboBox.addActionListener(e -> {
            String selectedType = (String) typeComboBox.getSelectedItem();
            final List<UserInsuranceObject> filteredInsurances;
            if (selectedType != null && !"Choose Insurance Type".equals(selectedType)) {
                filteredInsurances = controller.getInsurancesByType(selectedType);
            }
            else {
                filteredInsurances = controller.getInsurances();
            }
            updateTable(tableModel, filteredInsurances);
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

    private static void updateTable(DefaultTableModel tableModel, List<UserInsuranceObject> filteredInsurances) {
        tableModel.setRowCount(0);
        for (UserInsuranceObject insurance : filteredInsurances) {
            final String endDate;
            final String autoRenew;
            if (insurance.isAutoRenew()) {
                autoRenew = "Yes";
                endDate = "/";
            }
            else {
                endDate = String.valueOf(insurance.getEndDate());
                autoRenew = "No";
            }
            final String[] rowData = {
                insurance.getInsurance().getType(),
                insurance.getInsurance().getInsuranceName(),
                String.valueOf(insurance.getInsurance().getInsuranceID()),
                String.format("%.2f", insurance.getInsurance().getPremium()),
                String.valueOf(insurance.getStartDate()),
                endDate,
                autoRenew,
                insurance.getCardUsed(),
            };
            tableModel.addRow(rowData);
        }
    }
}
