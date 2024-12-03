package brokerage;

import brokerage.entity.StockFactory;
import brokerage.interface_adapter.BrokerageController;
import brokerage.interface_adapter.BrokeragePresenter;
import brokerage.interface_adapter.BrokerageViewModel;
import brokerage.use_case.BrokerageInputBoundary;
import brokerage.use_case.BrokerageInteractor;
import brokerage.use_case.BrokerageOutputBoundary;
import data_access.DBBrokerageDataAccessObject;
import interface_adapter.ViewManagerModel;
import loggedin.interface_adapter.LoggedinViewModel;

/**
 * This class contains the static factory function for creating the Brokerage View.
 */
public class BrokerageUseCaseFactory {
    /** Prevent instantiation. */
    private BrokerageUseCaseFactory() {

    }

    /**
     * Factory function for creating the WelcomeView.
     * @param viewManagerModel the ViewManagerModel to inject into the SignupView
     * @param brokerageViewModel the LoginViewModel to inject into the SignupView
     * @param loggedinViewModel the LoginViewModel to inject into the SignupView
     * @param brokerageDataAccessObject brokerage data access object
     * @return the WelcomeView created for the provided input classes
     */
    public static BrokerageView create(
            ViewManagerModel viewManagerModel, BrokerageViewModel brokerageViewModel,
            LoggedinViewModel loggedinViewModel, DBBrokerageDataAccessObject brokerageDataAccessObject) {

        final BrokerageController brokerageController = createBrokerageUseCase(viewManagerModel, brokerageViewModel,
                loggedinViewModel, brokerageDataAccessObject);

        return new BrokerageView(brokerageController, brokerageViewModel);
    }

    /**
     * Returns the brokerage controller.
     * @param viewManagerModel view manager model
     * @param brokerageViewModel brokerage view model
     * @param loggedinViewModel logged in view model
     * @param brokerageDataAccessObject brokerage data access
     * @return the brokerage controller
     */
    public static BrokerageController createBrokerageUseCase(ViewManagerModel viewManagerModel,
                                                              BrokerageViewModel brokerageViewModel,
                                                              LoggedinViewModel loggedinViewModel,
                                                              DBBrokerageDataAccessObject brokerageDataAccessObject) {

        final BrokerageOutputBoundary brokerageOutputBoundary = new BrokeragePresenter(brokerageViewModel,
                loggedinViewModel, viewManagerModel);

        final StockFactory stockFactory = new StockFactory();

        final BrokerageInputBoundary brokerageInputBoundary = new BrokerageInteractor(brokerageDataAccessObject,
                brokerageOutputBoundary);

        return new BrokerageController(brokerageInputBoundary);
    }
}
