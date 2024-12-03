package card.view;

import lombok.Getter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class CardView extends JFrame {
    private static final int SIZE_X = 600;
    private static final int SIZE_Y = 400;
    private static final int MAGIC_3 = 3;
    private static final int CARD_LIMIT = 10;
    private static final int GAP = 10;
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private JTextField usageField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton backButton;

    public CardView(CardController controller) {
        // Main frame
        setFrame(new JFrame("Card Manager"));
        getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getFrame().setSize(SIZE_X, SIZE_Y);
        getFrame().setLayout(new BorderLayout());

        // Table of form

        final String[] columns = {"ID", "Usage", "Expiry Date", "Security Code", "Expenses"};
        setModel(new DefaultTableModel(columns, 0));
        setTable(new JTable(getModel()));
        getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        final JScrollPane scrollPane = new JScrollPane(getTable());

        // input frame
        final JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(MAGIC_3, 2, GAP, GAP));

        inputPanel.add(new JLabel("Name:"));
        setUsageField(new JTextField());
        inputPanel.add(getUsageField());

        setAddButton(new JButton("Add Card"));
        getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CardController.getCardList().size() >= CARD_LIMIT) {
                    JOptionPane.showMessageDialog(getFrame(), "Too much cards", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    controller.addCard(getUsageField().getText());
                }
                controller.refresh();
            }
        });

        setDeleteButton(new JButton("Delete Card"));
        getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardView.this.deleteCard();
            }
        });

        setBackButton(new JButton("Back to Main"));
        getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.goBackToBaseView();
            }
        });

        final JPanel theButton = new JPanel();
        theButton.setLayout(new GridLayout(1, MAGIC_3));

        theButton.add(getAddButton());
        theButton.add(getDeleteButton());
        theButton.add(getBackButton());

        inputPanel.add(theButton);
        refresh(this);
        // final frame
        getFrame().add(inputPanel, BorderLayout.NORTH);
        getFrame().add(scrollPane, BorderLayout.CENTER);
        getFrame().setLocationRelativeTo(null);
    }

    /**
     * Used to renew the GUI as a help function to add/delete card.
     * @param cardView the GUI need to renew
     */
    public static void refresh(CardView cardView) {
        CardController.loadFromFile();
        cardView.getModel().setRowCount(0);
        for (Card card : CardController.getCardList()) {
            cardView.getModel().addRow(new Object[]{card.getId(),
                    card.getUsage(),
                    card.getDate(),
                    card.getCode(),
                    card.getExpenses() + "$"});
        }
    }

//    /**
//     * Used to add the card and renew the GUI.
//     */
//    public void addCard() {
//        CardController.loadFromFile();
//        final String name = getUsageField().getText();
//        final String securityCode = CardMethods.newCode();
//        final String id = CardMethods.newId(name);
//        final String expiryDate = CardMethods.newDate();

//        if (CardController.getCardList().size() >= CARD_LIMIT) {
//            JOptionPane.showMessageDialog(getFrame(), "Too much cards", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//        else {
//            final Card newCard = new Card(id, name, expiryDate, securityCode);
//            CardController.saveCards(newCard);
//            getUsageField().setText("");
//        }
//        refresh(this);
//    }

    /**
     * Used to delete the card and renew the GUI.
     */
    public void deleteCard() {
        final int selectedRow = getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(getFrame(), "Please select a row");
        }
        else {
            CardController.getCardList().remove(selectedRow);
            CardController.saveDeleteCard(selectedRow);
        }
        refresh(this);
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    public void setUsageField(JTextField usageField) {
        this.usageField = usageField;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }
}
