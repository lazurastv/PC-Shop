package pw.pcshop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pw.pcshop.addModels.ComputerVM;
import pw.pcshop.dataModels.Computer;
import pw.pcshop.repositories.ComputerRepository;
import pw.pcshop.verifiers.ComputerVerifier;
import pw.pcshop.verifiers.VerifierUtils;

@Service
@RequiredArgsConstructor
public class UserService {
    private final ComputerRepository computerRepository;

    public List<ComputerVM> getAll() {
        return ComputerVM.toComputerVMs(computerRepository.findAll());
    }

    public void add(ComputerVM computerVM) {
        VerifierUtils.throwIfNotCorrect(ComputerVerifier.verifyComputer(computerVM));
        Computer computer = new Computer();
        computerRepository.save(computer);
    }
}
