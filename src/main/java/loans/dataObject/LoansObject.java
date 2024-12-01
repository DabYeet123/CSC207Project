package loans.dataObject;

import java.time.LocalDate;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;

@Getter
@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class LoansObject implements Comparable<LoansObject> {
    private static final double PERCENT = 0.01;
    private int userID;
    private double amount;
    private LocalDate startDate;
    private LocalDate endDate;
    private double rate;
    private double repayment;
    private String cardUsed;

    /**
     * Default constructor for LoansObject.
     */
    public LoansObject() {

    }

    /**
     * Constructs a new LoansObject with the specified parameters.
     *
     * @param userID   The ID of the user applying for the loan.
     * @param amount   The amount of the loan.
     * @param term     The term of the loan in years.
     * @param rate     The interest rate of the loan.
     * @param cardUsed The card used for loan repayment.
     */
    public LoansObject(int userID, double amount, int term, double rate, String cardUsed) {
        this.userID = userID;
        this.amount = amount;
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now().plusYears(term).minusDays(1);
        this.rate = rate;
        this.repayment = amount * (1 + PERCENT * term * rate);
        this.cardUsed = cardUsed;
    }

    /**
     * Compares this LoansObject to another LoansObject by their end dates.
     *
     * @param o The other LoansObject to compare to.
     * @return A positive integer, zero, or a negative integer as this object's end date is
     *         after, equal to, or before the specified object's end date.
     */
    @Override
    public int compareTo(@NotNull LoansObject o) {
        return LoansMethods.sortByEndDate(this, o);
    }
}
