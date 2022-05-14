package pw.pcshop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pw.pcshop.addModels.MotherboardVM;
import pw.pcshop.dataModels.Motherboard;
import pw.pcshop.repositories.MotherboardRepository;
import pw.pcshop.verifiers.MotherboardVerifier;
import pw.pcshop.verifiers.VerifierUtils;

@Service
@RequiredArgsConstructor
public class MotherboardService {
    private final MotherboardRepository motherboardRepository;

    public List<MotherboardVM> getAll() {
        return MotherboardVM.toMotherboardVMs(motherboardRepository.findAll());
    }

    public void add(MotherboardVM motherboardVM) {
        VerifierUtils.throwIfNotCorrect(MotherboardVerifier.verifyMotherboard(motherboardVM));
        Motherboard motherboard = new Motherboard();
        motherboard.setManufacturer(motherboardVM.manufacturer);
        motherboard.setMaxRAM(motherboardVM.maxRAM);
        motherboard.setSeries(motherboardVM.series);
        motherboard.setSize(motherboardVM.size);
        motherboard.setSocket(motherboardVM.socket);
        motherboardRepository.save(motherboard);
    }
}
