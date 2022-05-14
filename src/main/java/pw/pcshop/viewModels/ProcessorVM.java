package pw.pcshop.viewModels;

import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;
import pw.pcshop.dataModels.Processor;

@NoArgsConstructor
public class ProcessorVM {
    public Long id;
    public String manufacturer;
    public String series;
    public int threadCount;
    public double frequency;

    public ProcessorVM(Processor processor) {
        id = processor.getId();
        manufacturer = processor.getManufacturer();
        series = processor.getSeries();
        threadCount = processor.getThreadCount();
        frequency = processor.getFrequency();
    }

    public static List<ProcessorVM> toProcessorVMs(List<Processor> processors) {
        List<ProcessorVM> processorVMs = new ArrayList<>();
        processors.forEach(processor -> processorVMs.add(new ProcessorVM(processor)));
        return processorVMs;
    }
}
