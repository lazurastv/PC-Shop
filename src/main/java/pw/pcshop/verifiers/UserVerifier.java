package pw.pcshop.verifiers;

public class UserVerifier {
    private static VerificationResult verifyName(String value) {
        return VerifierUtils.verifyIsWord("Name", value);
    }

    private static VerificationResult verifyLastName(String value) {
        return VerifierUtils.verifyIsWord("Last name", value);
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

    private static VerificationResult verifyPhoneNumber(String value) {
        return VerifierUtils.verifyIsNumber("Phone number", value);
    }

    private static VerificationResult verifyCreditCardNumber(String value) {
        return VerifierUtils.verifyIsNumber("Credit card number", value);
    }
}
