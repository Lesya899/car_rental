package org.nik.car_rental.http.controller;


import lombok.RequiredArgsConstructor;
import org.nik.car_rental.dto.CarCreateDto;
import org.nik.car_rental.dto.RentCreateDto;
import org.nik.car_rental.entity.RequestStatus;
import org.nik.car_rental.entity.Role;
import org.nik.car_rental.service.CarService;
import org.nik.car_rental.service.RentService;
import org.nik.car_rental.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.server.ResponseStatusException;


@Controller
@RequestMapping("/rental")
@RequiredArgsConstructor
public class RentalController {

    private final RentService rentService;
    private final UserService userService;
    private final CarService carService;

    @GetMapping
    public String findAllRequest(Model model) {
        model.addAttribute("rent", rentService.findAllRequests());
        return "/rent/rental-requests";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id, Model model) {
        return rentService.findById(id)
                .map(rent -> {
                    model.addAttribute("rent", rent);
                    return "rent/description-request";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/customer-requests")
    public String findCustomerRequests(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("rent", rentService.findAllRequests());
        userService.findByEmail(userDetails.getUsername())
                .map(user -> {
                    model.addAttribute("user", user);
                    model.addAttribute("roles", Role.values());
                    return "rent/customer-requests";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return "rent/customer-requests";
    }

    @GetMapping("/create-request")
    public String createRequest(Model model,
                           @ModelAttribute("rent") RentCreateDto rentCreateDto,
                           @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("rent", rentCreateDto);
        model.addAttribute("status", RequestStatus.PROCESSING);
        model.addAttribute("cars", carService.findAll());
        userService.findByEmail(userDetails.getUsername())
                .map(user -> {
                    model.addAttribute("user", user);
                    model.addAttribute("roles", Role.values());
                    return "rent/create-request";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return "rent/create-request";
    }

    @PostMapping("/create-request")
    public String create(@ModelAttribute RentCreateDto rentCreateDto) {
        rentService.create(rentCreateDto);
        return "redirect:/rental/customer-requests";
    }

    @PostMapping("/{id}/process-request")
    public String process(@PathVariable("id") Integer id, @ModelAttribute RentCreateDto rentCreateDto){
        return rentService.process(id, rentCreateDto)
                .map(rent -> "redirect:/rental/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}

