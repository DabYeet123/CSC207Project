package card.use_case;

import card.adapter.CardController;
import card.dataObject.Card;

import java.util.ArrayList;
import java.util.List;

public class CardOutput {
    private final List<Object[]> newLine;

    public CardOutput() {
        final List<Object[]> listCard = new ArrayList<>();
        for (Card card : CardController.getCardList()) {
            listCard.add(new Object[]{card.getId(),
                    card.getUsage(),
                    card.getDate(),
                    card.getCode(),
                    card.getExpenses() + "$"});
        }
        this.newLine = listCard;
    }

    public List<Object[]> getNewLine() {
        return newLine;
    }
}
