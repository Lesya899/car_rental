package org.nik.car_rental.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.nik.car_rental.dto.ModelCreateDto;
import org.nik.car_rental.dto.ModelReadDto;
import org.nik.car_rental.repository.IntegrationTestBase;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class ModelServiceTest extends IntegrationTestBase {

    private final ModelService modelService;
    private static final int MODEL_ID = 3;

    @Test
    void findAll() {
        List<ModelReadDto> listModel = modelService.findAll();
        assertThat(listModel.size()).isEqualTo(3);
    }

    @Test
    void findById() {
        Optional<ModelReadDto> maybeModel = modelService.findById(MODEL_ID);
        assertTrue(maybeModel.isPresent());
        maybeModel.ifPresent(model -> assertEquals("Mondeo", model.modelName()));
    }

    @Test
    void create() {
        ModelCreateDto modelCreateDto = new  ModelCreateDto(
                "Mustang",
                5
        );
        ModelReadDto actualResult = modelService.create(modelCreateDto);
        assertEquals(modelCreateDto.getModelName(), actualResult.modelName());
        assertEquals(modelCreateDto.getCapacity(), actualResult.capacity());

    }
}