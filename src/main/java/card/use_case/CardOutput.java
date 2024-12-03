package card.use_case;

import java.util.ArrayList;
import java.util.List;

import card.adapter.CardController;
import card.dataObject.Card;
import lombok.Getter;

@Getter
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

}
