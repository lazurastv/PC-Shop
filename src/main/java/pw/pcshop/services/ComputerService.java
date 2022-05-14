package pw.pcshop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pw.pcshop.dataModels.Computer;
import pw.pcshop.repositories.ComputerRepository;
import pw.pcshop.repositories.GraphicsCardRepository;
import pw.pcshop.repositories.MotherboardRepository;
import pw.pcshop.repositories.ProcessorRepository;
import pw.pcshop.repositories.UserRepository;
import pw.pcshop.verifiers.ComputerVerifier;
import pw.pcshop.verifiers.VerifierUtils;
import pw.pcshop.viewModels.ComputerVM;

@Service
@RequiredArgsConstructor
public class ComputerService {
    private final GraphicsCardRepository graphicsCardRepository;
    private final ProcessorRepository processorRepository;
    private final MotherboardRepository motherboardRepository;
    private final UserRepository userRepository;
    private final ComputerRepository computerRepository;

    public List<ComputerVM> getAll() {
        return ComputerVM.toComputerVMs(computerRepository.findAll());
    }

    public void add(ComputerVM computerVM) {
        VerifierUtils.throwIfNotCorrect(ComputerVerifier.verifyComputer(computerVM));
        Computer computer = new Computer();
        computer.setGraphicsCard(graphicsCardRepository.getById(computerVM.graphicsCardId));
        computer.setHeight(computerVM.height);
        computer.setLength(computerVM.length);
        computer.setMemory(computerVM.memory);
        computer.setModel(computerVM.model);
        computer.setMotherboard(motherboardRepository.getById(computerVM.motherboardId));
        computer.setOperatingSystem(computerVM.operatingSystem);
        computer.setPrice(computerVM.price);
        computer.setProcessor(processorRepository.getById(computerVM.processorId));
        computer.setRAM(computerVM.RAM);
        computer.setUser(userRepository.getById(computerVM.userId));
        computer.setWattage(computerVM.wattage);
        computer.setWidth(computerVM.width);
        computerRepository.save(computer);
    }
}
