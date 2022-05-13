package pw.pcshop.verifiers;

import java.util.Calendar;
import java.util.Date;

import pw.pcshop.verifiers.verificationResults.NonNumericResult;
import pw.pcshop.verifiers.verificationResults.VerificationResult;

public abstract class PeselVerifier {
    public static int getYear(String PESEL) {
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

    public static int getTrueMonth(String PESEL) {
        return getMonth(PESEL) % 20;
    }

    public static int getDay(String PESEL) {
        return Integer.parseInt(PESEL.substring(4, 6));
    }

    public static Date getDate(String PESEL) {
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

    public static VerificationResult verifyPESEL(String value) {
        if (value.length() != 11) {
            return new VerificationResult("Wrong PESEL length.");
        }
        if (!VerifierUtils.isNumeric(value)) {
            return new NonNumericResult("PESEL");
        }
        try {
            Calendar date = Calendar.getInstance();
            date.set(getYear(value), getTrueMonth(value), getDay(value));
            if (!VerifierUtils.isLivingBirthDate(date.getTime())) {
                return new VerificationResult("Impossible year.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return new VerificationResult("Invalid PESEL prefix.");
        }
        if (getControlNumber(value) != calculateControlNumber(value)) {
            return new VerificationResult("Wrong control number.");
        }
        return new VerificationResult();
    }
}
