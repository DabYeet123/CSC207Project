package insurance.view;

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

import insurance.adapter.InsuranceController;
import insurance.adapter.PurchaseInsuranceController;
import insurance.dataObject.InsuranceObject;
import insurance.useCase.InsuranceMethods;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class PurchaseInsuranceView extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    private static final String INVALID_INPUT = "Invalid Input";
    private static final String INVALID_SELECTION = "Invalid Selection";

    /**
     * Constructs the PurchaseInsuranceView for purchasing insurance policies.
     *
     * @param controller The controller to manage actions within the view.
     */
    public PurchaseInsuranceView(PurchaseInsuranceController controller) {
        setTitle("Purchase Insurance");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        final JPanel inputPanel = createInputPanel(controller);
        add(inputPanel, BorderLayout.CENTER);

    }

    private JPanel createInputPanel(PurchaseInsuranceController controller) {
        final JPanel inputPanel = new JPanel(new GridLayout(8, 2, 10, 10));

        final JComboBox<String> nameIDComboBox = createNameIDComboBox();
        final JComboBox<String> typeComboBox = createTypeComboBox(controller, nameIDComboBox);
        final JTextField cardField = new JTextField();
        final JCheckBox autoRenewCheckBox = createAutoRenewCheckBox();
        final JTextField termField = createTermField(autoRenewCheckBox);
        final JButton policyDetailsButton = createPolicyDetailsButton(controller, typeComboBox, nameIDComboBox);
        final JButton deleteButton = createDeleteButton(controller, nameIDComboBox, typeComboBox);
        final JButton newButton = createNewButton(controller);
        final JButton confirmButton = createConfirmButton(controller, nameIDComboBox, cardField,
                termField, autoRenewCheckBox, typeComboBox);
        final JButton cancelButton = createCancelButton(controller);
        inputPanel.add(new JLabel("Insurance Type:"));
        inputPanel.add(typeComboBox);
        inputPanel.add(new JLabel("Insurance Name (ID):"));
        inputPanel.add(nameIDComboBox);
        inputPanel.add(policyDetailsButton);
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel("Card Number:"));
        inputPanel.add(cardField);
        inputPanel.add(autoRenewCheckBox);
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel("Term (Yrs):"));
        inputPanel.add(termField);
        inputPanel.add(deleteButton);
        inputPanel.add(newButton);
        inputPanel.add(confirmButton);
        inputPanel.add(cancelButton);
        return inputPanel;
    }

    private JComboBox<String> createTypeComboBox(PurchaseInsuranceController controller,
                                                 JComboBox<String> nameIDComboBox) {
        JComboBox<String> typeComboBox = new JComboBox<>(
                new String[]{InsuranceMethods.CHOOSE_INSURANCE_TYPE, "Health", "Vehicle",
                        "Home", "Travel", "Life", "Pet", "Business", "Dental", "Other"});
        typeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typeComboBoxUpdate(typeComboBox, controller, nameIDComboBox);
            }});
        return typeComboBox;
    }

    private JComboBox<String> createNameIDComboBox() {
        final JComboBox<String> nameIDComboBox = new JComboBox<>(
                new String[]{InsuranceMethods.CHOOSE_INSURANCE_NAME_ID});
        nameIDComboBox.setEnabled(false);
        return nameIDComboBox;
    }

    private JCheckBox createAutoRenewCheckBox() {
        return new JCheckBox("Automatically renew every year");
    }

    private JTextField createTermField(JCheckBox autoRenewCheckBox) {
        final JTextField termField = new JTextField();
        autoRenewCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                termField.setEnabled(!autoRenewCheckBox.isSelected());
            }
        });
        return termField;
    }

    private JButton createDeleteButton(PurchaseInsuranceController controller, JComboBox<String> nameIDComboBox,
                                       JComboBox<String> typeComboBox) {
        final JButton deleteButton = new JButton("Delete Insurance");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteInsurance(nameIDComboBox, typeComboBox, controller);
            }
        });
        return deleteButton;
    }

    private JButton createNewButton(PurchaseInsuranceController controller) {
        final JButton newButton = new JButton("+ New Insurance");
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.addNewInsuranceTriggered();
            }
        });
        return newButton;
    }

    private JButton createConfirmButton(PurchaseInsuranceController controller, JComboBox<String> nameIDComboBox,
                                        JTextField cardField, JTextField termField, JCheckBox autoRenewCheckBox,
                                        JComboBox<String> typeComboBox) {
        final JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirm(nameIDComboBox, cardField, termField, autoRenewCheckBox, controller, typeComboBox);
            }
        });
        return confirmButton;
    }

    private JButton createCancelButton(PurchaseInsuranceController controller) {
        final JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.goBackToBaseView();
            }
        });
        return cancelButton;
    }

    private void deleteInsurance(JComboBox<String> nameIDComboBox, JComboBox<String> typeComboBox,
                                 PurchaseInsuranceController controller) {
        if (nameIDComboBox.getSelectedIndex() > 0) {
            final String selectedType = (String) typeComboBox.getSelectedItem();
            final List<InsuranceObject> filteredInsurances = controller.getInsurancesByType(selectedType);
            final int selectedIndex = nameIDComboBox.getSelectedIndex() - 1;
            final InsuranceObject selectedInsurance = filteredInsurances.get(selectedIndex);
            if (JOptionPane.showConfirmDialog(PurchaseInsuranceView.this,
                    "Are you sure to delete the selected insurance?"
                            + InsuranceMethods.END_LINE + InsuranceMethods.END_LINE
                            + InsuranceMethods.getInsurancePolicyDetails(selectedInsurance),
                    "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                final InsuranceController insuranceController = new InsuranceController();
                insuranceController.deleteInsurance(selectedInsurance);
                JOptionPane.showMessageDialog(PurchaseInsuranceView.this,
                        "You have successfully deleted the selected insurance.",
                        "Success", JOptionPane.PLAIN_MESSAGE);
                controller.goBackToBaseView();
            }
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
                    if (JOptionPane.showConfirmDialog(PurchaseInsuranceView.this,
                            "Are you sure to purchase the selected insurance?"
                                    + InsuranceMethods.END_LINE + InsuranceMethods.END_LINE
                                    + InsuranceMethods.getInsurancePolicyDetails(selectedInsurance),
                            "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        controller.onPurchaseInsuranceSuccess(selectedInsurance, term, autoRenew, cardUsed);
                        JOptionPane.showMessageDialog(PurchaseInsuranceView.this,
                                "You have successfully purchased the selected insurance."
                                        + InsuranceMethods.END_LINE + InsuranceMethods.END_LINE
                                        + InsuranceMethods.getInsurancePolicyDetails(selectedInsurance),
                                "Success", JOptionPane.PLAIN_MESSAGE);
                    }
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
    private JButton createPolicyDetailsButton(PurchaseInsuranceController controller, JComboBox<String> typeComboBox,
                               JComboBox<String> nameIDComboBox) {
        final JButton policyDetailsButton = new JButton("Insurance Policy Details");

        policyDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String selectedType = (String) typeComboBox.getSelectedItem();
                if (selectedType != null && !selectedType.equals(InsuranceMethods.CHOOSE_INSURANCE_TYPE)) {
                    final List<InsuranceObject> filteredInsurances = controller.getInsurancesByType(selectedType);
                    if (nameIDComboBox.getSelectedIndex() > 0) {
                        final int selectedIndex = nameIDComboBox.getSelectedIndex() - 1;
                        final InsuranceObject selectedInsurance = filteredInsurances.get(selectedIndex);
                        JOptionPane.showMessageDialog(PurchaseInsuranceView.this,
                                InsuranceMethods.getInsurancePolicyDetails(selectedInsurance),
                                "Insurance Policy Details", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(PurchaseInsuranceView.this,
                                "Please select a valid Insurance.",
                                INVALID_SELECTION, JOptionPane.WARNING_MESSAGE);
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
        if (selectedType != null && !InsuranceMethods.CHOOSE_INSURANCE_TYPE.equals(selectedType)) {

            final List<InsuranceObject> filteredInsurances = controller.getInsurancesByType(selectedType);
            nameIDComboBox.removeAllItems();
            nameIDComboBox.addItem(InsuranceMethods.CHOOSE_INSURANCE_NAME_ID);
            for (InsuranceObject insurance : filteredInsurances) {
                nameIDComboBox.addItem(insurance.getInsuranceName() + " (" + insurance.getInsuranceID() + ")");
            }
            nameIDComboBox.setEnabled(true);
        }
        else {
            nameIDComboBox.setEnabled(false);
            nameIDComboBox.removeAllItems();
            nameIDComboBox.addItem(InsuranceMethods.CHOOSE_INSURANCE_NAME_ID);
        }
    }
}
