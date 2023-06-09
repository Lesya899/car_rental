package org.nik.car_rental.http.controller;


import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.nik.car_rental.dto.CarReadDto;
import org.nik.car_rental.repository.IntegrationTestBase;
import org.nik.car_rental.service.CarService;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RequiredArgsConstructor
@AutoConfigureMockMvc
class CarControllerTest extends IntegrationTestBase {

    private final MockMvc mockMvc;
    private final CarService carService;

    @Test
    void findAllCars() throws Exception{
        mockMvc.perform(get("/cars/available-cars"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("car/cars"))
                .andExpect(model().attributeExists("cars"));
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/cars/" + 3))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("car/description-car"))
                .andExpect(model().attributeExists("carDescription"))
                .andExpect(model().attribute("carDescription", getCar()));

    }



    @Test
    void create() throws Exception {

        mockMvc.perform(post("/cars/add-car")
                        .param("brandName", "Honda")
                        .param("modelId", "1")
                        .param("color", "black")
                        .param("rentalPrice", "1500")
                        .param("image","honda_cr-v.jpg")
                        .param("carYear","2022")
                        .param("status","NOT_RENTED")
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/cars/{\\d+}")
                );
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(post("/cars/1/delete"))
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrl("/cars/available-cars")
                );

    }

    private CarReadDto getCar() {
        return carService.findById(3).get();
    }

}