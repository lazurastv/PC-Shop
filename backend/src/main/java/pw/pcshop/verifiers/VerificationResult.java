package pw.pcshop.verifiers;

public class VerificationResult {
    public boolean correct;
    public String message;

    public VerificationResult() {
        correct = true;
    }

    public VerificationResult(String message) {
        correct = false;
        this.message = message;
    }
}
