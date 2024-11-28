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
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());

        JPanel refreshPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setPreferredSize(new Dimension(80, 25));
        refreshPanel.add(refreshButton);
        topPanel.add(refreshPanel, BorderLayout.CENTER);

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
        buttonPanel.setLayout(new GridLayout(5, 2, 10, 10));

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
        cardsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.cardTriggered();
            }
        });

        JButton atmsButton = new JButton("ATMs near me");
        atmsButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Find ATMs near you functionality"));

        JButton loansButton = new JButton("Apply Loans");
        loansButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) { controller.applyLoansTriggered(); }
        });

        JButton loansHistoryButton = new JButton("Loans History");
        loansHistoryButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) { controller.seeLoansHistoryTriggered(); }
        });

        JButton insuranceButton = new JButton("Purchase Insurance");
        insuranceButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) { controller.purchaseInsuranceTriggered(); }
        });

        JButton myInsuranceButton = new JButton("My Insurance");
        myInsuranceButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) { controller.myInsuranceTriggered(); }
        });

        JButton assetsButton = new JButton("Assets");
        assetsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.buyAssetsTriggered();
            }
        });

        // Button of exchange button, find if we need that button in the main flame
        JButton exchangeButton = new JButton("Currency Exchange");
        exchangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.exchangeTriggered();
            }
        });

        buttonPanel.add(sendMoneyButton);
        buttonPanel.add(transactionsButton);
        buttonPanel.add(cardsButton);
        buttonPanel.add(atmsButton);
        buttonPanel.add(loansButton);
        buttonPanel.add(loansHistoryButton);
        buttonPanel.add(insuranceButton);
        buttonPanel.add(myInsuranceButton);
        buttonPanel.add(assetsButton);
        buttonPanel.add(exchangeButton);

        add(buttonPanel, BorderLayout.CENTER);

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.refreshTriggered();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.logOutTriggered();
            }
        });
    }
}
