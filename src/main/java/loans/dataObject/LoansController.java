package loans.dataObject;

import java.util.List;

import DataObjects.UserObject;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class LoansController {
    private final LoansDBAccess loansDBAccess = new LoansDBAccess();

    public UserObject addLoans(int userID, double amount, int term, double rate, String cardUsed) {
        final LoansObject newLoan = new LoansObject(userID, amount, term, rate, cardUsed);
        return loansDBAccess.saveData(userID, newLoan);
    }

    public List<LoansObject> getAllLoans(int userID) {
        return loansDBAccess.readData(userID);
    }
}
