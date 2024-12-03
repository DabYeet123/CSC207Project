package brokerage.interface_adapter;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Brokerage View.
 */
public class BrokerageViewModel extends ViewModel<BrokerageState> {
    public BrokerageViewModel() {
        super("brokerage");
        setState(new BrokerageState());
    }
}
