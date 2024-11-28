package Card;

import App.PresenterInterface;
import Transaction.MakeTransaction.MakeTransactionController;

public class CardPresenter implements PresenterInterface<MakeTransactionController> {
    private final CardView cardView;

    public CardPresenter(CardController controller) {
        this.cardView = new CardView(controller);
    }

    @Override
    public void showView() {
        cardView.frame.setVisible(true);
        CardView.refresh(cardView);
    }

    @Override
    public void disposeView() {
        cardView.frame.setVisible(false);
        cardView.dispose();
    }
}
