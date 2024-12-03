package card.use_case;

public interface CardOutputBoundary {
    /**
     * Used to renew the GUI as a help function to add/delete card.
     * @param cardOutput is the card list
     */
    void refresh(CardOutput cardOutput);
}
