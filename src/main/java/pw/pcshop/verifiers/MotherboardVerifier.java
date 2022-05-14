package pw.pcshop.verifiers;

public class MotherboardVerifier {
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
