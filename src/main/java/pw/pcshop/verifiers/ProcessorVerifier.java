package pw.pcshop.verifiers;

public class ProcessorVerifier {
    private static VerificationResult verifyManufacter(String value) {
        return VerifierUtils.verifyInList("Manufacturer", new String[] { "Intel", "AMD" }, value);
    }

    private static VerificationResult verifyThreadCount(int value) {
        return VerifierUtils.verifyIsPositive("Thread count", value);
    }

    private static VerificationResult verifyFrequency(double value) {
        return VerifierUtils.verifyIsPositive("Frequency", value);
    }
}
