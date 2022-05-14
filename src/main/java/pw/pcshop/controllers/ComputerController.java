package pw.pcshop.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pw.pcshop.services.ComputerService;
import pw.pcshop.viewModels.ComputerVM;

@RestController
@RequestMapping(path = "api/computer")
@RequiredArgsConstructor
public class ComputerController {
    private final ComputerService computerService;

    @GetMapping
    public ApiResult<List<ComputerVM>> getAll() {
        try {
            return new ApiResult<List<ComputerVM>>().OK(computerService.getAll());
        } catch (RuntimeException e) {
            return new ApiResult<List<ComputerVM>>().Error(e.getMessage());
        }
    }

    @PostMapping
    public ApiResult<Void> add(ComputerVM computerVM) {
        try {
            computerService.add(computerVM);
            return new ApiResult<Void>().OK();
        } catch (RuntimeException e) {
            return new ApiResult<Void>().Error(e.getMessage());
        }
    }
}
