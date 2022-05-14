package pw.pcshop.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pw.pcshop.services.ProcessorService;
import pw.pcshop.viewModels.ProcessorVM;

@RestController
@RequestMapping(path = "api/processor")
@RequiredArgsConstructor
public class ProcessorController {
    private final ProcessorService processorService;

    @GetMapping
    public ApiResult<List<ProcessorVM>> getAll() {
        try {
            return new ApiResult<List<ProcessorVM>>().OK(processorService.getAll());
        } catch (RuntimeException e) {
            return new ApiResult<List<ProcessorVM>>().Error(e.getMessage());
        }
    }

    @PostMapping
    public ApiResult<Void> add(ProcessorVM processorVM) {
        try {
            processorService.add(processorVM);
            return new ApiResult<Void>().OK();
        } catch (RuntimeException e) {
            return new ApiResult<Void>().Error(e.getMessage());
        }
    }
}
