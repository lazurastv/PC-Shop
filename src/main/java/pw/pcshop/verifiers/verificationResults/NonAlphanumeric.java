package pw.pcshop.verifiers.verificationResults;

public class NonAlphanumeric extends VerificationResult {
    public NonAlphanumeric(String item) {
        super(item + " must contain only letters and numbers.");
    }
}
