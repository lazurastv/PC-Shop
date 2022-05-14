package pw.pcshop.verifiers;

import java.util.Calendar;
import java.util.Date;

public class PeselVerifier {
    private static int getYear(String PESEL) {
        String year = PESEL.substring(0, 2);
        int month = getMonth(PESEL);
        if (month <= 12) {
            year = "19" + year;
        } else {
            year = "20" + year;
        }
        return Integer.parseInt(year);
    }

    private static int getMonth(String PESEL) {
        return Integer.parseInt(PESEL.substring(2, 4));
    }

    private static int getTrueMonth(String PESEL) {
        return getMonth(PESEL) % 20;
    }

    private static int getDay(String PESEL) {
        return Integer.parseInt(PESEL.substring(4, 6));
    }

    private static Date getDate(String PESEL) {
        Calendar date = Calendar.getInstance();
        date.set(getYear(PESEL), getTrueMonth(PESEL), getDay(PESEL));
        return date.getTime();
    }

    private static int getControlNumber(String PESEL) {
        return PESEL.charAt(10) - '0';
    }

    private static int calculateControlNumber(String PESEL) {
        int[] coeffs = new int[] { 1, 3, 7, 9 };
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            sum += (PESEL.charAt(i) - '0') * coeffs[i % 4];
        }
        return 10 - sum % 10;
    }

    public static VerificationResult verifyPESEL(String value, Date birthDate) {
        if (value.length() != 11) {
            return new VerificationResult("Wrong PESEL length.");
        }
        VerificationResult isNumeric = VerifierUtils.verifyIsNumber("PESEL", value);
        if (!isNumeric.correct) {
            return isNumeric;
        }
        try {
            Calendar date = Calendar.getInstance();
            date.set(getYear(value), getTrueMonth(value), getDay(value));
            if (!date.getTime().equals(birthDate)) {
                return new VerificationResult("PESEL and birth date do not match.");
            }
            if (!VerifierUtils.isLivingBirthDate(date.getTime())) {
                return new VerificationResult("Impossible year.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return new VerificationResult("Invalid PESEL date.");
        }
        if (getControlNumber(value) != calculateControlNumber(value)) {
            return new VerificationResult("Wrong control number.");
        }
        return new VerificationResult();
    }
}
