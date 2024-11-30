package loans.dataObject;

import java.time.LocalDate;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;

@Getter
public class LoansObject implements Comparable<LoansObject> {
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
        this.repayment = amount * (1 + 0.01 * term * rate);
        this.cardUsed = cardUsed;
    }

    public int compareTo(@NotNull LoansObject anotherLoan) {
        return sortByEndDate(anotherLoan);
    }

    public int sortByEndDate(LoansObject anotherLoan) {
        final int res;
        if (this.endDate.isAfter(anotherLoan.endDate)) {
            res = 1;
        }
        else if (this.endDate.isBefore(anotherLoan.endDate)) {
            res = -1;
        }
        else {
            res = sortByStartDate(anotherLoan);
        }
        return res;
    }

    public int sortByStartDate(LoansObject anotherLoan) {
        final int res;
        if (this.startDate.isAfter(anotherLoan.startDate)) {
            res = 1;
        }
        else if (this.startDate.isBefore(anotherLoan.startDate)) {
            res = -1;
        }
        else {
            res = sortByRepayment(anotherLoan);
        }
        return res;
    }

    public int sortByRepayment(LoansObject anotherLoan) {
        final Double x = this.repayment;
        final Double y = anotherLoan.repayment;
        return x.compareTo(y);
    }
}
