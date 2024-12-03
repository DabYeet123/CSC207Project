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

    /**
     * Default constructor for UserInsuranceObject.
     */
    public UserInsuranceObject() {

    }

    /**
     * Constructs a UserInsuranceObject with specified parameters.
     *
     * @param userID       The ID of the user.
     * @param insurance    The insurance object associated with the user.
     * @param term         The term duration of the insurance in years.
     * @param autoRenew    Indicates whether the insurance is set to auto-renew.
     * @param cardUsed     The card number used for payment.
     */
    public UserInsuranceObject(int userID, InsuranceObject insurance, int term, boolean autoRenew, String cardUsed) {
        this.userID = userID;
        this.insurance = insurance;
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now().plusYears(term).minusDays(1);
        this.autoRenew = autoRenew;
        this.cardUsed = cardUsed;
    }

    /**
     * Constructs a UserInsuranceObject with specified parameters, including start and end dates.
     *
     * @param userID       The ID of the user.
     * @param insurance    The insurance object associated with the user.
     * @param startDate    The start date of the insurance.
     * @param endDate      The end date of the insurance.
     * @param autoRenew    Indicates whether the insurance is set to auto-renew.
     * @param cardUsed     The card number used for payment.
     */
    public UserInsuranceObject(int userID, InsuranceObject insurance, LocalDate startDate, LocalDate endDate,
                               boolean autoRenew, String cardUsed) {
        this.userID = userID;
        this.insurance = insurance;
        this.startDate = startDate;
        this.endDate = endDate;
        this.autoRenew = autoRenew;
        this.cardUsed = cardUsed;
    }

    /**
     * Compares the current insurance object to another insurance object by insurance ID.
     *
     * @param o The other UserInsuranceObject to compare to.
     * @return A negative integer, zero, or a positive integer as this object is less than,
     *      equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(@NotNull UserInsuranceObject o) {
        return Integer.compare(this.insurance.getInsuranceID(), o.getInsurance().getInsuranceID());
    }
}
