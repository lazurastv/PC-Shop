package pw.pcshop.verifiers.verificationResults;

public class NonAlphabetic extends VerificationResult {
    public NonAlphabetic(String item) {
        super(item + " must contain only letters.");
    }
}
