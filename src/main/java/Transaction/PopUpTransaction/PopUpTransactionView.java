package Transaction.PopUpTransaction;

import Card.Card;
import Views.ButtonMaker;
import Views.LabelMaker;
import Views.PanelMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PopUpTransactionView extends JFrame {

    public PopUpTransactionView(PopUpTransactionController controller) {
        super("Transaction");
        setSize(400, 400);
        setLayout(null);

        double amount = controller.getAmount(); //TODO

        //List<Card> cards = CardController.cardList;
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card("Joe", "ATM", "today", "555"));
        cards.add(new Card("Bob", "House", "today", "555"));
        cards.add(new Card("Noob", "Stocks", "today", "555"));
        cards.add(new Card("Dude", "Any", "today", "555"));
        String[] cardNames = new String[cards.size()];
        for(int i = 0; i < cards.size(); i++) {
            cardNames[i] = cards.get(i).getId();
            cards.get(i).updateAmount(i * 1000);
        }

        DecimalFormat df = new DecimalFormat("#.00");

        JLabel dropdownLabel = new LabelMaker("Card: ", null, 30, 30,50,20);
        JComboBox<String> dropdown = new JComboBox<>(cardNames);
        dropdown.setBounds(100, 30, 200, 20);

        JPanel dropdownPanel = new PanelMaker(0, 0, 400, 100, null);
        dropdownPanel.add(dropdown);
        dropdownPanel.add(dropdownLabel);

        String selectedName = (String) dropdown.getSelectedItem();
        double balance = getCardByID(cards, selectedName).getExpenses();

        JPanel infoPanel = new PanelMaker(0, 100, 400, 200, new GridLayout(3, 2));
        JLabel transaction = new LabelMaker("Transaction Amount: ", null);
        JLabel transactionAmount = new LabelMaker("$" + df.format(amount), null);
        JLabel currentBalance = new LabelMaker("Current Balance: ", null);
        JLabel amountBefore = new LabelMaker("$" + df.format(balance), null);
        JLabel balanceAfter = new LabelMaker("Balance After Transaction: ", null);
        JLabel amountAfter = new LabelMaker("$" + df.format((balance + amount)), null);
        infoPanel.add(transaction);
        infoPanel.add(transactionAmount);
        infoPanel.add(currentBalance);
        infoPanel.add(amountBefore);
        infoPanel.add(balanceAfter);
        infoPanel.add(amountAfter);

        dropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedName = (String) dropdown.getSelectedItem();
                double balance = getCardByID(cards, selectedName).getExpenses();
                amountBefore.setText("$" + df.format(balance));
                amountAfter.setText("$" + df.format((balance + amount)));
            }
        });

        JPanel buttonPanel = new PanelMaker(0, 300, 400, 100, null);
        JButton confirm = new ButtonMaker("Submit", 50, 25, 100, 50) {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.confirm();
                controller.setSubmitPressed(true);
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

    public Card getCardByID(List<Card> cards, String id) {
        for(Card card: cards) {
            if(card.getId().equals(id)) return card;
        }
        return null;
    }

    public static void main(String[] args) {
        //JFrame frame = new PopUpTransactionView();
    }
}
