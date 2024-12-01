package cardandexchange.adapter;

import app.PresenterInterface;
import cardandexchange.view.CardView;
import transaction.makeTransaction.MakeTransactionController;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class CardPresenter implements PresenterInterface<MakeTransactionController> {
    private final CardView cardView;

    public CardPresenter(CardController controller) {
        this.cardView = new CardView(controller);
    }

    @Override
    public void showView() {
        cardView.getFrame().setVisible(true);
    }

    @Override
    public void disposeView() {
        cardView.getFrame().setVisible(false);
        cardView.dispose();
    }
}
