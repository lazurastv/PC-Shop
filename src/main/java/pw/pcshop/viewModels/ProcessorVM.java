package pw.pcshop.viewModels;

import java.util.ArrayList;
import java.util.List;

import pw.pcshop.dataModels.Processor;

public class ProcessorVM {
    public String manufacturer;
    public String series;
    public int threadCount;
    public double frequency;

    public ProcessorVM(Processor processor) {
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
