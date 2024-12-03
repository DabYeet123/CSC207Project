package loans.useCase;

import loans.dataObject.LoansObject;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class LoansMethods {

    /**
     * Compares two loan objects by their end dates.
     *
     * @param loanX The first loan object to be compared.
     * @param loanY The second loan object to be compared.
     * @return A positive integer if loanX's end date is after loanY's, a negative integer if loanX's end date
     *         is before loanY's, or the result of comparing their start dates if the end dates are equal.
     */
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

    /**
     * Compares two loan objects by their start dates.
     *
     * @param loanX The first loan object to be compared.
     * @param loanY The second loan object to be compared.
     * @return A positive integer if loanX's start date is after loanY's, a negative integer if loanX's start date
     *         is before loanY's, or the result of comparing their repayment amounts if the start dates are equal.
     */
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

    /**
     * Compares two loan objects by their repayment amounts.
     *
     * @param loanX The first loan object to be compared.
     * @param loanY The second loan object to be compared.
     * @return A positive integer if loanX's repayment is greater than loanY's, a negative integer if it is less,
     *         or zero if they are equal.
     */
    public static int sortByRepayment(LoansObject loanX, LoansObject loanY) {
        final Double x = loanX.getRepayment();
        final Double y = loanY.getRepayment();
        return x.compareTo(y);
    }
}
