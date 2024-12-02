package insurance.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import insurance.adapter.MyInsuranceController;
import insurance.adapter.UserInsuranceController;
import insurance.dataObject.UserInsuranceObject;
import userdataobject.UserObject;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class MyInsuranceView extends JFrame {
    private static final int WIDTH = 1350;
    private static final int HEIGHT = 400;
    private static final int INSURANCE_ID_COL = 2;
    private static final int AUTO_RENEW_COL = 7;
    private static final int FONT_SIZE = 20;
    private static final int INTERVAL_1 = 300;
    private static final int INTERVAL_2 = 400;
    private final UserObject user;

    /**
     * Constructs the MyInsuranceView for displaying user's insurance policies.
     *
     * @param controller The controller to manage actions within the view.
     */
    public MyInsuranceView(MyInsuranceController controller) {
        this.user = controller.getLoggedInUser();
        setTitle("My Insurance Policies");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        final String[] columnNames = {"Type", "Name", "ID", "Policy Details", "Premium ($)",
            "Start Date", "End Date", "Auto Renew", "Card"};
        final DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        final JTable insuranceTable = new JTable(tableModel);
        final JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"Choose Insurance Type",
            "Health", "Vehicle", "Home", "Travel", "Life", "Pet", "Business", "Dental", "Other"});
        updateTable(tableModel, controller.getInsurances());
        typeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filter(typeComboBox, controller, tableModel);
            }
        });

        final JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JLabel titleLabel = new JLabel("My Insurance Policies");
        final JButton cancelButton = new JButton("Cancel Auto Renewal");
        titleLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        headerPanel.add(new JLabel("Filter by Type:"));
        headerPanel.add(typeComboBox);
        headerPanel.add(Box.createHorizontalStrut(INTERVAL_1));
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createHorizontalStrut(INTERVAL_2));
        headerPanel.add(cancelButton);
        add(headerPanel, BorderLayout.NORTH);

        final JScrollPane scrollPane = new JScrollPane(insuranceTable);
        add(scrollPane, BorderLayout.CENTER);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelAutoRenewalButton(insuranceTable);
                controller.goBackToBaseView();
            }
        });
        backPanel(controller);
    }

    /**
     * Creates a back panel with a back button to return to the base view.
     *
     * @param controller The controller that manages the back navigation.
     */
    private void backPanel(MyInsuranceController controller) {
        final JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        final JButton backButton = new JButton("Back");
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
     * Filters the insurance policies based on the selected type and updates the table.
     *
     * @param typeComboBox The combo box for selecting the type of insurance.
     * @param controller   The controller that provides the filtered insurance policies.
     * @param tableModel   The table model to be updated with filtered insurance policies.
     */
    private static void filter(JComboBox<String> typeComboBox, MyInsuranceController controller,
                               DefaultTableModel tableModel) {
        final String selectedType = (String) typeComboBox.getSelectedItem();
        final List<UserInsuranceObject> filteredInsurances;
        if (selectedType != null && !"Choose Insurance Type".equals(selectedType)) {
            filteredInsurances = controller.getInsurancesByType(selectedType);
        }
        else {
            filteredInsurances = controller.getInsurances();
        }
        updateTable(tableModel, filteredInsurances);
    }

    /**
     * Updates the table with the given list of insurance policies.
     *
     * @param tableModel         The table model to be updated.
     * @param filteredInsurances The list of insurance policies to populate the table.
     */
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
                insurance.getInsurance().getPolicyDetails(),
                String.format("%.2f", insurance.getInsurance().getPremium()),
                String.valueOf(insurance.getStartDate()),
                endDate,
                autoRenew,
                insurance.getCardUsed(),
            };
            tableModel.addRow(rowData);
        }
    }

    /**
     * Handles the action for cancelling auto-renewal for the selected insurance policy.
     *
     * @param table The table displaying the insurance policies.
     */
    private void cancelAutoRenewalButton(JTable table) {
        final UserInsuranceController userInsuranceController = new UserInsuranceController();
        final int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(MyInsuranceView.this,
                    "Please select a row.", "Invalid Selection", JOptionPane.WARNING_MESSAGE);
        }
        else {
            final String isAutoRenew = table.getValueAt(selectedRow, AUTO_RENEW_COL).toString();
            if ("No".equals(isAutoRenew)) {
                JOptionPane.showMessageDialog(MyInsuranceView.this,
                        "Please select a row that is auto-renew.",
                        "Invalid Selection", JOptionPane.WARNING_MESSAGE);
            }
            else {
                final int insuranceID = Integer.parseInt(table.getValueAt(selectedRow, INSURANCE_ID_COL).toString());
                userInsuranceController.cancelAutoRenewalByInsuranceID(user.getUserID(), insuranceID);
            }
        }
    }
}