package Card;

import userdataobject.UserObject;
import login.loggedin.LoggedInController;
import login.welcome.WelcomeController;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CardController {
    public static List<Card> cardList = new ArrayList<>();
    static UserObject loggedInUser;
    private CardPresenter cardPresenter;
    private WelcomeController welcomeController;
    static CardDBAccess cardDBAccess = new CardDBAccess();

    public CardController(UserObject user) {
        this.loggedInUser = user;
        this.cardPresenter = new CardPresenter(this);
        this.welcomeController = new WelcomeController();
        loadFromFile();
    }

    public void launch() {
        cardPresenter.showView();
    }

    public void logOutTriggered(){
        cardPresenter.disposeView();
        welcomeController.launch();
    }

    public void goBackToBaseView() {
        cardPresenter.disposeView();
        LoggedInController controller = new LoggedInController(loggedInUser);
        controller.launch();
    }

    /**
     * used to load the file of json
     */
    public static void loadFromFile() {
        cardList = cardDBAccess.readData(loggedInUser.getUserID());
    }

    /**
     * used to get the new if with no same id
     */
    @NotNull
    public static String newId(String name) {
        String id = "";
        for (int i = 0; i < Math.min(name.length(), 3); i++) {
            char c = name.replaceAll("\\s", "").charAt(i);
            int k = c;
            id += String.valueOf(k);
        }
        String insideId = id + getDifferentnumber(id);
        while (!checkId(insideId)) {
            insideId = id + getDifferentnumber(id);
        }
        id = insideId;
        return id;
    }

    /**
     * used to check the repeating of id
     * @param id random id that need to check if there is same id in file
     */
    private static boolean checkId(String id) {
        loadFromFile();
        for (Card card : cardList) {
            if (Objects.equals(card.getId(), id)) {
                return false;
            }
        }
        return true;
    }

    /**
     * used to check the random part of id is with same index
     * @param id random id given by the name
     */
    private static String getDifferentnumber(String id) {
        long num = Math.round((Math.pow(10, (10 - id.length()))) * Math.random());
        switch (10 - id.length()) {
            case 1:
                return String.valueOf(num);
            case 2:
                if (num < 10) {
                    return "0" + num;
                }
                else {
                    return String.valueOf(num);
                }
            case 3:
                if (num < 10) {
                    return "00" + num;
                }
                else if (num < 100) {
                    return "0" + num;
                }
                else {
                    return String.valueOf(num);
                }
        }
        return String.valueOf(num);
    }

    /**
     * used to get the new update date for month and year
     */
    @NotNull
    public static String newDate() {
        String expiryDate;
        LocalDate today = LocalDate.now();
        String month;
        int currentYear = today.getYear();
        int currentMonth = today.getMonthValue();
        if (currentMonth < 10) {
            month = "0" + currentMonth;
        } else {
            month = String.valueOf(currentMonth);
        }
        expiryDate = month + "/" + (currentYear + 5);
        return expiryDate;
    }

    /**
     * used to get the new code which is random with same index
     */
    @NotNull
    public static String newCode() {
        String securityCode;
        long num = Math.round(1000 * Math.random());
        if (num >= 100) {
            securityCode = String.valueOf(num);
        } else if (num >= 10) {
            securityCode = "0" + num;
        } else {
            securityCode = "00" + num;
        }
        return securityCode;
    }

    /**
     * used to save the updated data in JSON file
     * @param card cards given in CardMethod CardList
     */
    public static void saveCards(Card card) {
        cardDBAccess.saveData(loggedInUser.getUserID(), card);
    }

    /**
     * used to save the updated data in JSON file
     * @param index the index card will be deleted in CardMethod CardList
     */
    public static void saveDeleteCard(int index) {
        cardDBAccess.saveDeleteData(loggedInUser.getUserID(), index);
    }

    public Card getCard(String cardID) {
        return cardDBAccess.readDataPoint(loggedInUser.getUserID(), cardID);
    }
}
