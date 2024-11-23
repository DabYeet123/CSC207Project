package LogIn.LoggedIn;

import DataObjects.UserObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoggedInView extends JFrame {
    UserObject user;

    public LoggedInView(LoggedInController controller) {
        this.user = controller.loggedInUser;

        setTitle("Logged In View");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());

        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(80, 25));
        logoutPanel.add(logoutButton);
        topPanel.add(logoutPanel, BorderLayout.EAST);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        JLabel welcomeLabel = new JLabel("Welcome, " + user.getFirstName() + " " + user.getLastName() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel accountLabel = new JLabel("UserID: " + user.getUserID());
        JLabel balanceLabel = new JLabel("Balance: $" + user.getBalance());

        infoPanel.add(welcomeLabel);
        infoPanel.add(accountLabel);
        infoPanel.add(balanceLabel);
        topPanel.add(infoPanel, BorderLayout.WEST);

        add(topPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 2, 10, 10));

        JButton sendMoneyButton = new JButton("Send Money");
        sendMoneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.sendMoneyTriggered();
            }
        });

        JButton transactionsButton = new JButton("Transactions");
        transactionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.seeTransactionHistoryTriggered();
            }
        });

        JButton cardsButton = new JButton("Cards");
        cardsButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Cards functionality"));

        JButton housesButton = new JButton("Houses near me");
        housesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.houseMapTriggered();
            }
        });

        JButton assetsButton = new JButton("Assets");
        assetsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.buyAssetsTriggered();
            }
        });

        JButton loansButton = new JButton("Loans");
        loansButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Loans functionality"));

        JButton atmsButton = new JButton("ATMMap");
        atmsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.atmMapTriggered();
            }
        });

        JButton exchangesButton = new JButton("Exchange");
        exchangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.exchangesTriggered();
            }
        });

        buttonPanel.add(sendMoneyButton);
        buttonPanel.add(transactionsButton);
        buttonPanel.add(cardsButton);
        buttonPanel.add(housesButton);
        buttonPanel.add(assetsButton);
        buttonPanel.add(loansButton);
        buttonPanel.add(atmsButton);
        buttonPanel.add(exchangesButton);

        add(buttonPanel, BorderLayout.CENTER);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.logOutTriggered();
            }
        });
    }
}
