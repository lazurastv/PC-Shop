package pw.pcshop.viewModels;

import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;
import pw.pcshop.dataModels.Motherboard;

@NoArgsConstructor
public class MotherboardVM {
    public String manufacturer;
    public String series;
    public String size;
    public String socket;
    public int maxRAM;

    public MotherboardVM(Motherboard motherboard) {
        manufacturer = motherboard.getManufacturer();
        series = motherboard.getSeries();
        size = motherboard.getSize();
        socket = motherboard.getSocket();
        maxRAM = motherboard.getMaxRAM();
    }

    public static List<MotherboardVM> toMotherboardVMs(List<Motherboard> motherboards) {
        List<MotherboardVM> motherboardVMs = new ArrayList<>();
        motherboards.forEach(motherboard -> motherboardVMs.add(new MotherboardVM(motherboard)));
        return motherboardVMs;
    }
}
