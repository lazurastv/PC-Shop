package pw.pcshop.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    public List<ProcessorVM> getAll() {
        return processorService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(ProcessorVM processorVM) {
        processorService.add(processorVM);
    }
}
