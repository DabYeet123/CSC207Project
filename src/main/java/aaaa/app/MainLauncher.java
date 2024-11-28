package aaaa.app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import aaaa.view.LoginView;
import aaaa.view.ViewManager;
import aaaa.view.WelcomeView;

/**
 * MainLauncher class to start the application.
 * This class contains the entry point of the application.
 */
public class MainLauncher {
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;

    /**
     * The main method for starting the program with an external database used to persist user data.
     * @param args input to main
     */
    public static void main(String[] args) {
        final JFrame application = new JFrame("Start");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        application.setLocationRelativeTo(null);

        final CardLayout cardLayout = new CardLayout();
        final JPanel views = new JPanel(cardLayout);
        application.add(views);

        // TODO: add other Views
        /**
        final LoggedinView loggedinView = new LoggedinView();
        views.add(loggedinView, loggedinView.getViewName());
        final LoginView loginView = new LoginView();
        views.add(loginView, loginView.getViewName());
        final MakeTransactionView makeTransactionView = new MakeTransactionView();
        views.add(makeTransactionView, makeTransactionView.getViewName());
        final SeeTransactionsView seeTransactionsView = new SeeTransactionsView();
        views.add(seeTransactionsView, seeTransactionsView.getViewName());
         */

        final ViewManager viewManager = new ViewManager(views, cardLayout);
        final WelcomeView welcomeView = new WelcomeView(viewManager);
        views.add(welcomeView, welcomeView.getViewName());
        final LoginView loginView = new LoginView(viewManager);
        views.add(loginView, loginView.getViewName());

        viewManager.setState(welcomeView.getViewName());

        application.pack();
        application.setVisible(true);
    }
}
