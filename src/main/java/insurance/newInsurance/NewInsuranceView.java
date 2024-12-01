package insurance.newInsurance;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import insurance.dataObject.InsuranceController;
import insurance.dataObject.InsuranceMethods;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class NewInsuranceView extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final int ROWS = 5;
    private static final int COLS = 2;
    private static final int H_GAP = 10;
    private static final int V_GAP = 10;

    public NewInsuranceView(NewInsuranceController controller) {
        setTitle("Add New Insurance");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        final JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(ROWS, COLS, H_GAP, V_GAP));
        final JLabel typeLabel = new JLabel("Insurance Type:");
        final JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"Choose Insurance Type", "Health",
            "Vehicle", "Home", "Travel", "Life", "Pet", "Business", "Dental", "Other"});
        final JLabel nameLabel = new JLabel("Insurance Name:");
        final JTextField nameField = new JTextField();
        final JLabel premiumLabel = new JLabel("Premium per year ($):");
        final JTextField premiumField = new JTextField();
        final JLabel policyLabel = new JLabel("Insurance Policy:");
        final JTextField policyField = new JTextField();
        final JButton confirmButton = new JButton("Confirm");
        final JButton cancelButton = new JButton("Cancel");

        inputPanel.add(typeLabel);
        inputPanel.add(typeComboBox);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(premiumLabel);
        inputPanel.add(premiumField);
        inputPanel.add(policyLabel);
        inputPanel.add(policyField);
        inputPanel.add(confirmButton);
        inputPanel.add(cancelButton);

        add(inputPanel, BorderLayout.CENTER);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmCreated(typeComboBox, nameField, premiumField, policyField, controller);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.goBackToBaseView();
            }
        });
    }

    private void confirmCreated(JComboBox<String> typeComboBox, JTextField nameField,
                                JTextField premiumField, JTextField policyField, NewInsuranceController controller) {
        final String type = (String) typeComboBox.getSelectedItem();
        try {
            if (type != null && !"Choose Insurance Type".equals(type)) {
                final String name = nameField.getText();
                final Double premium = (Double) Double.parseDouble(premiumField.getText());
                final String policy = policyField.getText();
                if (premium.compareTo(0.0) < 0) {
                    JOptionPane.showMessageDialog(NewInsuranceView.this,
                            "Premium must be non-negative.", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
                }
                else {
                    final InsuranceController insuranceController = new InsuranceController();
                    insuranceController.addInsurance(controller.getLoggedInUser().getUserID(), type,
                            premium, policy, name);
                    JOptionPane.showMessageDialog(NewInsuranceView.this,
                            "You have successfully added a new insurance."
                                    + InsuranceMethods.END_LINE + InsuranceMethods.END_LINE
                                    + InsuranceMethods.getInsurancePolicyDetails(type,
                                            insuranceController.getLatestInsurance()),
                            "Success", JOptionPane.PLAIN_MESSAGE);
                    controller.goBackToBaseView();
                }
            }
            else {
                JOptionPane.showMessageDialog(NewInsuranceView.this,
                        "Please select a valid Insurance Type.", "Invalid Selection",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(NewInsuranceView.this,
                    "Premium must be a number.", "Invalid Input",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
