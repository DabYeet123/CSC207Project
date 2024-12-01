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

    public LoansObject() {

    }

    public LoansObject(int userID, double amount, int term, double rate, String cardUsed) {
        this.userID = userID;
        this.amount = amount;
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now().plusYears(term).minusDays(1);
        this.rate = rate;
        this.repayment = amount * (1 + PERCENT * term * rate);
        this.cardUsed = cardUsed;
    }

    @Override
    public int compareTo(@NotNull LoansObject o) {
        return LoansMethods.sortByEndDate(this, o);
    }

}
