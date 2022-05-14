package pw.pcshop.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pw.pcshop.services.MotherboardService;
import pw.pcshop.viewModels.MotherboardVM;

@RestController
@RequestMapping(path = "api/motherboard")
@RequiredArgsConstructor
public class MotherboardController {
    private final MotherboardService motherboardService;

    @GetMapping
    public ApiResult<List<MotherboardVM>> getAll() {
        try {
            return new ApiResult<List<MotherboardVM>>().OK(motherboardService.getAll());
        } catch (RuntimeException e) {
            return new ApiResult<List<MotherboardVM>>().Error(e.getMessage());
        }
    }

    @PostMapping
    public ApiResult<Void> add(MotherboardVM motherboardVM) {
        try {
            motherboardService.add(motherboardVM);
            return new ApiResult<Void>().OK();
        } catch (RuntimeException e) {
            return new ApiResult<Void>().Error(e.getMessage());
        }
    }
}
