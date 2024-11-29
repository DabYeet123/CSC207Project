package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import entity.User;
import interface_adapter.loggedin.LoggedinController;
import interface_adapter.loggedin.LoggedinState;
import interface_adapter.loggedin.LoggedinViewModel;

/**
 * A GUI view for logged-in users, displaying account details
 * and options to perform various actions.
 */
public class LoggedinView extends JPanel implements ActionListener, PropertyChangeListener {
    private static final int ROW_COUNT = 4;
    private static final int COLUMN_COUNT = 2;
    private static final int HORIZONTAL_GAP = 10;
    private static final int VERTICAL_GAP = 10;
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;
    private final String viewName = "loggedin";
    private final LoggedinViewModel loggedinViewModel;
    private final LoggedinController loggedinController;

    private final JButton makeTransaction;
    private final JButton seeTransactionHistory;
    private final JButton manageCards;
    private final JButton manageAssets;
    private final JButton manageHouses;
    private final JButton manageAtms;
    private final JButton manageLoans;
    private final JButton getLoan;
    private final JButton exchange;
    private final JButton logout;

    @SuppressWarnings("checkstyle:ExecutableStatementCount")
    public LoggedinView(LoggedinController controller, LoggedinViewModel loggedinViewModel) {
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.loggedinController = controller;
        this.loggedinViewModel = loggedinViewModel;
        this.loggedinViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Logged in Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LoggedinState currentState = loggedinViewModel.getState();
        final User loggedInUser = currentState.getUser();

        final JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(ROW_COUNT, COLUMN_COUNT, HORIZONTAL_GAP, VERTICAL_GAP));

        makeTransaction = new JButton("Make Transaction");
        buttons.add(makeTransaction);
        seeTransactionHistory = new JButton("See Transaction History");
        buttons.add(seeTransactionHistory);
        manageCards = new JButton("Manage Cards");
        buttons.add(manageCards);
        manageAssets = new JButton("Manage Assets");
        buttons.add(manageAssets);
        manageHouses = new JButton("Manage Houses");
        buttons.add(manageHouses);
        manageAtms = new JButton("Manage Atms");
        buttons.add(manageAtms);
        manageLoans = new JButton("Manage Loans");
        buttons.add(manageLoans);
        getLoan = new JButton("Get Loan");
        buttons.add(getLoan);
        exchange = new JButton("Exchange");
        buttons.add(exchange);
        logout = new JButton("Log Out");
        buttons.add(logout);

        this.add(title);
        add(createTopPanel(loggedInUser), BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);

        makeTransaction.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        loggedinController.switchToMakeTransactionView();
                    }
                }
        );
        seeTransactionHistory.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        loggedinController.switchToSeeTransactionHistoryView();
                    }
                }
        );
        // TODO: add other action listeners

        logout.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    /**
     * Creates the top panel containing the user's information and logout button.
     *
     * @param loggedInUser the controller for handling user actions.
     * @return the top panel component.
     */
    private JPanel createTopPanel(User loggedInUser) {
        final JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        final JLabel welcomeLabel = new JLabel("Welcome, " + loggedInUser.getFirstName() + " "
                + loggedInUser.getLastName() + "!");
        final JLabel accountLabel = new JLabel("UserID: " + loggedInUser.getUserID());
        final JLabel balanceLabel = new JLabel("Balance: $" + loggedInUser.getBalance());

        topPanel.add(welcomeLabel);
        topPanel.add(accountLabel);
        topPanel.add(balanceLabel);

        return topPanel;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
        if ("cancel".equals(evt.getActionCommand())) {
            loggedinController.switchToWelcomeView();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public String getViewName() {
        return viewName;
    }
}
