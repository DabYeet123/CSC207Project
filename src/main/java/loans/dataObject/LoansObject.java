package loans.dataObject;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public class LoansObject implements Comparable<LoansObject> {
    int userID;
    double amount;
    LocalDate startDate;
    LocalDate endDate;
    double rate;
    double repayment;
    String cardUsed;

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

    public int getUserID() {
        return userID;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getRate() {
        return rate;
    }

    public double getRepayment() {
        return repayment;
    }

    public String getCardUsed() {
        return cardUsed;
    }

    public int compareTo(@NotNull LoansObject anotherLoan) {
        return sortByEndDate(anotherLoan);
    }

    public int sortByEndDate(LoansObject anotherLoan) {
        if (this.endDate.isAfter(anotherLoan.endDate)) {
            return 1;
        }
        else if (this.endDate.isBefore(anotherLoan.endDate)) {
            return -1;
        }
        else {
            return sortByStartDate(anotherLoan);
        }
    }

    public int sortByStartDate(LoansObject anotherLoan) {
        if (this.startDate.isAfter(anotherLoan.startDate)) {
            return 1;
        }
        else if (this.startDate.isBefore(anotherLoan.startDate)) {
            return -1;
        }
        else {
            return sortByRepayment(anotherLoan);
        }
    }

    public int sortByRepayment(LoansObject anotherLoan) {
        final Double x = this.repayment;
        final Double y = anotherLoan.repayment;
        return x.compareTo(y);
    }
}
