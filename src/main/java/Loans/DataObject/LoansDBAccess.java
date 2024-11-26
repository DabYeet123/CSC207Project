package Loans.DataObject;

import Card.Card;
import Card.CardController;
import DataAccess.DataAccessController;
import DataAccess.DataAccessInterface;
import UserDataObject.UserObject;
import UserDataObject.UsersController;

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
                user.setBalance(user.getBalance() - loan.repayment);
                usersController.changeUser(userID, user);
                Card card = cardController.getCard(loan.cardUsed);
                card.updateAmount(loan.repayment);
            } else {
                newLoans.add(loan);
            }
        }
        return newLoans;
    }

    private UserObject updateBalance(UserObject user, double amount){
        user.setBalance(user.getBalance() + amount);
        usersController.changeUser(user.getUserID(), user);
        return user;
    }
}
