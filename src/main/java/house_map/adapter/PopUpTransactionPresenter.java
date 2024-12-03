package house_map.adapter;

import App.PresenterInterface;
import DataObjects.UserObject;
import house_map.use_case.pop_up_transaction.PopUpTransactionOutput;
import house_map.use_case.pop_up_transaction.PopUpTransactionOutputBoundary;
import house_map.view.PopUpTransactionView;

/**
 * Presenter for the PopupTransaction.
 */
public class PopUpTransactionPresenter implements PresenterInterface<PopUpTransactionController>,
        PopUpTransactionOutputBoundary {
    private final PopUpTransactionView popUpTransactionView;

    public PopUpTransactionPresenter(UserObject user, PopUpTransactionController controller) {
        this.popUpTransactionView = new PopUpTransactionView(user, controller);
    }

    @Override
    public void showView() {
        popUpTransactionView.setVisible(true);
    }

    @Override
    public void disposeView() {
        popUpTransactionView.dispose();
    }

    @Override
    public void updateView(PopUpTransactionOutput popUpTransactionOutput) {
        final double price = popUpTransactionOutput.getPrice();
        popUpTransactionView.setPrice(price);
        popUpTransactionView.updateView();
    }

}
