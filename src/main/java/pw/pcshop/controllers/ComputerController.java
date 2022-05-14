package pw.pcshop.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    public List<ComputerVM> getAll() {
        return computerService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(ComputerVM computerVM) {
        computerService.add(computerVM);
    }
}
