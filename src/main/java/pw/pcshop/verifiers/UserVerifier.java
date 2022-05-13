package pw.pcshop.verifiers;

import java.util.Calendar;
import java.util.Date;

import pw.pcshop.verifiers.verificationResults.NonAlphabetic;
import pw.pcshop.verifiers.verificationResults.NonNumericResult;
import pw.pcshop.verifiers.verificationResults.VerificationResult;

public class UserVerifier {
    private static VerificationResult verifyAnyName(String item, String value) {
        if (!VerifierUtils.isAlphabetic(value)) {
            return new NonAlphabetic(item);
        }
        return new VerificationResult();
    }

    private static VerificationResult verifyName(String value) {
        return verifyAnyName("Name", value);
    }

    private static VerificationResult verifyLastName(String value) {
        return verifyAnyName("Last name", value);
    }

    private static VerificationResult verifyBirthDate(Date value) {
        if (!VerifierUtils.isLivingBirthDate(value)) {
            return new VerificationResult("Impossible birth date.");
        }
        return new VerificationResult();
    }

    private static VerificationResult verifyEmail(String value) {
        String[] values = value.split("@");
        if (values.length != 2) {
            return new VerificationResult("Wrong number of @ symbols.");
        }
        if (!VerifierUtils.isAlphaNumeric(values[0])) {
            return new VerificationResult("Impossible email address.");
        }
        values = values[1].split(".");
        if (!VerifierUtils.isAlphaNumeric(values[0])) {
            return new VerificationResult("Unlikely email provider.");
        }
        if (!VerifierUtils.isAlphabetic(values[1])) {
            return new VerificationResult("Impossible localization.");
        }
        return new VerificationResult();
    }

    private static VerificationResult verifyAnyNumber(String item, String value) {
        if (!VerifierUtils.isNumeric(value)) {
            return new NonNumericResult(item);
        }
        return new VerificationResult();
    }

    private static VerificationResult verifyPhoneNumber(String value) {
        return verifyAnyNumber("Phone number", value);
    }

    private static VerificationResult verifyCreditCardNumber(String value) {
        return verifyAnyNumber("Credit card number", value);
    }
}
