package pw.pcshop.verifiers;

public class GraphicsCardVerifier {
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
