package house_map.data_access;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cardandexchange.dataObject.Card;
import cardandexchange.dataAccess.CardDBAccess;
import userdataobject.UserObject;

/**
 * Holds all the card data associated with the user.
 */
public class CardData {

    private final List<Card> cards;
    private final Map<String, String> cardNames;

    public CardData(UserObject user) {
        final CardDBAccess cardDBAccess = new CardDBAccess();
        this.cards = cardDBAccess.readData(user.getUserID());
        this.cardNames = createCardNames();
    }

    /**
     * Generates a mapping that associates the card's usage to its id.
     * @return a mapping that takes in the card's usage and returns the card's id
     */
    public Map<String, String> createCardNames() {
        final Map<String, String> cardMap = new HashMap<>();
        for (Card card : cards) {
            cardMap.put(card.getUsage(), card.getId());
        }
        return cardMap;
    }

    /**
     * Returns the card with the name.
     * @param name the card's usage
     * @return the card with the name
     */
    public Card getCardByName(String name) {
        Card correct = null;
        for (Card card: cards) {
            if (card.getUsage().equals(name)) {
                correct = card;
                break;
            }
        }
        return correct;
    }

    public Map<String, String> getCardNames() {
        return cardNames;
    }
}
