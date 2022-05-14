package pw.pcshop.verifiers;

public class ComputerVerifier {
    public static VerificationResult verifyOS(String value) {
        return VerifierUtils.verifyInList("Operating system",
                new String[] { "Windows 7", "Windows 8.1", "Windows 10", "Ubuntu" }, value);
    }

    public static VerificationResult verifyRAM(int value) {
        return VerifierUtils.verifyIsPositive("RAM", value);
    }

    public static VerificationResult verifyLength(int value) {
        return VerifierUtils.verifyIsPositive("Length", value);
    }

    public static VerificationResult verifyWidth(int value) {
        return VerifierUtils.verifyIsPositive("Width", value);
    }

    public static VerificationResult verifyHeight(int value) {
        return VerifierUtils.verifyIsPositive("Height", value);
    }

    public static VerificationResult verifyMemory(int value) {
        return VerifierUtils.verifyIsPositive("Memory", value);
    }

    public static VerificationResult verifyWattage(double value) {
        return VerifierUtils.verifyIsPositive("Memory", (int) (value + 1));
    }

    public static VerificationResult verifyPrice(double value) {
        return VerifierUtils.verifyIsPositive("Price", (int) (value + 1));
    }
}
