package pw.pcshop.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    public List<MotherboardVM> getAll() {
        return motherboardService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody MotherboardVM motherboardVM) {
        motherboardService.add(motherboardVM);
    }
}
