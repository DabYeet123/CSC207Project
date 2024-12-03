package loans.dataAccess;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cardandexchange.adapter.CardController;
import dataaccess.DataAccessController;
import dataaccess.DataAccessInterface;
import loans.dataObject.LoansObject;
import userdataobject.UserObject;
import userdataobject.UsersController;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class LoansDBAccess implements DataAccessInterface<LoansObject> {
    private static final String LOANS_HISTORY_JSON = "\\LoansHistory.json";
    private final DataAccessController controller = new DataAccessController();
    private final UsersController usersController = new UsersController();

    /**
     * Saves a new loan for the specified user.
     *
     * @param userID The ID of the user.
     * @param loan   The loan object to be saved.
     * @return The updated user object after saving the loan.
     */
    @Override
    public UserObject saveData(int userID, LoansObject loan) {
        final UserObject user = usersController.getUser(userID);
        final double amount = loan.getAmount();

        final List<LoansObject> loans = controller.readData(user.getFileDirectory()
                + LOANS_HISTORY_JSON, LoansObject.class);
        loans.add(loan);
        Collections.sort(loans);
        controller.saveData(user.getFileDirectory() + LOANS_HISTORY_JSON, loans, LoansObject.class);
        updateBalance(user, amount);
        return user;
    }

    /**
     * Reads the loans data for the specified user.
     *
     * @param userID The ID of the user.
     * @return A list of loan objects associated with the user.
     */
    @Override
    public List<LoansObject> readData(int userID) {
        final UserObject user = usersController.getUser(userID);
        final CardController cardController = new CardController(user);
        final List<LoansObject> loans = controller.readData(user.getFileDirectory()
                + LOANS_HISTORY_JSON, LoansObject.class);
        final List<LoansObject> newLoans = new ArrayList<>();
        final LocalDate today = LocalDate.now();
        for (LoansObject loan : loans) {
            if (loan.getEndDate().isBefore(today)) {
                updateBalance(user, -loan.getRepayment());
                cardController.updateData(userID, loan.getCardUsed(), loan.getRepayment());
            }
            else {
                newLoans.add(loan);
            }
        }
        controller.saveData(user.getFileDirectory() + LOANS_HISTORY_JSON, newLoans, LoansObject.class);
        return newLoans;
    }

    /**
     * Updates the user's balance.
     *
     * @param user   The user whose balance is to be updated.
     * @param amount The amount to update the balance by (can be positive or negative).
     */
    private void updateBalance(UserObject user, double amount) {
        user.setBalance(user.getBalance() + amount);
        usersController.changeUser(user.getUserID(), user);
    }
}
