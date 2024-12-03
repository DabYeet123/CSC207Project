package house_map.use_case.pop_up_transaction;

import cardandexchange.dataObject.Card;
import userdataobject.UserObject;
import house_map.adapter.PopUpTransactionPresenter;

/**
 * Use case for popup transaction.
 */
public class PopUpTransactionUseCase implements PopUpTransactionInputBoundary {

    private final UserObject user;
    private final PopUpTransactionOutputBoundary popUpTransactionPresenter;

    private double price;
    private Card currentCard;

    public PopUpTransactionUseCase(UserObject user, PopUpTransactionOutputBoundary popUpTransactionPresenter) {
        this.user = user;
        this.popUpTransactionPresenter = popUpTransactionPresenter;
    }

    @Override
    public void execute(PopUpTransactionInput popUpTransactionInput) {
        final double price = popUpTransactionInput.getPrice();
        popUpTransactionPresenter.updateView(new PopUpTransactionOutput(price));
        popUpTransactionPresenter.showView();
    }

    /**
     * Updates the balance of the card.
     * @param price the price being updated
     */
    public void updateBalance(double price) {
        final Card card = currentCard;
        card.updateAmount(-price);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCurrentCard(Card card) {
        this.currentCard = card;
    }

}
