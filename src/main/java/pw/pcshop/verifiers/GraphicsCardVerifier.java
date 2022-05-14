package pw.pcshop.verifiers;

import pw.pcshop.addModels.AddGraphicsCard;

public class GraphicsCardVerifier {
    public VerificationResult verifyGraphicsCard(AddGraphicsCard graphicsCard) {
        try {
            VerifierUtils.throwIfNotCorrect(verifyManufacturer(graphicsCard.manufacturer));
            VerifierUtils.throwIfNotCorrect(verifyVRAM(graphicsCard.vRAM));
            VerifierUtils.throwIfNotCorrect(verifyMemoryType(graphicsCard.memoryType));
            VerifierUtils.throwIfNotCorrect(verifyLength(graphicsCard.length));
            VerifierUtils.throwIfNotCorrect(verifyWidth(graphicsCard.width));
            VerifierUtils.throwIfNotCorrect(verifyHeight(graphicsCard.height));
        } catch (VerificationException v) {
            return new VerificationResult(v.getMessage());
        }
        return new VerificationResult();
    }

    private VerificationResult verifyManufacturer(String value) {
        return VerifierUtils.verifyInList("Manufacturer",
                new String[] { "AMD", "NVIDIA", "Zotac", "ASUS", "EVGA", "ASRock" }, value);
    }

    private VerificationResult verifyVRAM(int value) {
        return VerifierUtils.verifyIsPositive("VRAM", value);
    }

    private VerificationResult verifyMemoryType(String value) {
        return VerifierUtils.verifyInList("Memory type", new String[] { "GDDR", "GDDR2", "GDDR3", "GDDR4", "GDDR5" },
                value);
    }

    private VerificationResult verifyLength(int value) {
        return VerifierUtils.verifyIsPositive("Length", value);
    }

    private VerificationResult verifyWidth(int value) {
        return VerifierUtils.verifyIsPositive("Width", value);
    }

    private VerificationResult verifyHeight(int value) {
        return VerifierUtils.verifyIsPositive("Height", value);
    }
}
