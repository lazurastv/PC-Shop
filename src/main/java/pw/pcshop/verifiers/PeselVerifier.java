package pw.pcshop.verifiers;

import java.util.Calendar;
import java.util.Date;

public class PeselVerifier {
    private static int getCentury(String PESEL) {
        int month = getMonth(PESEL);
        switch (month / 20) {
            case 0:
                return 19;
            case 1:
                return 20;
            case 2:
                return 21;
            case 3:
                return 22;
            case 4:
                return 18;
            default:
                throw new ArrayIndexOutOfBoundsException();
        }
    }

    private static int getYear(String PESEL) {
        String year = PESEL.substring(0, 2);
        return 100 * getCentury(PESEL) + Integer.parseInt(year);
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
        date.set(getYear(PESEL), getTrueMonth(PESEL) - 1, getDay(PESEL));
        return date.getTime();
    }

    private static int getControlNumber(String PESEL) {
        return PESEL.charAt(10) - '0';
    }

    private static int calculateControlNumber(String PESEL) {
        int[] coeffs = new int[] { 1, 3, 7, 9 };
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (PESEL.charAt(i) - '0') * coeffs[i % 4];
        }
        return 10 - sum % 10;
    }

    private static boolean datesMatch(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        for (int field : new int[] { Calendar.YEAR, Calendar.MONTH, Calendar.DATE }) {
            if (cal1.get(field) != cal2.get(field)) {
                return false;
            }
        }
        return true;
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
            Date PESELdate = getDate(value);
            if (!datesMatch(PESELdate, birthDate)) {
                return new VerificationResult("PESEL and birth date do not match.");
            }
            if (!VerifierUtils.isLivingBirthDate(PESELdate)) {
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
