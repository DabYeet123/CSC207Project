package oldstuff.Views.appold;

import oldstuff.Views.login.welcome.WelcomeController;

/**
 * MainLauncher class to start the application.
 * This class contains the entry point of the application, which initializes
 * and launches the WelcomeController. The WelcomeController is responsible for
 * managing the welcome screen and user authentication.
 */
public class MainLauncher {
    /**
     * The entry point of the application. This method initializes and launches the
     * WelcomeController, which handles the initial user interface and functionality.
     *
     * @param args the command line arguments (not used in this case)
     */
    public static void main(String[] args) {
        final WelcomeController welcomeController = new WelcomeController();
        welcomeController.launch();
    }
}
