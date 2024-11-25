package Transaction.PopUpTransaction;

import App.PresenterInterface;

public class PopUpTransactionPresenter implements PresenterInterface<PopUpTransactionController> {
    private PopUpTransactionView popUpTransactionView;

    public PopUpTransactionPresenter(PopUpTransactionController controller) {
        this.popUpTransactionView = new PopUpTransactionView(controller);
    }

    @Override
    public void showView() {
        popUpTransactionView.setVisible(true);
    }

    @Override
    public void disposeView() {
        popUpTransactionView.dispose();
    }
}
