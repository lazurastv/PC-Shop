package pw.pcshop.viewModels;

import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;
import pw.pcshop.dataModels.GraphicsCard;

@NoArgsConstructor
public class GraphicsCardVM {
    public String manufacturer;
    public String series;
    public int vRAM;
    public String memoryType;
    public int length;
    public int width;
    public int height;

    public GraphicsCardVM(GraphicsCard graphicsCard) {
        manufacturer = graphicsCard.getManufacturer();
        series = graphicsCard.getSeries();
        vRAM = graphicsCard.getVRAM();
        memoryType = graphicsCard.getMemoryType();
        length = graphicsCard.getLength();
        width = graphicsCard.getWidth();
        height = graphicsCard.getHeight();
    }

    public static List<GraphicsCardVM> toGraphicsCardVMs(List<GraphicsCard> graphicsCards) {
        List<GraphicsCardVM> graphicsCardVMs = new ArrayList<>();
        graphicsCards.forEach(graphicsCard -> graphicsCardVMs.add(new GraphicsCardVM(graphicsCard)));
        return graphicsCardVMs;
    }
}
