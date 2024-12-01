package loans.dataObject;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class LoansMethods {

    public static int sortByEndDate(LoansObject loanX, LoansObject loanY) {
        final int res;
        if (loanX.getEndDate().isAfter(loanY.getEndDate())) {
            res = 1;
        }
        else if (loanX.getEndDate().isBefore(loanY.getEndDate())) {
            res = -1;
        }
        else {
            res = sortByStartDate(loanX, loanY);
        }
        return res;
    }

    public static int sortByStartDate(LoansObject loanX, LoansObject loanY) {
        final int res;
        if (loanX.getStartDate().isAfter(loanY.getStartDate())) {
            res = 1;
        }
        else if (loanX.getStartDate().isBefore(loanY.getStartDate())) {
            res = -1;
        }
        else {
            res = sortByRepayment(loanX, loanY);
        }
        return res;
    }

    public static int sortByRepayment(LoansObject loanX, LoansObject loanY) {
        final Double x = loanX.getRepayment();
        final Double y = loanY.getRepayment();
        return x.compareTo(y);
    }
}
