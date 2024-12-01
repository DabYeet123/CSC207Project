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

import insurance.dataObject.InsuranceObject;

public class PurchaseInsuranceView extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final String CHOOSE_INSURANCE_TYPE = "Choose Insurance Type";

    public PurchaseInsuranceView(PurchaseInsuranceController controller) {

        setTitle("Purchase Insurance");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        final JPanel inputPanel = new JPanel(new GridLayout(7, 2, 10, 10));

        final JLabel typeLabel = new JLabel("Insurance Type:");
        final JComboBox<String> typeComboBox = new JComboBox<>(new String[]{CHOOSE_INSURANCE_TYPE, "Health", "Vehicle",
            "Home", "Travel", "Life", "Pet", "Business", "Dental"});
        final JLabel nameIDLabel = new JLabel("Insurance Name (ID):");
        final JComboBox<String> nameIDComboBox = new JComboBox<>(new String[]{"Choose Insurance Name/ID"});
        nameIDComboBox.setEnabled(false);

        typeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String selectedType = (String) typeComboBox.getSelectedItem();
                if (selectedType != null && !selectedType.equals(CHOOSE_INSURANCE_TYPE)) {
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
                        final String policyDetails = selectedInsurance.getPolicyDetails();
                        JOptionPane.showMessageDialog(PurchaseInsuranceView.this, "Insurance Type: " + selectedType + "\n"
                                        + "Insurance Name: " + selectedInsurance.getInsuranceName() + "\n"
                                        + "Insurance ID: " + selectedInsurance.getInsuranceID() + "\n"
                                        + "Price per year ($): " + selectedInsurance.getPremium() + "\n"
                                        + "Insurance Policy: " + policyDetails,
                                "Insurance Policy Details", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(PurchaseInsuranceView.this, "Please select a valid Insurance Type.", "Invalid Selection", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        final JButton confirmButton = new JButton("Confirm");
        final JButton cancelButton = new JButton("Cancel");

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
                                    "Invalid Input", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(PurchaseInsuranceView.this,
                                "Please select a valid Insurance.",
                                "Invalid Selection", JOptionPane.WARNING_MESSAGE);
                    }
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(PurchaseInsuranceView.this,
                            "Card number and term must be valid.", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
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
