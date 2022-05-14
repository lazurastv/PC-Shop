package pw.pcshop.verifiers;

import pw.pcshop.addModels.GraphicsCardVM;

public class GraphicsCardVerifier {
    public static VerificationResult verifyGraphicsCard(GraphicsCardVM graphicsCard) {
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

    private static VerificationResult verifyManufacturer(String value) {
        return VerifierUtils.verifyInList("Manufacturer",
                new String[] { "AMD", "NVIDIA", "Zotac", "ASUS", "EVGA", "ASRock" }, value);
    }

    private static VerificationResult verifyVRAM(int value) {
        return VerifierUtils.verifyIsPositive("VRAM", value);
    }

    private static VerificationResult verifyMemoryType(String value) {
        return VerifierUtils.verifyInList("Memory type", new String[] { "GDDR", "GDDR2", "GDDR3", "GDDR4", "GDDR5" },
                value);
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
}
