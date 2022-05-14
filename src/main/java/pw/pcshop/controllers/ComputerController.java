package pw.pcshop.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pw.pcshop.services.ComputerService;
import pw.pcshop.viewModels.ComputerAddUser;
import pw.pcshop.viewModels.ComputerVM;

@RestController
@CrossOrigin
@RequestMapping(path = "api/computer")
@RequiredArgsConstructor
public class ComputerController {
    private final ComputerService computerService;

    @GetMapping
    public List<ComputerVM> getAll() {
        return computerService.getAll();
    }

    @GetMapping(path = "{id}")
    public double getIncome(@PathVariable("id") Long id) {
        return computerService.getIncome(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody ComputerVM computerVM) {
        computerService.add(computerVM);
    }

    @PutMapping
    public void addUser(@RequestBody ComputerAddUser model) {
        computerService.addUser(model);
    }
}
