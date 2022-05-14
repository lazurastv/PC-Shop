package pw.pcshop.viewModels;

import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;
import pw.pcshop.dataModels.Computer;

@NoArgsConstructor
public class ComputerVM {
    public Long id;
    public Long processorId;
    public Long graphicsCardId;
    public Long motherboardId;
    public String model;
    public String operatingSystem;
    public int RAM;
    public int length;
    public int width;
    public int height;
    public int memory;
    public double wattage;
    public double price;

    public ComputerVM(Computer computer) {
        id = computer.getId();
        processorId = computer.getProcessor().getId();
        graphicsCardId = computer.getGraphicsCard().getId();
        motherboardId = computer.getMotherboard().getId();
        model = computer.getModel();
        operatingSystem = computer.getOperatingSystem();
        RAM = computer.getRAM();
        length = computer.getLength();
        width = computer.getWidth();
        height = computer.getHeight();
        memory = computer.getMemory();
        wattage = computer.getWattage();
        price = computer.getPrice();
    }

    public static List<ComputerVM> toComputerVMs(List<Computer> computers) {
        List<ComputerVM> computerVMs = new ArrayList<>();
        computers.forEach(computer -> computerVMs.add(new ComputerVM(computer)));
        return computerVMs;
    }
}
