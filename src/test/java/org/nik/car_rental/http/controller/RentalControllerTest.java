package org.nik.car_rental.http.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.nik.car_rental.dto.RentReadDto;
import org.nik.car_rental.repository.IntegrationTestBase;
import org.nik.car_rental.service.RentService;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@RequiredArgsConstructor
class RentalControllerTest extends IntegrationTestBase {

    private final MockMvc mockMvc;
    private final RentService rentService;


    @Test
    void findAllRequest() throws Exception{
        mockMvc.perform(get("/rental"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("/rent/rental-requests"))
                .andExpect(model().attributeExists("rent"));
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/rental/" + 2))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("rent/description-request"))
                .andExpect(model().attributeExists("rent"))
                .andExpect(model().attribute("rent", getRent()));

    }

    private RentReadDto getRent() {
        return rentService.findById(2).get();
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/rental/create-request")
                        .param("dateStart", "2023-06-15")
                        .param("terminationCarRental", "2023-06-20")
                        .param("carId", "2")
                        .param("requestStatus", "PROCESSING")
                        .param("userId","3")
                        .param("passport","056398524753")
                        .param("drivingExperience","7")
                        .param("mess","Car rental request is in processing")
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/rental/{\\d+}")
                );
    }
}