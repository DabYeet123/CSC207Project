package loans.dataObject;

import DataObjects.UserObject;

import java.util.List;

public class LoansController {
    LoansDBAccess loansDBAccess = new LoansDBAccess();

    public UserObject addLoans(int userID, double amount, int term, double rate, String cardUsed) {
        LoansObject newLoan = new LoansObject(userID, amount, term, rate, cardUsed);
        UserObject user = loansDBAccess.saveData(userID, newLoan);

        return user;
    }

    public List<LoansObject> getAllLoans(int userID) {
        return loansDBAccess.readData(userID);
    }
}
