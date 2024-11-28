package HouseMap.UseCase.PopUpTransaction;

import Card.Card;
import Card.CardDBAccess;
import DataObjects.UserObject;
import HouseMap.Adapter.PopUpTransactionController;
import HouseMap.Adapter.PopUpTransactionPresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PopUpTransactionUseCase implements PopUpTransactionIB{

    private UserObject user;
    private final PopUpTransactionPresenter popUpTransactionPresenter;

    private double price;
    private Card currentCard;

    public PopUpTransactionUseCase(UserObject user, PopUpTransactionPresenter popUpTransactionPresenter) {
        this.user = user;
        this.popUpTransactionPresenter = popUpTransactionPresenter;
    }

    @Override
    public void execute(PopUpTransactionInput popUpTransactionInput) {
        double price = popUpTransactionInput.getPrice();
        popUpTransactionPresenter.updateView(new PopUpTransactionOutput(price));
        popUpTransactionPresenter.showView();
    }

    public void updateBalance(double price) {
        Card card = currentCard;
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
