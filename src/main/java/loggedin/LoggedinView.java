package loggedin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import entity.User;
import loggedin.interface_adapter.LoggedinController;
import loggedin.interface_adapter.LoggedinState;
import loggedin.interface_adapter.LoggedinViewModel;

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

    private User loggedinUser;
    private final JLabel name;
    private final JLabel balance;

    private final JLabel userErrorField = new JLabel();
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

        final JLabel userInfo = new JLabel("Currently logged in: ");
        name = new JLabel();
        final JLabel balanceInfo = new JLabel("Balance: ");
        balance = new JLabel();

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
        this.add(userInfo);
        this.add(name);
        this.add(balanceInfo);
        this.add(balance);

        makeTransaction.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        loggedinController.switchToMakeTransactionView(loggedinUser);
                    }
                }
        );
        seeTransactionHistory.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        loggedinController.switchToSeeTransactionHistoryView(loggedinUser);
                    }
                }
        );
        // TODO: add other action listeners

        logout.addActionListener(this);

        add(buttons, BorderLayout.CENTER);
        this.add(userErrorField);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
        if ("Log Out".equals(evt.getActionCommand())) {
            loggedinController.switchToWelcomeView();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final LoggedinState state = (LoggedinState) evt.getNewValue();
        loggedinUser = state.getUser();
        name.setText(state.getUser().getFirstName() + " " + state.getUser().getLastName());
        balance.setText(String.valueOf(state.getUser().getBalance()));
    }

    public String getViewName() {
        return viewName;
    }
}
