package pw.pcshop.verifiers;

import pw.pcshop.viewModels.UserVM;

public class UserVerifier {
    public static VerificationResult verifyUser(UserVM user) {
        try {
            VerifierUtils.throwIfNotCorrect(verifyName(user.name));
            VerifierUtils.throwIfNotCorrect(verifyLastName(user.lastName));
            VerifierUtils.throwIfNotCorrect(verifyEmail(user.email));
            VerifierUtils.throwIfNotCorrect(PeselVerifier.verifyPESEL(user.PESEL, user.birthDate));
            VerifierUtils.throwIfNotCorrect(verifyPhoneNumber(user.phoneNumber));
            VerifierUtils.throwIfNotCorrect(verifyCreditCardNumber(user.creditCardNumber));
        } catch (VerificationException v) {
            return new VerificationResult(v.getMessage());
        }
        return new VerificationResult();
    }

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
