package pw.pcshop.verifiers;

import java.util.Calendar;
import java.util.Date;

public class UserVerifier {
    private static boolean isLetter(char character) {
        return (character >= 'A' && character <= 'Z') ||
                (character >= 'a' && character <= 'z') ||
                character > (char) 127;
    }

    private static boolean isNumber(char character) {
        return character >= '0' && character <= '9';
    }

    private static VerificationResult verifyName(String value) {
        for (int i = 0; i < value.length(); i++) {
            if (!isLetter(value.charAt(i))) {
                return new VerificationResult("Symbol '" + value.charAt(i) + "' not allowed in name.");
            }
        }
        return new VerificationResult();
    }

    private static VerificationResult verifyBirthDate(Date value) {
        Calendar earliestDate = Calendar.getInstance();
        Calendar latestDate = Calendar.getInstance();
        earliestDate.set(1900, 1, 1);

        if (value.before(earliestDate.getTime()) || value.after(latestDate.getTime())) {
            return new VerificationResult("Impossible birth date.");
        }
        return new VerificationResult();
    }

    private static VerificationResult verifyPESEL(String value) {
        if (value.length() < 11) {
            return new VerificationResult("PESEL is too short.");
        }
        for (int i = 0; i < 11; i++) {
            if (!isNumber(value.charAt(i))) {
                return new VerificationResult("PESEL can contain only numbers.");
            }
        }
        /*
         * Check for:
         * First two numbers must be < 22 if third and fourth are > 20 and < 40
         */
        return new VerificationResult();
    }
}
