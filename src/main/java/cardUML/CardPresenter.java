package cardUML;

import app.PresenterInterface;
import transaction.makeTransaction.MakeTransactionController;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class CardPresenter implements PresenterInterface<MakeTransactionController>, CardOutputBoundary {
    private final CardView cardView;

    public CardPresenter(CardController controller) {
        this.cardView = new CardView(controller);
    }

    @Override
    public void refresh(CardOutput cardOutput) {
        cardView.getModel().setRowCount(0);
        for (Object[] oneCard : cardOutput.getNewLine()) {
            cardView.getModel().addRow(oneCard);
        }
        cardView.getUsageField().setText("");
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
