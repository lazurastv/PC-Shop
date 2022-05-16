package pw.pcshop.verifiers;

import pw.pcshop.viewModels.MotherboardVM;

public class MotherboardVerifier {
    public static VerificationResult verifyMotherboard(MotherboardVM motherboard) {
        try {
            VerifierUtils.throwIfNotCorrect(verifyManufacturer(motherboard.manufacturer));
            VerifierUtils.throwIfNotCorrect(verifySize(motherboard.size));
            VerifierUtils.throwIfNotCorrect(verifyMaxRAM(motherboard.maxRAM));
        } catch (VerificationException v) {
            return new VerificationResult(v.getMessage());
        }
        return new VerificationResult();
    }

    private static VerificationResult verifyManufacturer(String value) {
        return VerifierUtils.verifyInList("Manufacturer", new String[] { "Asus", "MSI", "Gigabyte", "ASRock", "Acer" },
                value);
    }

    private static VerificationResult verifySize(String value) {
        return VerifierUtils.verifyInList("Size", new String[] { "Mini-ITX", "microATX", "ATX", "EATX" }, value);
    }

    private static VerificationResult verifyMaxRAM(int value) {
        return VerifierUtils.verifyIsPositive("RAM", value);
    }
}
