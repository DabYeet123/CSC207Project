package insurance.purchaseInsurance;

import DataObjects.UserObject;
import insurance.dataObject.InsuranceObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PurchaseInsuranceView extends JFrame {
    UserObject user;

    public PurchaseInsuranceView(PurchaseInsuranceController controller) {
        this.user = controller.loggedInUser;

        setTitle("Purchase Insurance");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 10, 10));

        JLabel typeLabel = new JLabel("Insurance Type:");
        JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"Choose Insurance Type", "Health", "Vehicle", "Home", "Travel", "Life", "Pet", "Business", "Dental"});
        JLabel nameIDLabel = new JLabel("Insurance Name (ID):");
        JComboBox<String> nameIDComboBox = new JComboBox<>(new String[]{"Choose Insurance Name/ID"});
        nameIDComboBox.setEnabled(false);

        typeComboBox.addActionListener(e -> {
            String selectedType = (String) typeComboBox.getSelectedItem();
            if (selectedType != null && !selectedType.equals("Choose Insurance Type")) {
                final List<InsuranceObject> filteredInsurances = controller.getInsurancesByType(selectedType);
                nameIDComboBox.removeAllItems();
                nameIDComboBox.addItem("Choose Insurance Name (ID)");
                for (InsuranceObject insurance : filteredInsurances) {
                    nameIDComboBox.addItem(insurance.getInsuranceName() + " (" + insurance.getInsuranceID() + ")");
                }
                nameIDComboBox.setEnabled(true);
            }
            else {
                nameIDComboBox.setEnabled(false);
                nameIDComboBox.removeAllItems();
                nameIDComboBox.addItem("Choose Insurance Name (ID)");
            }
        });

        JLabel cardLabel = new JLabel("Card Number:");
        JTextField cardField = new JTextField();

        JCheckBox autoRenewCheckBox = new JCheckBox("Automatically renew every year");
        JLabel termLabel = new JLabel("Term (Yrs):");
        JTextField termField = new JTextField();
        autoRenewCheckBox.addActionListener(e -> termField.setEnabled(!autoRenewCheckBox.isSelected()));

        JButton policyDetailsButton = new JButton("Insurance Policy Details");


        policyDetailsButton.addActionListener(e -> {
            String selectedType = (String) typeComboBox.getSelectedItem();
            if (selectedType != null && !selectedType.equals("Choose Insurance Type")) {
                List<InsuranceObject> filteredInsurances = controller.getInsurancesByType(selectedType);
                if (nameIDComboBox.getSelectedIndex() > 0) {
                    int selectedIndex = nameIDComboBox.getSelectedIndex() - 1;
                    final InsuranceObject selectedInsurance = filteredInsurances.get(selectedIndex);
                    String policyDetails = selectedInsurance.getPolicyDetails();
                    JOptionPane.showMessageDialog(this, "Insurance Type: " + selectedType + "\n"
                                    + "Insurance Name: " + selectedInsurance.getInsuranceName() + "\n"
                                    + "Insurance ID: " + selectedInsurance.getInsuranceID() + "\n"
                                    + "Price per year ($): " + selectedInsurance.getPremium() + "\n"
                                    + "Insurance Policy: " + policyDetails,
                            "Insurance Policy Details", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else {
                JOptionPane.showMessageDialog(this, "Please select a valid Insurance Type.", "Invalid Selection", JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton confirmButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Cancel");

        inputPanel.add(typeLabel);
        inputPanel.add(typeComboBox);
        inputPanel.add(nameIDLabel);
        inputPanel.add(nameIDComboBox);
        inputPanel.add(cardLabel);
        inputPanel.add(cardField);
        inputPanel.add(autoRenewCheckBox);
        inputPanel.add(new JLabel());
        inputPanel.add(termLabel);
        inputPanel.add(termField);
        inputPanel.add(policyDetailsButton);
        inputPanel.add(new JLabel());
        inputPanel.add(confirmButton);
        inputPanel.add(cancelButton);

        add(inputPanel, BorderLayout.CENTER);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String type = (String) typeComboBox.getSelectedItem();
                    if (nameIDComboBox.getSelectedIndex() > 0) {
                        String cardUsed = cardField.getText();
                        final int term;
                        if (!termField.getText().isEmpty()) {
                            term = Integer.parseInt(termField.getText());
                        }
                        else {
                            term = 0;
                        }
                        boolean autoRenew = autoRenewCheckBox.isSelected();
                        boolean success = controller.purchaseInsuranceTriggered(term, autoRenew, cardUsed);
                        if (success) {
                            String selectedType = (String) typeComboBox.getSelectedItem();
                            List<InsuranceObject> filteredInsurances = controller.getInsurancesByType(selectedType);
                            int selectedIndex = nameIDComboBox.getSelectedIndex() - 1;
                            final InsuranceObject selectedInsurance = filteredInsurances.get(selectedIndex);
                            controller.onPurchaseInsuranceSuccess(selectedInsurance, term, autoRenew, cardUsed);
                        }
                        else {
                            JOptionPane.showMessageDialog(PurchaseInsuranceView.this, "Please fill all the fields correctly.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(PurchaseInsuranceView.this, "Please select a valid Insurance.", "Invalid Selection", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(PurchaseInsuranceView.this, "Card number and term must be valid.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.goBackToBaseView();
            }
        });
    }
}
