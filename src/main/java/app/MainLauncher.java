package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import brokerage.BrokerageUseCaseFactory;
import brokerage.BrokerageView;
import brokerage.interface_adapter.BrokerageViewModel;
import data_access.DBBrokerageDataAccessObject;
import data_access.DBTransactionDataAccessObject;
import data_access.DBUserDataAccessObject;
import interface_adapter.ViewManagerModel;
import loggedin.LoggedinUseCaseFactory;
import loggedin.LoggedinView;
import loggedin.interface_adapter.LoggedinViewModel;
import login.LoginView;
import login.interface_adapter.LoginViewModel;
import maketransaction.MakeTransactionView;
import maketransaction.interface_adapter.MakeTransactionViewModel;
import seetransactions.SeeTransactionsUseCaseFactory;
import seetransactions.SeeTransactionsView;
import seetransactions.interface_adapter.SeeTransactionsViewModel;
import maketransaction.MakeTransactionUseCaseFactory;
import signup.SignupView;
import signup.interface_adapter.SignupViewModel;
import login.LoginUseCaseFactory;
import signup.SignupUseCaseFactory;
import welcome.WelcomeView;
import welcome.interface_adapter.WelcomeViewModel;
import view.*;
import welcome.WelcomeUseCaseFactory;

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
        final BrokerageViewModel brokerageViewModel = new BrokerageViewModel();

        final DBUserDataAccessObject userDataAccessObject = new DBUserDataAccessObject();
        final DBTransactionDataAccessObject transactionDataAccessObject = new DBTransactionDataAccessObject();
        final DBBrokerageDataAccessObject brokerageDataAccessObject = new DBBrokerageDataAccessObject();

        final WelcomeView welcomeView = WelcomeUseCaseFactory.create(viewManagerModel, signupViewModel, loginViewModel);
        views.add(welcomeView, welcomeView.getViewName());
        final LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, welcomeViewModel, loggedinViewModel,
                loginViewModel, userDataAccessObject);
        views.add(loginView, loginView.getViewName());
        final SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, welcomeViewModel, loggedinViewModel,
                signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.getViewName());
        final LoggedinView loggedinView = LoggedinUseCaseFactory.create(viewManagerModel, welcomeViewModel,
                makeTransactionViewModel, seeTransactionsViewModel, brokerageViewModel, loggedinViewModel);
        views.add(loggedinView, loggedinView.getViewName());
        final MakeTransactionView makeTransactionView = MakeTransactionUseCaseFactory.create(viewManagerModel,
                loggedinViewModel, makeTransactionViewModel, transactionDataAccessObject);
        views.add(makeTransactionView, makeTransactionView.getViewName());
        final SeeTransactionsView seeTransactionsView = SeeTransactionsUseCaseFactory.create(viewManagerModel,
                loggedinViewModel, seeTransactionsViewModel, transactionDataAccessObject);
        views.add(seeTransactionsView, seeTransactionsView.getViewName());
        final BrokerageView brokerageView = BrokerageUseCaseFactory(viewManagerModel, brokerageViewModel, loggedinView
            brokerageDataAccessObject);
        views.add(brokerageView, brokerageView.getViewName());

        viewManagerModel.setState(welcomeView.getViewName());
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
