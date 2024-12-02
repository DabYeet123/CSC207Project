package brokerage.interface_adapter;

public class BrokerageViewModel extends ViewModel<BrokerageState>{

    public BrokerageViewModel(){
        super("brokerage");
        setState(new BrokerageState());
    }
}
