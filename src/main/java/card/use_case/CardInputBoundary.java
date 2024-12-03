package card.use_case;

import card.dataObject.Card;

public interface CardInputBoundary {
    /**
     * Generates the map for the given address.
     * @param name the name to generate
     */
    Card generateCard(String name);

    /**
     * Generates the map for the given address.
     * @param cardInput card Input
     */
    Card addCard(CardInput cardInput);
}
