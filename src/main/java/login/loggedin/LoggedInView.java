package login.loggedin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import userdataobject.UserObject;

/**
 * A GUI view for logged-in users, displaying account details
 * and options to perform various actions.
 */
public class LoggedInView extends JFrame {
    private static final int ROW_COUNT = 4;
    private static final int COLUMN_COUNT = 2;
    private static final int HORIZONTAL_GAP = 10;
    private static final int VERTICAL_GAP = 10;
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;
    private static final int LOGOUT_WIDTH = 80;
    private static final int LOGOUT_HEIGHT = 25;
    private static final int FONT_SIZE = 16;
    private UserObject user;

    public LoggedInView(LoggedInController controller) {
        this.user = controller.getLoggedInUser();

        setTitle("Logged In View");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(createTopPanel(controller), BorderLayout.NORTH);
        add(createButtonPanel(controller), BorderLayout.CENTER);
    }

    /**
     * Creates the top panel containing the user's information and logout button.
     *
     * @param controller the controller for handling user actions.
     * @return the top panel component.
     */
    private JPanel createTopPanel(LoggedInController controller) {
        final JPanel topPanel = new JPanel(new BorderLayout());

        final JPanel refreshPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        final JButton refreshButton = new JButton("Refresh");
        refreshButton.setPreferredSize(new Dimension(LOGOUT_WIDTH, LOGOUT_HEIGHT));
        refreshPanel.add(refreshButton);
        topPanel.add(refreshPanel, BorderLayout.CENTER);

        final JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        final JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(LOGOUT_WIDTH, LOGOUT_HEIGHT));
        logoutPanel.add(logoutButton);
        topPanel.add(logoutPanel, BorderLayout.EAST);

        final JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        final JLabel welcomeLabel = new JLabel("Welcome, " + user.getFirstName() + " " + user.getLastName() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        final JLabel accountLabel = new JLabel("UserID: " + user.getUserID());
        final JLabel balanceLabel = new JLabel("Balance: $" + user.getBalance());

        infoPanel.add(welcomeLabel);
        infoPanel.add(accountLabel);
        infoPanel.add(balanceLabel);
        topPanel.add(infoPanel, BorderLayout.WEST);

        refreshButton.addActionListener(event -> controller.refreshTriggered());
        logoutButton.addActionListener(event -> controller.logOutTriggered());

        return topPanel;
    }

    /**
     * Creates the central button panel with various user action options.
     *
     * @param controller the controller for handling user actions.
     * @return the button panel component.
     */
    private JPanel createButtonPanel(LoggedInController controller) {
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(ROW_COUNT, COLUMN_COUNT, HORIZONTAL_GAP, VERTICAL_GAP));

        final JButton sendMoneyButton = createButton("Send Money", controller::sendMoneyTriggered);
        final JButton transactionsButton = createButton("Transactions", controller::seeTransactionHistoryTriggered);
        final JButton cardsButton = createButton("Cards", controller::cardTriggered);
        final JButton atmsButton = createButton("ATMs near me", controller::atmMapTriggered);
        final JButton housesButton = createButton("Houses near me", controller::houseMapTriggered);
        final JButton assetsButton = createButton("Assets", controller::buyAssetsTriggered);
        final JButton loansButton = createButton("Apply Loans", controller::applyLoansTriggered);
        final JButton loansHistoryButton = createButton("Loans History", controller::seeLoansHistoryTriggered);
        final JButton exchangeButton = createButton("Currency Exchange", controller::exchangeTriggered);
        final JButton insuranceButton = createButton("Purchase Insurance", controller::purchaseInsuranceTriggered);
        final JButton myInsuranceButton = createButton("My Insurance", controller::myInsuranceTriggered);

        buttonPanel.add(sendMoneyButton);
        buttonPanel.add(transactionsButton);
        buttonPanel.add(cardsButton);
        buttonPanel.add(atmsButton);
        buttonPanel.add(housesButton);
        buttonPanel.add(assetsButton);
        buttonPanel.add(loansButton);
        buttonPanel.add(loansHistoryButton);
        buttonPanel.add(exchangeButton);
        buttonPanel.add(insuranceButton);
        buttonPanel.add(myInsuranceButton);

        return buttonPanel;
    }

    /**
     * Creates a button and assigns an action to it.
     *
     * @param text     the text displayed on the button.
     * @param action   the action to be performed when the button is clicked.
     * @return the created button.
     */
    private JButton createButton(String text, Runnable action) {
        final JButton button = new JButton(text);
        button.addActionListener(event -> action.run());
        return button;
    }
}
