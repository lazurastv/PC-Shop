package pw.pcshop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pw.pcshop.dataModels.Computer;
import pw.pcshop.dataModels.GraphicsCard;
import pw.pcshop.dataModels.User;
import pw.pcshop.repositories.ComputerRepository;
import pw.pcshop.repositories.GraphicsCardRepository;
import pw.pcshop.repositories.MotherboardRepository;
import pw.pcshop.repositories.ProcessorRepository;
import pw.pcshop.repositories.UserRepository;
import pw.pcshop.verifiers.ComputerVerifier;
import pw.pcshop.verifiers.VerifierUtils;
import pw.pcshop.viewModels.ComputerAddUser;
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
        GraphicsCard graphicsCard = graphicsCardRepository.getById(computerVM.graphicsCardId);
        VerifierUtils.throwIfNotCorrect(ComputerVerifier.verifyComputer(computerVM, graphicsCard));
        Computer computer = new Computer();
        computer.setGraphicsCard(graphicsCard);
        computer.setHeight(computerVM.height);
        computer.setLength(computerVM.length);
        computer.setMemory(computerVM.memory);
        computer.setModel(computerVM.model);
        computer.setMotherboard(motherboardRepository.getById(computerVM.motherboardId));
        computer.setOperatingSystem(computerVM.operatingSystem);
        computer.setPrice(computerVM.price);
        computer.setProcessor(processorRepository.getById(computerVM.processorId));
        computer.setRAM(computerVM.RAM);
        computer.setWattage(computerVM.wattage);
        computer.setWidth(computerVM.width);
        computerRepository.save(computer);
    }

    public void addUser(ComputerAddUser model) {
        Computer computer = computerRepository.getById(model.computerId);
        User user = userRepository.getById(model.userId);
        computer.addUser(user);
        computerRepository.save(computer);
    }

    public double getIncome(Long id) {
        return computerRepository.getById(id).getIncome();
    }
}
