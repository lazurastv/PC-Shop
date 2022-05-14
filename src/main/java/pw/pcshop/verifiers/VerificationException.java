package pw.pcshop.verifiers;

public class VerificationException extends RuntimeException {
    public VerificationException(VerificationResult result) {
        super(result.message);
    }
}
