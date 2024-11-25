package ATM.DataObject;

import Views.ButtonMaker;
import Views.LabelMaker;
import Views.PanelMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ATMPopUp extends PanelMaker {

    public ATMPopUp(ATMObject atmObject, int x, int y, int width, int height) {
        super(x, y, width, height, new GridLayout(2, 1));
        JPanel labels = new PanelMaker(x, y, width, height / 2, new GridLayout(3, 1));
        JLabel name = new LabelMaker(atmObject.getName(), null);
        JLabel cash = new LabelMaker("Remaining Cash :" + atmObject.getRemainingCash(), null);
        JLabel fee = new LabelMaker("TransactionFee: " + atmObject.getTransactionFee(), null);
        labels.add(name);
        labels.add(cash);
        labels.add(fee);

        this.add(labels);

        JPanel buttons = new PanelMaker(0, 0, width, height / 2, new GridLayout(1, 2));
        JButton withdraw = new ButtonMaker("Withdraw") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("withdrawing");
            }
        };
        JButton deposit = new ButtonMaker("Deposit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("depositing");
            }
        };

        buttons.add(deposit);
        buttons.add(withdraw);
        this.add(buttons);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {

                Rectangle bounds = getBounds();
                int x = e.getPoint().x + bounds.x;
                int y = e.getPoint().y + bounds.y;
                if (bounds.contains(x, y)) {
                    setVisible(true); // Hide popup when mouse exits
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Rectangle bounds = getBounds();
                int x = e.getPoint().x + bounds.x;
                int y = e.getPoint().y + bounds.y;
                if (!bounds.contains(x, y)) {
                    setVisible(false); // Hide popup when mouse exits
                }
            }
        });

        this.setVisible(false);

    }

}
