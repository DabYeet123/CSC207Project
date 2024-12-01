package insurance.dataObject;

import java.time.LocalDate;

import org.jetbrains.annotations.NotNull;

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

    public UserInsuranceObject(int userID, InsuranceObject insurance, LocalDate startDate, LocalDate endDate, boolean autoRenew, String cardUsed) {
        this.userID = userID;
        this.insurance = insurance;
        this.startDate = startDate;
        this.endDate = endDate;
        this.autoRenew = autoRenew;
        this.cardUsed = cardUsed;
    }

    public int getUserID() {
        return userID;
    }

    public InsuranceObject getInsurance() {
        return insurance;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public boolean isAutoRenew() {
        return autoRenew;
    }

    public String getCardUsed() {
        return cardUsed;
    }

    @Override
    public int compareTo(@NotNull UserInsuranceObject o) {
        final int res;
        if (this.insurance.getInsuranceID() > o.getInsurance().getInsuranceID()) {
            res = 1;
        }
        else if (this.insurance.getInsuranceID() < o.getInsurance().getInsuranceID()) {
            res = -1;
        }
        else {
            res = 0;
        }
        return res;
    }
}
