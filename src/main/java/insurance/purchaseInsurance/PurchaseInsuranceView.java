package insurance.purchaseInsurance;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jetbrains.annotations.NotNull;

import insurance.dataObject.InsuranceController;
import insurance.dataObject.InsuranceMethods;
import insurance.dataObject.InsuranceObject;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class PurchaseInsuranceView extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final String CHOOSE_INSURANCE_TYPE = "Choose Insurance Type";
    private static final String CHOOSE_INSURANCE_NAME_ID = "Choose Insurance Name (ID)";
    private static final String INVALID_INPUT = "Invalid Input";
    private static final String INVALID_SELECTION = "Invalid Selection";

    public PurchaseInsuranceView(PurchaseInsuranceController controller) {

        setTitle("Purchase Insurance");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        final JPanel inputPanel = new JPanel(new GridLayout(8, 2, 10, 10));

        final JLabel typeLabel = new JLabel("Insurance Type:");
        final JComboBox<String> typeComboBox = new JComboBox<>(new String[]{CHOOSE_INSURANCE_TYPE, "Health", "Vehicle",
            "Home", "Travel", "Life", "Pet", "Business", "Dental", "Other"});
        final JLabel nameIDLabel = new JLabel("Insurance Name (ID):");
        final JComboBox<String> nameIDComboBox = new JComboBox<>(new String[]{CHOOSE_INSURANCE_NAME_ID});
        nameIDComboBox.setEnabled(false);

        typeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typeComboBoxUpdate(typeComboBox, controller, nameIDComboBox);
            }
        });

        final JLabel cardLabel = new JLabel("Card Number:");
        final JTextField cardField = new JTextField();

        final JCheckBox autoRenewCheckBox = new JCheckBox("Automatically renew every year");
        final JLabel termLabel = new JLabel("Term (Yrs):");
        final JTextField termField = new JTextField();

        autoRenewCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                termField.setEnabled(!autoRenewCheckBox.isSelected());
            }
        });

        final JButton policyDetailsButton = getjButton(controller, typeComboBox, nameIDComboBox);

        final JButton deleteButton = new JButton("Delete Insurance");
        final JButton newButton = new JButton("+ New Insurance");
        final JButton confirmButton = new JButton("Confirm");
        final JButton cancelButton = new JButton("Cancel");

        inputPanel.add(typeLabel);
        inputPanel.add(typeComboBox);
        inputPanel.add(nameIDLabel);
        inputPanel.add(nameIDComboBox);
        inputPanel.add(policyDetailsButton);
        inputPanel.add(new JLabel());
        inputPanel.add(cardLabel);
        inputPanel.add(cardField);
        inputPanel.add(autoRenewCheckBox);
        inputPanel.add(new JLabel());
        inputPanel.add(termLabel);
        inputPanel.add(termField);
        inputPanel.add(deleteButton);
        inputPanel.add(newButton);
        inputPanel.add(confirmButton);
        inputPanel.add(cancelButton);

        add(inputPanel, BorderLayout.CENTER);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteInsurance(nameIDComboBox, typeComboBox, controller);
            }
        });

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.addNewInsuranceTriggered();
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirm(nameIDComboBox, cardField, termField, autoRenewCheckBox, controller, typeComboBox);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.goBackToBaseView();
            }
        });
    }

    private void deleteInsurance(JComboBox<String> nameIDComboBox, JComboBox<String> typeComboBox,
                                 PurchaseInsuranceController controller) {
        if (nameIDComboBox.getSelectedIndex() > 0) {
            final String selectedType = (String) typeComboBox.getSelectedItem();
            final List<InsuranceObject> filteredInsurances = controller.getInsurancesByType(selectedType);
            final int selectedIndex = nameIDComboBox.getSelectedIndex() - 1;
            final InsuranceObject selectedInsurance = filteredInsurances.get(selectedIndex);
            JOptionPane.showConfirmDialog(PurchaseInsuranceView.this,
                    "Are you sure to delete the selected insurance?" + InsuranceMethods.END_LINE + InsuranceMethods.END_LINE
                            + InsuranceMethods.getInsurancePolicyDetails(selectedType, selectedInsurance),
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            final InsuranceController insuranceController = new InsuranceController();
            insuranceController.deleteInsurance(selectedInsurance);
            JOptionPane.showMessageDialog(PurchaseInsuranceView.this,
                    "You have successfully deleted the selected insurance.",
                    "Success", JOptionPane.PLAIN_MESSAGE);
            controller.goBackToBaseView();
        }
        else {
            JOptionPane.showMessageDialog(PurchaseInsuranceView.this,
                    "Please select a valid Insurance.",
                    INVALID_SELECTION, JOptionPane.WARNING_MESSAGE);
        }
    }

    private void confirm(JComboBox<String> nameIDComboBox, JTextField cardField, JTextField termField,
                         JCheckBox autoRenewCheckBox, PurchaseInsuranceController controller,
                         JComboBox<String> typeComboBox) {
        try {
            if (nameIDComboBox.getSelectedIndex() > 0) {
                final String cardUsed = cardField.getText();
                final int term;
                if (!termField.getText().isEmpty()) {
                    term = Integer.parseInt(termField.getText());
                }
                else {
                    term = 0;
                }
                final boolean autoRenew = autoRenewCheckBox.isSelected();
                final boolean success = controller.purchaseInsuranceTriggered(term, autoRenew, cardUsed);
                if (success) {
                    final String selectedType = (String) typeComboBox.getSelectedItem();
                    final List<InsuranceObject> filteredInsurances = controller.getInsurancesByType(selectedType);
                    final int selectedIndex = nameIDComboBox.getSelectedIndex() - 1;
                    final InsuranceObject selectedInsurance = filteredInsurances.get(selectedIndex);
                    controller.onPurchaseInsuranceSuccess(selectedInsurance, term, autoRenew, cardUsed);
                }
                else {
                    JOptionPane.showMessageDialog(PurchaseInsuranceView.this,
                            "Please fill all the fields correctly.",
                            INVALID_INPUT, JOptionPane.WARNING_MESSAGE);
                }
            }
            else {
                JOptionPane.showMessageDialog(PurchaseInsuranceView.this,
                        "Please select a valid Insurance.",
                        INVALID_SELECTION, JOptionPane.WARNING_MESSAGE);
            }
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(PurchaseInsuranceView.this,
                    "Card number and term must be valid.", INVALID_INPUT,
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    @NotNull
    private JButton getjButton(PurchaseInsuranceController controller, JComboBox<String> typeComboBox,
                               JComboBox<String> nameIDComboBox) {
        final JButton policyDetailsButton = new JButton("Insurance Policy Details");

        policyDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String selectedType = (String) typeComboBox.getSelectedItem();
                if (selectedType != null && !selectedType.equals(CHOOSE_INSURANCE_TYPE)) {
                    final List<InsuranceObject> filteredInsurances = controller.getInsurancesByType(selectedType);
                    if (nameIDComboBox.getSelectedIndex() > 0) {
                        final int selectedIndex = nameIDComboBox.getSelectedIndex() - 1;
                        final InsuranceObject selectedInsurance = filteredInsurances.get(selectedIndex);
                        JOptionPane.showMessageDialog(PurchaseInsuranceView.this,
                                InsuranceMethods.getInsurancePolicyDetails(selectedType, selectedInsurance),
                                "Insurance Policy Details", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(PurchaseInsuranceView.this,
                            "Please select a valid Insurance Type.", INVALID_SELECTION,
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        return policyDetailsButton;
    }

    private static void typeComboBoxUpdate(JComboBox<String> typeComboBox, PurchaseInsuranceController controller,
                                           JComboBox<String> nameIDComboBox) {
        final String selectedType = (String) typeComboBox.getSelectedItem();
        if (selectedType != null && !selectedType.equals(CHOOSE_INSURANCE_TYPE)) {
            final List<InsuranceObject> filteredInsurances = controller.getInsurancesByType(selectedType);
            nameIDComboBox.removeAllItems();
            nameIDComboBox.addItem(CHOOSE_INSURANCE_NAME_ID);
            for (InsuranceObject insurance : filteredInsurances) {
                nameIDComboBox.addItem(insurance.getInsuranceName() + " (" + insurance.getInsuranceID() + ")");
            }
            nameIDComboBox.setEnabled(true);
        }
        else {
            nameIDComboBox.setEnabled(false);
            nameIDComboBox.removeAllItems();
            nameIDComboBox.addItem(CHOOSE_INSURANCE_NAME_ID);
        }
    }
}
