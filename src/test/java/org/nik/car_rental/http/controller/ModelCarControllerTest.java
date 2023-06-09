package org.nik.car_rental.http.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.nik.car_rental.dto.ModelReadDto;
import org.nik.car_rental.repository.IntegrationTestBase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RequiredArgsConstructor
@AutoConfigureMockMvc
class ModelCarControllerTest extends IntegrationTestBase {

    private final MockMvc mockMvc;


    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/car-models"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("car/car-models"))
                .andExpect(model().attributeExists("modelCar"));
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/car-models/" + 2))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("car/model-description"))
                .andExpect(model().attributeExists("model"))
                .andExpect(model().attribute("model", getModel()));

    }


    @Test
    void create() throws Exception {
        mockMvc.perform(post("/car-models/add-model")
                        .param("modelName", "Focus")
                        .param("capacity", "5")
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/car-models/{\\d+}")
                );
    }

    private ModelReadDto getModel() {
        return ModelReadDto.builder()
                .id(2)
                .modelName("Creta")
                .capacity(5)
                .build();
    }
}