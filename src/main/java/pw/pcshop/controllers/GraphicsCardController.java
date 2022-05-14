package pw.pcshop.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ApiResult<List<GraphicsCardVM>> getAll() {
        try {
            return new ApiResult<List<GraphicsCardVM>>().OK(graphicsCardService.getAll());
        } catch (RuntimeException e) {
            return new ApiResult<List<GraphicsCardVM>>().Error(e.getMessage());
        }
    }

    @PostMapping
    public ApiResult<Void> add(GraphicsCardVM graphicsCardVM) {
        try {
            graphicsCardService.add(graphicsCardVM);
            return new ApiResult<Void>().OK();
        } catch (RuntimeException e) {
            return new ApiResult<Void>().Error(e.getMessage());
        }
    }
}
