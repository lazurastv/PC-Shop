package pw.pcshop.verifiers;

import java.util.Arrays;
import java.util.HashSet;

import pw.pcshop.dataModels.Motherboard;

public class MotherboardVerifier {
    private Motherboard motherboard;

    public MotherboardVerifier(Motherboard motherboard) {
        this.motherboard = motherboard;
    }

    private VerificationResult verifyManufacturer() {
        HashSet<String> validManufacturers = new HashSet<>(Arrays.asList("Asus", "MSI", "Gigabyte", "ASRock", "Acer"));
        if (!validManufacturers.contains(motherboard.getManufacturer())) {
            return new VerificationResult("Invalid manufacturer.");
        }
        return new VerificationResult();
    }

    private VerificationResult verifySize() {
        HashSet<String> validSizes = new HashSet<>(Arrays.asList("Mini-ITX", "microATX", "ATX", "EATX"));
        if (!validSizes.contains(motherboard.getSize())) {
            return new VerificationResult("Invalid size.");
        }
        return new VerificationResult();
    }

    private VerificationResult verifyMaxRAM() {
        if (motherboard.getMaxRAM() < 1) {
            return new VerificationResult("RAM must be positive.");
        }
        return new VerificationResult();
    }
}
