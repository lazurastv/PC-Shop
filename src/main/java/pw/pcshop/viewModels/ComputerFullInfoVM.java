package pw.pcshop.viewModels;

import java.util.ArrayList;
import java.util.List;

import pw.pcshop.dataModels.Computer;

public class ComputerFullInfoVM {
    public Long id;
    public ProcessorVM processor;
    public GraphicsCardVM graphicsCard;
    public MotherboardVM motherboard;
    public List<UserVM> users;
    public String model;
    public String operatingSystem;
    public int RAM;
    public int length;
    public int width;
    public int height;
    public int memory;
    public double wattage;
    public double price;

    public ComputerFullInfoVM(Computer computer) {
        id = computer.getId();
        processor = new ProcessorVM(computer.getProcessor());
        graphicsCard = new GraphicsCardVM(computer.getGraphicsCard());
        motherboard = new MotherboardVM(computer.getMotherboard());
        users = UserVM.toUserVMs(computer.getUsers());
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

    public static List<ComputerFullInfoVM> toComputerVMs(List<Computer> computers) {
        List<ComputerFullInfoVM> computerFullInfoVMs = new ArrayList<>();
        computers.forEach(computer -> computerFullInfoVMs.add(new ComputerFullInfoVM(computer)));
        return computerFullInfoVMs;
    }
}
