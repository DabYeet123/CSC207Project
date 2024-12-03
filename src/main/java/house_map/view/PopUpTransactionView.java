package house_map.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import cardandexchange.dataObject.Card;
import userdataobject.UserObject;
import house_map.adapter.PopUpTransactionController;
import house_map.data_access.CardData;
import house_map.view_maker.ButtonMaker;
import house_map.view_maker.LabelMaker;
import house_map.view_maker.PanelMaker;

/**
 * The view for the Popup Transaction.
 */
public class PopUpTransactionView extends JFrame {

    public static final Rectangle CANCEL = new Rectangle(250, 25, 100, 50);
    public static final Rectangle CONFIRM = new Rectangle(50, 25, 100, 50);
    public static final Rectangle DROPDOWN = new Rectangle(100, 30, 200, 20);
    public static final int SIZE = 400;
    public static final DecimalFormat df = new DecimalFormat("#.00");
    private PopUpTransactionController controller;
    private CardData cardData;
    private JComboBox<String> dropdown;
    private LabelMaker transactionAmount;
    private LabelMaker amountBefore;
    private LabelMaker amountAfter;
    private double price;
    private Card currentCard;

    public PopUpTransactionView(UserObject user, PopUpTransactionController controller) {
        super("Transaction");
        cardData = new CardData(user);

        setSize(SIZE, SIZE);
        setLayout(null);

        this.controller = controller;

        final PanelMaker dropdownPanel = new PanelMaker(0, 0, SIZE, SIZE / 4, null, Color.black);
        addDropdown(dropdownPanel);
        final PanelMaker infoPanel = new PanelMaker(0, SIZE / 4, SIZE, SIZE / 2,
                new GridLayout(3, 2), Color.black);
        addInfo(infoPanel);

        final PanelMaker buttonPanel = new PanelMaker(0, 3 * SIZE / 4, SIZE, SIZE / 4, null, Color.black);
        final ButtonMaker confirm = new ButtonMaker("Submit", CONFIRM.x, CONFIRM.y, CONFIRM.width, CONFIRM.height) {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.confirmTransaction(price);
                controller.closeView();
            }
        };
        final ButtonMaker cancel = new ButtonMaker("Cancel", CANCEL.x, CANCEL.y, CANCEL.width, CANCEL.height) {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.closeView();
            }
        };

        buttonPanel.add(confirm);
        buttonPanel.add(cancel);

        this.add(dropdownPanel);
        this.add(infoPanel);
        this.add(buttonPanel);

        dispose();
        setUndecorated(true);
        setLocationRelativeTo(null);
    }

    /**
     * Creates the dropdown part of the view.
     * @param dropdownPanel the panel for the dropdown view
     */
    public void addDropdown(PanelMaker dropdownPanel) {
        final LabelMaker dropdownLabel = new LabelMaker("Card: ", null, 30, 30, 50, 20);
        final Map<String, String> cardIDName = cardData.getCardNames();
        final String[] cardNames = new String[cardIDName.values().size()];
        int i = 0;
        for (String name: cardIDName.keySet()) {
            cardNames[i] = name;
            i++;
        }

        dropdown = new JComboBox<>(cardNames);
        dropdown.setBounds(DROPDOWN.x, DROPDOWN.y, DROPDOWN.width, DROPDOWN.height);
        dropdownPanel.add(dropdown);
        dropdownPanel.add(dropdownLabel);
        currentCard = cardData.getCardByName((String) dropdown.getSelectedItem());
        dropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentCard = cardData.getCardByName((String) dropdown.getSelectedItem());
                controller.setCurrentCard(currentCard);
                final double balance = currentCard.getExpenses();
                amountBefore.setText("$" + df.format(balance));
                amountAfter.setText("$" + df.format(balance + price));
            }
        });
    }

    /**
     * Adds all the information with the panel.
     * @param infoPanel the panel where the information is displayed
     */
    public void addInfo(PanelMaker infoPanel) {

        double balance = 0;
        if(currentCard != null) {
            balance = currentCard.getExpenses();
        }
        final LabelMaker transaction = new LabelMaker("Transaction Amount: ", null);
        transactionAmount = new LabelMaker("$" + df.format(price), null);
        final LabelMaker currentBalance = new LabelMaker("Current Balance: ", null);
        amountBefore = new LabelMaker("$" + df.format(balance), null);
        final LabelMaker balanceAfter = new LabelMaker("Balance After Transaction: ", null);
        amountAfter = new LabelMaker("$" + df.format(balance + price), null);

        infoPanel.add(transaction);
        infoPanel.add(transactionAmount);
        infoPanel.add(currentBalance);
        infoPanel.add(amountBefore);
        infoPanel.add(balanceAfter);
        infoPanel.add(amountAfter);
    }

    /**
     * Updates the view when the cards are switched.
     */
    public void updateView() {
        controller.setCurrentCard(currentCard);
        final double balance = currentCard.getExpenses();
        transactionAmount.setText("$" + df.format(price));
        amountBefore.setText("$" + df.format(balance));
        amountAfter.setText("$" + df.format(balance + price));
        controller.updateBalance(price);
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
