package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.DBTransactionDataAccessObject;
import data_access.DBUserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin.LoggedinViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.maketransaction.MakeTransactionViewModel;
import interface_adapter.seetransactions.SeeTransactionsViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import view.*;

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

        final ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // TODO: add here
        final WelcomeViewModel welcomeViewModel = new WelcomeViewModel();
        final LoginViewModel loginViewModel = new LoginViewModel();
        final SignupViewModel signupViewModel = new SignupViewModel();
        final LoggedinViewModel loggedinViewModel = new LoggedinViewModel();
        final MakeTransactionViewModel makeTransactionViewModel = new MakeTransactionViewModel();
        final SeeTransactionsViewModel seeTransactionsViewModel = new SeeTransactionsViewModel();

        final DBUserDataAccessObject userDataAccessObject = new DBUserDataAccessObject();
        final DBTransactionDataAccessObject transactionDataAccessObject = new DBTransactionDataAccessObject();

        final WelcomeView welcomeView = WelcomeUseCaseFactory.create(viewManagerModel, signupViewModel, loginViewModel);
        views.add(welcomeView, welcomeView.getViewName());
        final LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, welcomeViewModel, loggedinViewModel,
                loginViewModel, userDataAccessObject);
        views.add(loginView, loginView.getViewName());
        final SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, welcomeViewModel, loggedinViewModel,
                signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.getViewName());
        final LoggedinView loggedinView = LoggedinUseCaseFactory.create(viewManagerModel, welcomeViewModel,
                makeTransactionViewModel, seeTransactionsViewModel, loggedinViewModel);
        views.add(loggedinView, loggedinView.getViewName());
        final MakeTransactionView makeTransactionView = MakeTransactionUseCaseFactory.create(viewManagerModel,
                loggedinViewModel, makeTransactionViewModel, transactionDataAccessObject);
        views.add(makeTransactionView, makeTransactionView.getViewName());

        viewManagerModel.setState(welcomeView.getViewName());
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
