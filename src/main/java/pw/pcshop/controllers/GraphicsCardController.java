package pw.pcshop.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pw.pcshop.services.GraphicsCardService;
import pw.pcshop.viewModels.GraphicsCardVM;

@RestController
@RequestMapping(path = "api/graphicsCard")
@RequiredArgsConstructor
public class GraphicsCardController {
    private final GraphicsCardService graphicsCardService;

    @GetMapping
    public List<GraphicsCardVM> getAll() {
        return graphicsCardService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(GraphicsCardVM graphicsCardVM) {
        graphicsCardService.add(graphicsCardVM);
    }
}
