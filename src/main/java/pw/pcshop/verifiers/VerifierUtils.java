package pw.pcshop.verifiers;

import java.util.Calendar;
import java.util.Date;

public abstract class VerifierUtils {
    public static boolean isLetter(char character) {
        character = Character.toLowerCase(character);
        return character >= 'a' && character <= 'z' || character > (char) 127;
    }

    public static boolean isNumber(char character) {
        return character >= '0' && character <= '9';
    }

    public static boolean isAlphabetic(String value) {
        for (int i = 0; i < value.length(); i++) {
            if (!isLetter(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNumeric(String value) {
        for (int i = 0; i < value.length(); i++) {
            if (!isNumber(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAlphaNumeric(String value) {
        for (int i = 0; i < value.length(); i++) {
            if (!isLetter(value.charAt(i)) && !isNumber(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isLivingBirthDate(Date date) {
        Calendar earliestDate = Calendar.getInstance();
        Calendar latestDate = Calendar.getInstance();
        Calendar givenDate = Calendar.getInstance();
        earliestDate.set(1900, 1, 1);
        givenDate.setTime(date);

        return givenDate.after(earliestDate) && givenDate.before(latestDate);
    }
}
