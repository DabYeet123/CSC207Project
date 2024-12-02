package Card;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Card {

     private final String id;
     private final String usage;
     private final String date;
     private final String code;
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

    public String getUsage() {
        return this.usage;
    }

    public String getDate() {
        return this.date;
    }

    public String getCode() {
        return this.code;
    }

    public double getExpenses() {
        return this.expenses;
    }

    public void updateAmount(double newAmount) {
        this.expenses -= newAmount;
    }
}
