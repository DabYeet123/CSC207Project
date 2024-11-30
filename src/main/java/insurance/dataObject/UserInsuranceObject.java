package insurance.dataObject;

import java.time.LocalDate;

public class UserInsuranceObject {

    private int userID;
    private InsuranceObject insurance;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean autoRenew;
    private String cardNumber;

    public UserInsuranceObject() {

    }

    public UserInsuranceObject(int userID, InsuranceObject insurance, int term, boolean autoRenew, String cardNumber) {
        this.userID = userID;
        this.insurance = insurance;
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now().plusYears(term).minusDays(1);
        this.autoRenew = autoRenew;
        this.cardNumber = cardNumber;
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

    public String getCardNumber() {
        return cardNumber;
    }
}
