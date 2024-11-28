package HouseMap.DataAccess;

import Card.CardDBAccess;
import Card.Card;
import DataObjects.UserObject;
import HouseMap.DataObject.HouseObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardData {

    private final List<Card> cards;

    public CardData(UserObject user) {
        CardDBAccess cardDBAccess = new CardDBAccess();
        this.cards = cardDBAccess.readData(user.getUserID());
        Map<String, String> cardNames = getCardNames();
    }


    public List<Card> getCards() {
        return cards;
    }

    public Map<String, String> getCardNames() {
        Map<String, String> cardMap = new HashMap<>();
        for (Card card : cards) {
            cardMap.put(card.getUsage(), card.getId());
        }
        return cardMap;
    }

    public Card getCardByName(String name) {
        for(Card card: cards) {
            if(card.getUsage().equals(name)) return card;
        }
        return null;
    }
}
