package pw.pcshop.verifiers;

import pw.pcshop.addModels.ProcessorVM;

public class ProcessorVerifier {
    public static VerificationResult verifyProcessor(ProcessorVM processor) {
        try {
            VerifierUtils.throwIfNotCorrect(verifyManufacter(processor.manufacturer));
            VerifierUtils.throwIfNotCorrect(verifyThreadCount(processor.threadCount));
            VerifierUtils.throwIfNotCorrect(verifyFrequency(processor.frequency));
        } catch (VerificationException v) {
            return new VerificationResult(v.getMessage());
        }
        return new VerificationResult();
    }

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
