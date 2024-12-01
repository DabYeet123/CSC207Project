package loans.adapter;

import java.util.List;

import DataObjects.UserObject;
import loans.dataAccess.LoansDBAccess;
import loans.dataObject.LoansObject;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class LoansController {
    private final LoansDBAccess loansDBAccess = new LoansDBAccess();

    /**
     * Adds a new loan for the given user.
     *
     * @param userID   The ID of the user applying for the loan.
     * @param amount   The amount of the loan.
     * @param term     The term of the loan in years.
     * @param rate     The interest rate of the loan.
     * @param cardUsed The card used for loan repayment.
     * @return The updated user object after adding the loan.
     */
    public UserObject addLoans(int userID, double amount, int term, double rate, String cardUsed) {
        final LoansObject newLoan = new LoansObject(userID, amount, term, rate, cardUsed);
        return loansDBAccess.saveData(userID, newLoan);
    }

    /**
     * Retrieves all loans for the given user.
     *
     * @param userID The ID of the user.
     * @return A list of loans associated with the user.
     */
    public List<LoansObject> getAllLoans(int userID) {
        return loansDBAccess.readData(userID);
    }
}
