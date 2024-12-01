package loans.dataObject;

import Card.CardController;
import DataAccess.DataAccessController;
import DataAccess.DataAccessInterface;
import DataObjects.UserObject;
import DataObjects.UsersController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoansDBAccess implements DataAccessInterface<LoansObject> {
    DataAccessController controller = new DataAccessController();
    UsersController usersController = new UsersController();

    @Override
    public UserObject saveData(int userID, LoansObject loan) {
        final UserObject user = usersController.getUser(userID);
        final double amount = loan.getAmount();

        final List<LoansObject> loans = controller.readData(user.getFileDirectory() + "\\LoansHistory.json", LoansObject.class);
        loans.add(loan);
        Collections.sort(loans);
        controller.saveData(user.getFileDirectory() + "\\LoansHistory.json", loans, LoansObject.class);
        updateBalance(user, amount);
        return user;
    }

    @Override
    public List<LoansObject> readData(int userID) {
        UserObject user = usersController.getUser(userID);
        CardController cardController = new CardController(user);
        List<LoansObject> loans = controller.readData(user.getFileDirectory() + "\\LoansHistory.json", LoansObject.class);
        List<LoansObject> newLoans = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (LoansObject loan : loans) {
            if (loan.getEndDate().isBefore(today)) {
                updateBalance(user, -loan.getRepayment());
                cardController.updateData(userID, loan.getCardUsed(), loan.getRepayment());
            }
            else {
                newLoans.add(loan);
            }
        }
        controller.saveData(user.getFileDirectory() + "\\LoansHistory.json", newLoans, LoansObject.class);
        return newLoans;
    }

    private void updateBalance(UserObject user, double amount) {
        user.setBalance(user.getBalance() + amount);
        usersController.changeUser(user.getUserID(), user);
    }
}
