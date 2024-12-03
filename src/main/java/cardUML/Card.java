package cardUML;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class Card {

    private final String id;
    @Getter
    private final String usage;
    @Getter
    private final String date;
    @Getter
    private final String code;
    @Getter
    private double expenses;

    @JsonCreator
    public Card(@JsonProperty("id") String id,
                @JsonProperty("name")String usage,
                @JsonProperty("expiryDate")String date,
                @JsonProperty("securityCode")String code) {
        this.id = id;
        this.usage = usage;
        this.date = date;
        this.code = code;
        this.expenses = 0;
    }

    @JsonProperty
    public String getId() {
        return this.id;
    }

    /**
     * Update .
     * @param newAmount update new amount
     */
    public void updateAmount(double newAmount) {
        this.expenses -= newAmount;
    }
}
