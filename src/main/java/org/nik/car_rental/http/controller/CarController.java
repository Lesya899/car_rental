package org.nik.car_rental.http.controller;


import lombok.RequiredArgsConstructor;
import org.nik.car_rental.dto.CarCreateDto;
import org.nik.car_rental.service.CarService;
import org.nik.car_rental.service.ModelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final ModelService modelService;




    @GetMapping ("/available-cars")
    public String findAllCars(Model model) {
        model.addAttribute("cars", carService.findAll());
        return "car/cars";
    }

    @ResponseBody
    @GetMapping(value = "/{id}/image", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] findImage(@PathVariable("id") Integer id) {
        return carService.findCarImage(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id, Model model) {
        return carService.findById(id)
                .map(car -> {
                    model.addAttribute("carDescription", car);
                    return "car/description-car";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/add-car")
    public String addCar(Model model, @ModelAttribute("car") CarCreateDto carCreateDto) {
        model.addAttribute("car", carCreateDto);
        model.addAttribute("modelCar", modelService.findAll());
        return "car/add-car";
    }

    @PostMapping("/add-car")
    public String create(@ModelAttribute CarCreateDto carCreateDto) {
        return "redirect:/cars/" + carService.create(carCreateDto).getId();
    }


    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        if (!carService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/cars/available-cars";
    }
}
