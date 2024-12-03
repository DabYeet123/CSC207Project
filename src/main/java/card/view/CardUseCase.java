package card.view;

public class CardUseCase implements CardInputBoundary{
    private final CardPresenter cardPresenter;

    public CardUseCase(CardPresenter cardPresenter) {
        this.cardPresenter = cardPresenter;
    }

    @Override
    public Card generateCard(String name) {
        final String securityCode = CardMethods.newCode();
        final String id = CardMethods.newId(name);
        final String expiryDate = CardMethods.newDate();
        return new Card(id, name, securityCode, expiryDate);
    }

    @Override
    public Card addCard(CardInput cardInput) {
        final String name = cardInput.getName();
        return generateCard(name);
    }
}
