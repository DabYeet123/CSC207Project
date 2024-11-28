package HouseMap.View;

import Card.Card;
import DataObjects.UserObject;
import HouseMap.Adapter.PopUpTransactionController;
import HouseMap.DataAccess.CardData;
import HouseMap.DataObject.HouseObject;
import Views.ButtonMaker;
import Views.LabelMaker;
import Views.PanelMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Map;

public class PopUpTransactionView extends JFrame {

    private PopUpTransactionController controller;
    private CardData cardData;

    private JComboBox<String> dropdown;
    private JLabel transactionAmount;
    private JLabel amountBefore ;
    private JLabel amountAfter;

    private double price;
    private boolean buying;
    private Card currentCard;
    private HouseObject currentHouse;

    public PopUpTransactionView(UserObject user, PopUpTransactionController controller) {
        super("Transaction");
        cardData = new CardData(user);

        setSize(400, 400);
        setLayout(null);

        this.controller = controller;

        JPanel dropdownPanel = new PanelMaker(0, 0, 400, 100, null, Color.black);
        addDropdown(dropdownPanel);
        JPanel infoPanel = new PanelMaker(0, 100, 400, 200, new GridLayout(3, 2), Color.black);
        addInfo(infoPanel);


        JPanel buttonPanel = new PanelMaker(0, 300, 400, 100, null, Color.black);
        JButton confirm = new ButtonMaker("Submit", 50, 25, 100, 50) {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.confirmTransaction(price);
                controller.closeView();
            }
        };
        JButton cancel = new ButtonMaker("Cancel", 250, 25, 100, 50) {
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


    public void addDropdown(JPanel dropdownPanel) {
        JLabel dropdownLabel = new LabelMaker("Card: ", null, 30, 30,50,20);
        Map<String, String> cardIDName = cardData.getCardNames();
        String[] cardNames = new String[cardIDName.values().size()];
        int i = 0;
        for(String name: cardIDName.keySet()) {
            cardNames[i] = name;
            i++;
        }

        dropdown = new JComboBox<>(cardNames);
        dropdown.setBounds(100, 30, 200, 20);
        dropdownPanel.add(dropdown);
        dropdownPanel.add(dropdownLabel);
        DecimalFormat df = new DecimalFormat("#.00");
        currentCard = cardData.getCardByName((String) dropdown.getSelectedItem());
        //controller.setCurrentCard(currentCard);
        dropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentCard = cardData.getCardByName((String) dropdown.getSelectedItem());
                controller.setCurrentCard(currentCard);
                double balance = currentCard.getExpenses();
                amountBefore.setText("$" + df.format(balance));
                amountAfter.setText("$" + df.format((balance + price)));
            }
        });
    }

    public void addInfo(JPanel infoPanel) {
        DecimalFormat df = new DecimalFormat("#.00");

        double balance = currentCard.getExpenses();
        JLabel transaction = new LabelMaker("Transaction Amount: ", null);
        transactionAmount = new LabelMaker("$" + df.format(price), null);
        JLabel currentBalance = new LabelMaker("Current Balance: ", null);
        amountBefore = new LabelMaker("$" + df.format(balance), null);
        JLabel balanceAfter = new LabelMaker("Balance After Transaction: ", null);
        amountAfter = new LabelMaker("$" + df.format((balance + price)), null);

        infoPanel.add(transaction);
        infoPanel.add(transactionAmount);
        infoPanel.add(currentBalance);
        infoPanel.add(amountBefore);
        infoPanel.add(balanceAfter);
        infoPanel.add(amountAfter);
    }

    public void updateView() {
        DecimalFormat df = new DecimalFormat("#.00");

        controller.setCurrentCard(currentCard);
        double balance = currentCard.getExpenses();
        transactionAmount.setText("$" + df.format(price));
        amountBefore.setText("$" + df.format(balance));
        amountAfter.setText("$" + df.format((balance + price)));
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
