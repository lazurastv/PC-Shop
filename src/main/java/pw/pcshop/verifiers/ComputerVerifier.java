package pw.pcshop.verifiers;

import pw.pcshop.viewModels.ComputerVM;

public class ComputerVerifier {
    public static VerificationResult verifyComputer(ComputerVM computer) {
        try {
            VerifierUtils.throwIfNotCorrect(verifyOS(computer.operatingSystem));
            VerifierUtils.throwIfNotCorrect(verifyRAM(computer.RAM));
            VerifierUtils.throwIfNotCorrect(verifyLength(computer.length));
            VerifierUtils.throwIfNotCorrect(verifyWidth(computer.width));
            VerifierUtils.throwIfNotCorrect(verifyHeight(computer.height));
            VerifierUtils.throwIfNotCorrect(verifyMemory(computer.memory));
            VerifierUtils.throwIfNotCorrect(verifyWattage(computer.wattage));
            VerifierUtils.throwIfNotCorrect(verifyPrice(computer.price));
        } catch (VerificationException v) {
            return new VerificationResult(v.getMessage());
        }
        return new VerificationResult();
    }

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
