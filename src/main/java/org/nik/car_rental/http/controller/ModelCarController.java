package org.nik.car_rental.http.controller;

import lombok.RequiredArgsConstructor;
import org.nik.car_rental.dto.ModelCreateDto;
import org.nik.car_rental.service.ModelService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/car-models")
@RequiredArgsConstructor
public class ModelCarController {


    private final ModelService modelService;


    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("modelCar", modelService.findAll());
        return "car/car-models";
    }


    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id, Model model) {
        return modelService.findById(id)
                .map(modelCar -> {
                    model.addAttribute("model", modelCar);
                    return "car/model-description";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/add-model")
    public String addCar(Model model, @ModelAttribute("modelCar") ModelCreateDto modelCreateDto) {
        model.addAttribute("modelCar", modelCreateDto);
        return "car/add-model";
    }

    @PostMapping("/add-model")
    public String create(@ModelAttribute ModelCreateDto modelCreateDto) {
        return "redirect:/car-models/" + modelService.create(modelCreateDto).id();
    }
}