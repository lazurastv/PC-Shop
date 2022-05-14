package pw.pcshop.verifiers;

import pw.pcshop.addModels.AddMotherboard;

public class MotherboardVerifier {
    public VerificationResult verifyMotherboard(AddMotherboard motherboard) {
        try {
            VerifierUtils.throwIfNotCorrect(verifyManufacturer(motherboard.manufacturer));
            VerifierUtils.throwIfNotCorrect(verifySize(motherboard.size));
            VerifierUtils.throwIfNotCorrect(verifyMaxRAM(motherboard.maxRAM));
        } catch (VerificationException v) {
            return new VerificationResult(v.getMessage());
        }
        return new VerificationResult();
    }

    private VerificationResult verifyManufacturer(String value) {
        return VerifierUtils.verifyInList("Manufacturer", new String[] { "Asus", "MSI", "Gigabyte", "ASRock", "Acer" },
                value);
    }

    private VerificationResult verifySize(String value) {
        return VerifierUtils.verifyInList("Size", new String[] { "Mini-ITX", "microATX", "ATX", "EATX" }, value);
    }

    private VerificationResult verifyMaxRAM(int value) {
        return VerifierUtils.verifyIsPositive("RAM", value);
    }
}
