package Loans.DataObject;

import cardandexchange.adapter.CardController;
import cardandexchange.dataObject.Card;
import dataaccess.DataAccessController;
import dataaccess.DataAccessInterface;
import userdataobject.UserObject;
import userdataobject.UsersController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoansDBAccess implements DataAccessInterface<LoansObject> {
    DataAccessController controller = new DataAccessController();
    UsersController usersController = new UsersController();

    @Override
    public UserObject saveData(int userID, LoansObject loan) {
        UserObject user = usersController.getUser(userID);
        double amount = loan.amount;

        List<LoansObject> loans = controller.readData(user.getFileDirectory() + "\\LoansHistory.json", LoansObject.class);
        loans.add(loan);
        Collections.sort(loans);
        controller.saveData(user.getFileDirectory() + "\\LoansHistory.json", loans, LoansObject.class);
        return updateBalance(user, amount);
    }

    @Override
    public List<LoansObject> readData(int userID) {
        UserObject user = usersController.getUser(userID);
        CardController cardController = new CardController(user);
        List<LoansObject> loans = controller.readData(user.getFileDirectory() + "\\LoansHistory.json", LoansObject.class);
        List<LoansObject> newLoans = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (LoansObject loan : loans) {
            if (loan.endDate.isBefore(today)) {
                updateBalance(user, -loan.repayment);
                cardController.updateData(userID, loan.cardUsed, loan.repayment);
            } else {
                newLoans.add(loan);
            }
        }
        controller.saveData(user.getFileDirectory() + "\\LoansHistory.json", newLoans, LoansObject.class);
        return newLoans;
    }

    private UserObject updateBalance(UserObject user, double amount){
        user.setBalance(user.getBalance() + amount);
        usersController.changeUser(user.getUserID(), user);
        return user;
    }
}
