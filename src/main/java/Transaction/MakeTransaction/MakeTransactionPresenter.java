package Transaction.MakeTransaction;

import App.PresenterInterface;

public class MakeTransactionPresenter implements PresenterInterface<MakeTransactionController> {
    private final MakeTransactionView makeTransactionView;

    public MakeTransactionPresenter(MakeTransactionController controller){
        this.makeTransactionView = new MakeTransactionView(controller);
    }

    @Override
    public void showView(){
        makeTransactionView.setVisible(true);
    }

    @Override
    public void disposeView(){
        makeTransactionView.setVisible(false);
        makeTransactionView.dispose();
    }
}
