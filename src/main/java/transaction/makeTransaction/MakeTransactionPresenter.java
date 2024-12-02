package transaction.makeTransaction;

import appold.PresenterInterface;

/**
 * Presenter for managing the presentation logic of the make transaction view.
 * This class is responsible for displaying and disposing of the transaction view.
 */
public class MakeTransactionPresenter implements PresenterInterface<MakeTransactionController> {
    private final MakeTransactionView makeTransactionView;

    public MakeTransactionPresenter(MakeTransactionController controller) {
        this.makeTransactionView = new MakeTransactionView(controller);
    }

    @Override
    public void showView() {
        makeTransactionView.setVisible(true);
    }

    @Override
    public void disposeView() {
        makeTransactionView.setVisible(false);
        makeTransactionView.dispose();
    }
}
