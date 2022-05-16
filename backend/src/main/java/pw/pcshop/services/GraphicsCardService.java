package pw.pcshop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pw.pcshop.dataModels.GraphicsCard;
import pw.pcshop.repositories.GraphicsCardRepository;
import pw.pcshop.verifiers.GraphicsCardVerifier;
import pw.pcshop.verifiers.VerifierUtils;
import pw.pcshop.viewModels.GraphicsCardVM;

@Service
@RequiredArgsConstructor
public class GraphicsCardService {
    private final GraphicsCardRepository graphicsCardRepository;

    public List<GraphicsCardVM> getAll() {
        return GraphicsCardVM.toGraphicsCardVMs(graphicsCardRepository.findAll());
    }

    public void add(GraphicsCardVM graphicsCardVM) {
        VerifierUtils.throwIfNotCorrect(GraphicsCardVerifier.verifyGraphicsCard(graphicsCardVM));
        GraphicsCard graphicsCard = new GraphicsCard();
        graphicsCard.setHeight(graphicsCardVM.height);
        graphicsCard.setLength(graphicsCardVM.length);
        graphicsCard.setManufacturer(graphicsCardVM.manufacturer);
        graphicsCard.setMemoryType(graphicsCardVM.memoryType);
        graphicsCard.setSeries(graphicsCardVM.series);
        graphicsCard.setVRAM(graphicsCardVM.vRAM);
        graphicsCard.setWidth(graphicsCardVM.width);
        graphicsCardRepository.save(graphicsCard);
    }
}
