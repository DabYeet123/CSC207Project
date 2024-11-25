package Transaction.SeeTransactionHistory;

import App.PresenterInterface;

public class SeeTransactionHistoryPresenter implements PresenterInterface<SeeTransactionHistoryController> {
    private final SeeTransactionHistoryView seeTransactionHistoryView;

    public SeeTransactionHistoryPresenter(SeeTransactionHistoryController controller){
        this.seeTransactionHistoryView = new SeeTransactionHistoryView(controller);
    }

    @Override
    public void showView(){
        seeTransactionHistoryView.setVisible(true);
    }

    @Override
    public void disposeView(){
        seeTransactionHistoryView.setVisible(false);
        seeTransactionHistoryView.dispose();
    }
}
