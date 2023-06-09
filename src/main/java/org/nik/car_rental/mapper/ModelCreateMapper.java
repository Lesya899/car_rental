package org.nik.car_rental.mapper;


import lombok.RequiredArgsConstructor;
import org.nik.car_rental.dto.ModelCreateDto;
import org.nik.car_rental.entity.Model;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ModelCreateMapper implements Mapper<ModelCreateDto, Model>{

    @Override
    public Model map(ModelCreateDto fromObject, Model toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public Model map(ModelCreateDto object) {
        Model model = new Model();
        copy(object, model);
        return model;
    }

    private void copy(ModelCreateDto object, Model model) {
        model.setModelName(object.getModelName());
        model.setCapacity(object.getCapacity());
    }
}
