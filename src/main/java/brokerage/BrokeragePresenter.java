package brokerage;

import app.PresenterInterface;
import brokerage.view.BrokerageView;

/**
 * Handles the presentation layer for the brokerage system, managing the interaction
 * between the controller and the view.
 */

public class BrokeragePresenter implements PresenterInterface<BrokerageController> {
    private final BrokerageView brokerageView;

    public BrokeragePresenter(BrokerageController controller) {
        brokerageView = new BrokerageView(controller);
    }

    @Override
    public void showView() {
        brokerageView.setVisible(true);
    }

    @Override
    public void disposeView() {
        brokerageView.setVisible(false);
        brokerageView.dispose();
    }

}
