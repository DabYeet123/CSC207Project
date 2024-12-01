package insurance.dataObject;

import java.time.LocalDate;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;

@Getter
@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class UserInsuranceObject implements Comparable<UserInsuranceObject> {

    private int userID;
    private InsuranceObject insurance;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean autoRenew;
    private String cardUsed;

    public UserInsuranceObject() {

    }

    public UserInsuranceObject(int userID, InsuranceObject insurance, int term, boolean autoRenew, String cardUsed) {
        this.userID = userID;
        this.insurance = insurance;
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now().plusYears(term).minusDays(1);
        this.autoRenew = autoRenew;
        this.cardUsed = cardUsed;
    }

    public UserInsuranceObject(int userID, InsuranceObject insurance, LocalDate startDate, LocalDate endDate,
                               boolean autoRenew, String cardUsed) {
        this.userID = userID;
        this.insurance = insurance;
        this.startDate = startDate;
        this.endDate = endDate;
        this.autoRenew = autoRenew;
        this.cardUsed = cardUsed;
    }

    @Override
    public int compareTo(@NotNull UserInsuranceObject o) {
        return Integer.compare(this.insurance.getInsuranceID(), o.getInsurance().getInsuranceID());
    }
}
