package org.nik.car_rental.repository;


import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.nik.car_rental.entity.Model;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RequiredArgsConstructor
class ModelRepositoryTest extends IntegrationTestBase{

    private static final int MODEL_ID = 2;
    private final ModelRepository modelRepository;



    @Test
    void checkFindAllModels() {
        List<Model> result = modelRepository.findAll();
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    void checkFindModelById() {
        Optional<Model> model = modelRepository.findById(MODEL_ID);
        assertThat(model).isNotNull();
        model.ifPresent(value -> assertThat(value.getModelName()).isEqualTo("Creta"));
    }


    @Test
    void checkSaveModel() {
        Model createModel = Model.builder()
                .modelName("Sentra")
                .capacity(5)
                .build();
        modelRepository.save(createModel);
        assertNotNull(createModel.getId());
    }
}