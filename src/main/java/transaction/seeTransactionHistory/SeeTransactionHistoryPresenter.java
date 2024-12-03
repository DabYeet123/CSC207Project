package transaction.seeTransactionHistory;

import app.PresenterInterface;

/**
 * Presenter for managing the transaction history view. This class acts as an intermediary
 * between the controller and the view for displaying and disposing of the transaction history UI.
 */
public class SeeTransactionHistoryPresenter implements PresenterInterface<SeeTransactionHistoryController> {
    private final SeeTransactionHistoryView seeTransactionHistoryView;

    public SeeTransactionHistoryPresenter(SeeTransactionHistoryController controller) {
        this.seeTransactionHistoryView = new SeeTransactionHistoryView(controller);
    }

    @Override
    public void showView() {
        seeTransactionHistoryView.setVisible(true);
    }

    @Override
    public void disposeView() {
        seeTransactionHistoryView.setVisible(false);
        seeTransactionHistoryView.dispose();
    }
}
