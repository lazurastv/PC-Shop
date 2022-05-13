package pw.pcshop.verifiers.verificationResults;

public class NonNumericResult extends VerificationResult {
    public NonNumericResult(String item) {
        super(item + " must contain only numbers.");
    }
}
