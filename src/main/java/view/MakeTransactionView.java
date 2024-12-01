package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import entity.User;
import interface_adapter.maketransaction.MakeTransactionController;
import interface_adapter.maketransaction.MakeTransactionState;
import interface_adapter.maketransaction.MakeTransactionViewModel;

/**
 * The View for when the user is logged into the program.
 */
public class MakeTransactionView extends JPanel implements ActionListener, PropertyChangeListener {
    private static final int ROW_COUNT = 4;
    private static final int COLUMN_COUNT = 2;
    private static final int HORIZONTAL_GAP = 10;
    private static final int VERTICAL_GAP = 10;
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;

    private final String viewName = "maketransaction";
    private final MakeTransactionViewModel makeTransactionViewModel;
    private final MakeTransactionController makeTransactionController;

    private final JTextField userInputField = new JTextField(15);
    private final JTextField receiverIdInputField = new JTextField(15);
    private final JTextField amountInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    private final JButton makeTransactionButton;
    private final JButton cancel;
    // TODO : add connection to db to retrieve cards

    private User loggedinUser;

    @SuppressWarnings("checkstyle:ExecutableStatementCount")
    public MakeTransactionView(MakeTransactionController makeTransactionController,
                               MakeTransactionViewModel makeTransactionViewModel) {
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.makeTransactionController = makeTransactionController;
        this.makeTransactionViewModel = makeTransactionViewModel;
        this.makeTransactionViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Make Transaction in Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel transactionPanel = new JPanel(
                new GridLayout(ROW_COUNT, COLUMN_COUNT, HORIZONTAL_GAP, VERTICAL_GAP));

        final JLabel cardLabel = new JLabel("Card:");
        final String[] cardOptions = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        final JComboBox<String> cardDropdown = new JComboBox<>(cardOptions);

        final LabelTextPanel userInfo = new LabelTextPanel(
                new JLabel("Sender UserID"), userInputField);

        final LabelTextPanel receiverIdInfo = new LabelTextPanel(
                new JLabel("Receiver UserID"), receiverIdInputField);

        final LabelTextPanel amountInfo = new LabelTextPanel(
                new JLabel("Amount"), amountInputField);

        final JPanel buttons = new JPanel();
        makeTransactionButton = new JButton("Process Transaction");
        buttons.add(makeTransactionButton);
        cancel = new JButton("Cancel");
        buttons.add(cancel);

        makeTransactionButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        if (event.getSource().equals(makeTransactionButton)) {
                            final String cardUsed = (String) cardDropdown.getSelectedItem();
                            MakeTransactionState currentState = makeTransactionViewModel.getState();
                            currentState.setCard(cardUsed);
                            makeTransactionViewModel.setState(currentState);

                            currentState = makeTransactionViewModel.getState();
                            makeTransactionController.execute(
                                    currentState.getUser(),
                                    currentState.getReceiverId(),
                                    currentState.getCard(),
                                    currentState.getAmount()
                            );
                        }
                    }
                }
        );

        cancel.addActionListener(this);

        receiverIdInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final MakeTransactionState currentState = makeTransactionViewModel.getState();
                currentState.setReceiverId(Integer.parseInt(receiverIdInputField.getText()));
                makeTransactionViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        amountInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final MakeTransactionState currentState = makeTransactionViewModel.getState();
                currentState.setAmount(Double.parseDouble(amountInputField.getText()));
                makeTransactionViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        this.add(title);

        transactionPanel.add(userInfo);
        transactionPanel.add(userInputField);
        transactionPanel.add(cardLabel);
        transactionPanel.add(cardDropdown);
        transactionPanel.add(receiverIdInfo);
        transactionPanel.add(receiverIdInputField);
        transactionPanel.add(amountInfo);
        transactionPanel.add(amountInputField);

        add(transactionPanel, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
        if ("cancel".equals(evt.getActionCommand())) {
            makeTransactionController.switchToLoggedinView();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final MakeTransactionState state = (MakeTransactionState) evt.getNewValue();
        setFields(state);
        usernameErrorField.setText(state.getTransactionError());
    }

    private void setFields(MakeTransactionState state) {
        userInputField.setText(String.valueOf(state.getUser().getUserID()));
    }

    public String getViewName() {
        return viewName;
    }
}
