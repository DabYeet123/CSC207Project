package card.use_case;

public class CardInput {
    private final String name;

    public CardInput(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}