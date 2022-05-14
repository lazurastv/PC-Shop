package pw.pcshop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pw.pcshop.addModels.ProcessorVM;
import pw.pcshop.dataModels.Processor;
import pw.pcshop.repositories.ProcessorRepository;
import pw.pcshop.verifiers.ProcessorVerifier;
import pw.pcshop.verifiers.VerifierUtils;

@Service
@RequiredArgsConstructor
public class ProcessorService {
    private final ProcessorRepository processorRepository;

    public List<ProcessorVM> getAll() {
        return ProcessorVM.toProcessorVMs(processorRepository.findAll());
    }

    public void add(ProcessorVM processorVM) {
        VerifierUtils.throwIfNotCorrect(ProcessorVerifier.verifyProcessor(processorVM));
        Processor processor = new Processor();
        processor.setFrequency(processorVM.frequency);
        processor.setManufacturer(processorVM.manufacturer);
        processor.setSeries(processorVM.series);
        processor.setThreadCount(processorVM.threadCount);
        processorRepository.save(processor);
    }
}
