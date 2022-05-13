package pw.pcshop.verifiers;

import pw.pcshop.verifiers.verificationResults.VerificationResult;

public class ProcessorVerifier {
    private static VerificationResult verifyManufacter(String value) {
        if (value != "Intel" && value != "AMD") {
            return new VerificationResult("Unknown manufacturer.");
        }
        return new VerificationResult();
    }

    private static VerificationResult verifyThreadCount(int value) {
        if (value <= 0) {
            return new VerificationResult("Thread count must be positive.");
        }
        return new VerificationResult();
    }

    private static VerificationResult verifyFrequency(double value) {
        if (value <= 0) {
            return new VerificationResult("Thread count must be positive.");
        }
        return new VerificationResult();
    }
}
