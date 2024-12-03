package cardUML;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.Objects;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class CardMethods {
    private static final int CARD_10 = 10;
    private static final int DATE_10 = 10;
    private static final int YEAR_ADD_5 = 5;
    private static final int CARD_3 = 3;
    private static final int INDEX_10 = 10;
    private static final int INDEX_100 = 100;
    private static final String ADD_0 = "0";

    /**
     * Used to get the new if with no same id.
     * @param name given name
     * @return new ID
     */
    @NotNull
    public static String newId(String name) {
        final StringBuilder id = new StringBuilder();
        for (int i = 0; i < Math.min(name.length(), CARD_3); i++) {
            final int k = name.replaceAll("\\s", "").charAt(i);
            id.append(k);
        }
        final String insideId = id + getDifferentnumber(id.toString());
        return getIdForTest(insideId, id.toString());
    }

    /**
     * Used to get the new if with no same id.
     * @param insideId given id front
     * @param id random id
     * @return a string is individual
     */
    @NotNull
    public static String getIdForTest(String insideId, String id) {
        String inside = insideId;
        String idIn = id;
        while (!checkId(inside) | inside.length() < CARD_10) {
            inside = idIn + getDifferentnumber(idIn);
        }
        idIn = inside;
        return idIn;
    }

    /**
     * Used to check the repeating of id.
     * @param id random id that need to check if there is same id in file
     * @return boolean to check whether used or not
     */
    public static boolean checkId(String id) {
        boolean result = true;
        CardController.loadFromFile();
        for (Card card : CardController.getCardList()) {
            if (Objects.equals(card.getId(), id)) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Used to check the random part of id is with same index.
     * @param id random id given by the name
     * @return String of random id
     */
    public static String getDifferentnumber(String id) {
        final long num = Math.round((Math.pow(10, CARD_10 - id.length())) * Math.random());
        return threeCaseFor0(id, num);
    }

    /**
     * Used to check the random part of id is with same index.
     * @param id random id given by the name
     * @param num random num given
     * @return String of random id
     */
    @NotNull
    public static String threeCaseFor0(String id, long num) {
        String result = String.valueOf(num);
        switch (CARD_10 - id.length()) {
            case 1:
                result = String.valueOf(num);
                break;
            case 2:
                if (num < INDEX_10) {
                    result = ADD_0 + num;
                }
                else {
                    result = String.valueOf(num);
                }
                break;
            case CARD_3:
                if (num < INDEX_10) {
                    result = "00" + num;
                }
                else if (num < INDEX_100) {
                    result = ADD_0 + num;
                }
                break;
            default: result = String.valueOf(num);
        }
        return result;
    }

    /**
     * Used to get the new update date for month and year.
     * @return give new data
     */
    @NotNull
    public static String newDate() {
        final LocalDate today = LocalDate.now();
        final int currentYear = today.getYear();
        final int currentMonth = today.getMonthValue();
        return getDateForTest(currentMonth, currentYear);
    }

    /**
     * Used to get the new code which is random with same index.
     * @param currentYear given month
     * @param currentMonth given year
     * @return new code
     */
    @NotNull
    public static String getDateForTest(int currentMonth, int currentYear) {
        final String month;
        final String expiryDate;
        if (currentMonth < DATE_10) {
            month = ADD_0 + currentMonth;
        }
        else {
            month = String.valueOf(currentMonth);
        }
        expiryDate = month + "/" + (currentYear + YEAR_ADD_5);
        return expiryDate;
    }

    /**
     * Used to get the new code which is random with same index.
     * @return new code
     */
    @NotNull
    public static String newCode() {
        final long num = Math.round(1000 * Math.random());
        return getNewCodeForTest(num);
    }

    /**
     * Used to get the new code which is random with same index.
     * @param num given num to index 3
     * @return new code
     */
    @NotNull
    public static String getNewCodeForTest(long num) {
        final String securityCode;
        if (num >= INDEX_100) {
            securityCode = String.valueOf(num);
        }
        else if (num >= INDEX_10) {
            securityCode = ADD_0 + num;
        }
        else {
            securityCode = "00" + num;
        }
        return securityCode;
    }
}
