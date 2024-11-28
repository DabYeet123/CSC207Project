package aaaa.view;

import login.login.LogInView;
import login.signup.SignUpView;

import java.awt.*;
import java.util.Objects;

import javax.swing.*;

public class ViewManager {
    private static JFrame currentView = null;
    private static JPanel currentPanel;
    private static CardLayout cardLayout;

    public ViewManager(JPanel jPanel, CardLayout layout) {
        currentPanel = jPanel;
        cardLayout = layout;
    }

    public JFrame getCurrentView() {
        return currentView;
    }

    public void setState(String viewName) {
        JFrame currentView = getCurrentView(viewName);
        cardLayout.show(currentView, viewName);
    }

    private JFrame getCurrentView(String viewName) {
        // TODO: fix logic
        JPanel newView = null;
        if (Objects.equals(viewName, "welcome")) {
            newView = new WelcomeView();
        }
        else if (Objects.equals(viewName, "login")) {
            newView = new LogInView();
        }
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
        return newView;
    }
}
