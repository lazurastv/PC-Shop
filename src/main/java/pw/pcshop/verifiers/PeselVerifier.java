package pw.pcshop.verifiers;

import java.util.Calendar;

import pw.pcshop.verifiers.verificationResults.NonNumericResult;
import pw.pcshop.verifiers.verificationResults.VerificationResult;

public abstract class PeselVerifier {
    public static int getYear(String PESEL) {
        return Integer.parseInt(PESEL.substring(0, 2));
    }

    private static int getMonth(String PESEL) {
        return Integer.parseInt(PESEL.substring(2, 4));
    }

    public static int getTrueMonth(String PESEL) {
        int month = getMonth(PESEL);
        if (month > 12) {
            return month - 20;
        }
        return month;
    }

    public static int getDay(String PESEL) {
        return Integer.parseInt(PESEL.substring(4, 6));
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
