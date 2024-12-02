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
import insurance.useCase.InsuranceMethods;
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
        final JComboBox<String> typeComboBox = new JComboBox<>(new String[]{InsuranceMethods.CHOOSE_INSURANCE_TYPE,
            "Health", "Vehicle", "Home", "Travel", "Life", "Pet", "Business", "Dental", "Other"});
        updateTable(typeComboBox, tableModel, controller);
        typeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable(typeComboBox, tableModel, controller);
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
                cancelAutoRenewalButton(insuranceTable, controller);
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
     * Updates the table with the given list of insurance policies.
     *
     * @param typeComboBox       The combo box for selecting the type of insurance.
     * @param tableModel         The table model to be updated.
     * @param controller         The controller that provides the filtered insurance policies.
     */
    private static void updateTable(JComboBox<String> typeComboBox, DefaultTableModel tableModel,
                                    MyInsuranceController controller) {
        final String selectedType = (String) typeComboBox.getSelectedItem();
        final List<UserInsuranceObject> filteredInsurances;
        if (selectedType != null && !"Choose Insurance Type".equals(selectedType)) {
            filteredInsurances = controller.getInsurancesByType(selectedType);
        }
        else {
            filteredInsurances = controller.getInsurances();
        }
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
                controller.showCardInformation(insurance.getCardUsed()),
            };
            tableModel.addRow(rowData);
        }
    }

    /**
     * Handles the action for cancelling auto-renewal for the selected insurance policy.
     *
     * @param table The table displaying the insurance policies.
     */
    private void cancelAutoRenewalButton(JTable table, MyInsuranceController controller) {
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
                if (JOptionPane.showConfirmDialog(MyInsuranceView.this,
                "Are you sure to delete the selected insurance?",
                "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    final int insuranceID = Integer.parseInt(table.getValueAt(selectedRow, INSURANCE_ID_COL).toString());
                    userInsuranceController.cancelAutoRenewalByInsuranceID(user.getUserID(), insuranceID);
                    JOptionPane.showMessageDialog(MyInsuranceView.this,
                            "You have successfully canceled the auto-renewal of this insurance.",
                            "Success", JOptionPane.PLAIN_MESSAGE);
                    controller.goBackToBaseView();
                }
            }
        }
    }
}
