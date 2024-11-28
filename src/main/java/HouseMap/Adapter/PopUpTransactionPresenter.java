package HouseMap.Adapter;

import App.PresenterInterface;
import Card.Card;
import DataObjects.UserObject;
import HouseMap.UseCase.PopUpTransaction.PopUpTransactionOB;
import HouseMap.UseCase.PopUpTransaction.PopUpTransactionOutput;
import HouseMap.View.PopUpTransactionView;

public class PopUpTransactionPresenter implements PresenterInterface<PopUpTransactionController>, PopUpTransactionOB {
    private PopUpTransactionView popUpTransactionView;

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
        double price = popUpTransactionOutput.getPrice();
        popUpTransactionView.setPrice(price);
        popUpTransactionView.updateView();
    }

}
