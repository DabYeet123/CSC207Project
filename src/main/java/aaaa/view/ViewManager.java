package aaaa.view;

import java.awt.CardLayout;
import java.util.Objects;

import javax.swing.JPanel;

import aaaa.interface_adapter.login.LoginController;
import aaaa.interface_adapter.login.LoginPresenter;

/**
 * Manages transitions and state changes between various views in the application.
 * Responsible for displaying the appropriate view based on the current state.
 */
public class ViewManager {
    private static JPanel allViews;
    private static CardLayout cardLayout;
    private static String currentViewName;
    private final LoginController loginController = new LoginController();
    private final LoginPresenter loginPresenter = new LoginPresenter(this, getCurrentViewName());

    public ViewManager(JPanel views, CardLayout layout) {
        allViews = views;
        cardLayout = layout;
    }

    /**
     * Updates the view to the specified state.
     *
     * @param viewName the name of the view to display
     */
    public void setState(String viewName) {
        JPanel view = getCurrentView();
        cardLayout.show(allViews, viewName);
        currentViewName = viewName;
    }

    /**
     * Retrieves the view corresponding to the specified name.
     *
     * @return the {@code JFrame} of the view
     */
    private JPanel getCurrentView() {
        // TODO: fix logic
        JPanel newView = null;
        if (Objects.equals(currentViewName, "welcome")) {
            newView = new WelcomeView(this);
        }
        else if (Objects.equals(currentViewName, "login")) {
            newView = new LoginView(this);
        }

        /**
        else if (Objects.equals(viewName, "signup")) {
            newView = new SignUpView();
        }
        else if (Objects.equals(viewName, "loggedin")) {
            newView = new LoggedinView();
        }
        else if (Objects.equals(viewName, "maketransaction")) {
            newView = new MakeTransactionView();
        }
        else if (Objects.equals(viewName, "seetransaction")) {
            newView = new SeeTransactionsView();
        }
         */
        return newView;
    }

    public LoginController getLoginController() {
        return loginController;
    }
}

