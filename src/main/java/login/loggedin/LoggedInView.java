package login.loggedin;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
    private static final String FONT = "Arial";
    private final LoggedInController controller;

    public LoggedInView(LoggedInController controller) {
        this.controller = controller;
        setTitle("Logged In View");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(createTopPanel(), BorderLayout.NORTH);
        add(createButtonPanel(), BorderLayout.CENTER);
    }

    /**
     * Updates the view with the latest data from the ViewModel.
     * This method refreshes the displayed user information such as welcome message,
     * user ID, and account balance based on the provided ViewModel.
     *
     * @param viewModel The ViewModel that contains the updated data for the user.
     */
    public void updateView(LoggedInViewModel viewModel) {
        // Update UI components with the new ViewModel data
        final JLabel welcomeLabel = (JLabel) getComponentByName("welcomeLabel");
        final JLabel accountLabel = (JLabel) getComponentByName("accountLabel");
        final JLabel balanceLabel = (JLabel) getComponentByName("balanceLabel");

        welcomeLabel.setText(viewModel.getWelcomeMessage());
        accountLabel.setText("UserID: " + viewModel.getUserId());
        balanceLabel.setText("Balance: " + viewModel.getBalance());
    }

    private Component getComponentByName(String name) {
        Component newComponent = null;
        for (Component component : getContentPane().getComponents()) {
            if (component instanceof JLabel && ((JLabel) component).getText().contains(name)) {
                newComponent = component;
                break;
            }
        }
        return newComponent;
    }

    private JPanel createTopPanel() {
        final JPanel topPanel = new JPanel(new BorderLayout());
        final JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        final JLabel welcomeLabel = new JLabel("Welcome, ");
        welcomeLabel.setFont(new Font(FONT, Font.BOLD, FONT_SIZE));
        welcomeLabel.setName("welcomeLabel");

        final JLabel accountLabel = new JLabel("Account: ");
        accountLabel.setFont(new Font(FONT, Font.PLAIN, FONT_SIZE));
        accountLabel.setName("accountLabel");

        final JLabel balanceLabel = new JLabel("Balance: ");
        balanceLabel.setFont(new Font(FONT, Font.PLAIN, FONT_SIZE));
        balanceLabel.setName("balanceLabel");

        infoPanel.add(welcomeLabel);
        infoPanel.add(accountLabel);
        infoPanel.add(balanceLabel);

        topPanel.add(infoPanel, BorderLayout.CENTER);
        return topPanel;
    }

    private JPanel createButtonPanel() {
        final JPanel buttonPanel = new JPanel(new GridLayout(ROW_COUNT, COLUMN_COUNT, HORIZONTAL_GAP, VERTICAL_GAP));
        buttonPanel.add(createButton("Logout", event -> controller.logOutTriggered()));
        buttonPanel.add(createButton("Send Money", event -> controller.sendMoneyTriggered()));
        buttonPanel.add(createButton("Transaction History", event -> controller.seeTransactionHistoryTriggered()));
        buttonPanel.add(createButton("Card Options", event -> controller.cardTriggered()));
        buttonPanel.add(createButton("Currency Exchange", event -> controller.exchangeTriggered()));
        buttonPanel.add(createButton("Buy Assets", event -> controller.buyAssetsTriggered()));
        buttonPanel.add(createButton("Apply Loans", event -> controller.applyLoansTriggered()));
        buttonPanel.add(createButton("Loans History", event -> controller.seeLoansHistoryTriggered()));
        buttonPanel.add(createButton("ATM Map", event -> controller.atmMapTriggered()));
        buttonPanel.add(createButton("House Map", event -> controller.houseMapTriggered()));
        return buttonPanel;
    }

    private JButton createButton(String text, ActionListener action) {
        final JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(LOGOUT_WIDTH, LOGOUT_HEIGHT));
        button.addActionListener(action);
        return button;
    }
}
