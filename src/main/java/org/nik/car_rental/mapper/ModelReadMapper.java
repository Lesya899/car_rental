package org.nik.car_rental.mapper;


import lombok.RequiredArgsConstructor;
import org.nik.car_rental.dto.ModelReadDto;
import org.nik.car_rental.entity.Model;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ModelReadMapper implements Mapper<Model, ModelReadDto>{

    @Override
    public ModelReadDto map(Model object) {
        return new ModelReadDto(
                object.getId(),
                object.getModelName(),
                object.getCapacity()
        );
    }
}
