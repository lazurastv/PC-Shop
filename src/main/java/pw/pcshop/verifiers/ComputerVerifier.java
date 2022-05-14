package pw.pcshop.verifiers;

public class ComputerVerifier {
    private static VerificationResult verifyOS(String value) {
        return VerifierUtils.verifyInList("Operating system",
                new String[] { "Windows 7", "Windows 8.1", "Windows 10", "Ubuntu" }, value);
    }

    private static VerificationResult verifyRAM(int value) {
        return VerifierUtils.verifyIsPositive("RAM", value);
    }

    private static VerificationResult verifyLength(int value) {
        return VerifierUtils.verifyIsPositive("Length", value);
    }

    private static VerificationResult verifyWidth(int value) {
        return VerifierUtils.verifyIsPositive("Width", value);
    }

    private static VerificationResult verifyHeight(int value) {
        return VerifierUtils.verifyIsPositive("Height", value);
    }

    private static VerificationResult verifyMemory(int value) {
        return VerifierUtils.verifyIsPositive("Memory", value);
    }

    private static VerificationResult verifyWattage(double value) {
        return VerifierUtils.verifyIsPositive("Memory", value);
    }

    private static VerificationResult verifyPrice(double value) {
        return VerifierUtils.verifyIsPositive("Price", value);
    }
}
