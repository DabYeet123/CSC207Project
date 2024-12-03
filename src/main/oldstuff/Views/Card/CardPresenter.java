package oldstuff.Views.Card;

public class CardPresenter {
    private final CardView cardView;

    public CardPresenter(CardController controller) {
        this.cardView = new CardView(controller);
    }

    public void showView() {
        cardView.frame.setVisible(true);
        CardView.refresh(cardView);
    }

    public void disposeView() {
        cardView.frame.setVisible(false);
        cardView.dispose();
    }
}
