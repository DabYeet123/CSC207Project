package card.use_case;

import lombok.Getter;

@Getter
public class CardInput {
    private final String name;

    public CardInput(String name) {
        this.name = name;
    }

}
