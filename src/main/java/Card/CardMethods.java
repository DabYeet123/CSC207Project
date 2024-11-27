package Card;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.Objects;

public class CardMethods {
    /**
     * used to get the new if with no same id
     */
    @NotNull
    public static String newId(String name) {
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < Math.min(name.length(), 3); i++) {
            int k = name.replaceAll("\\s", "").charAt(i);
            id.append(k);
        }
        String insideId = id + getDifferentnumber(id.toString());
        return getIdForTest(insideId, id.toString());
    }

    @NotNull
    public static String getIdForTest(String insideId, String id) {
        while (!checkId(insideId) | insideId.length() < 10) {
            insideId = id + getDifferentnumber(id);
        }
        id = insideId;
        return id;
    }

    /**
     * used to check the repeating of id
     * @param id random id that need to check if there is same id in file
     */
    public static boolean checkId(String id) {
        CardController.loadFromFile();
        for (CardObject card : CardController.cardList) {
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
    public static String getDifferentnumber(String id) {
        long num = Math.round((Math.pow(10, (10 - id.length()))) * Math.random());
        return threeCaseFor0(id, num);
    }

    @NotNull
    public static String threeCaseFor0(String id, long num) {
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
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        int currentMonth = today.getMonthValue();
        return getDateForTest(currentMonth, currentYear);
    }

    @NotNull
    public static String getDateForTest(int currentMonth, int currentYear) {
        String month;
        String expiryDate;
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
        long num = Math.round(1000 * Math.random());
        return getNewCodeForTest(num);
    }

    @NotNull
    public static String getNewCodeForTest(long num) {
        String securityCode;
        if (num >= 100) {
            securityCode = String.valueOf(num);
        } else if (num >= 10) {
            securityCode = "0" + num;
        } else {
            securityCode = "00" + num;
        }
        return securityCode;
    }
}
