package org.nik.car_rental.mapper;

import lombok.RequiredArgsConstructor;
import org.nik.car_rental.dto.CarReadDto;
import org.nik.car_rental.dto.ModelReadDto;
import org.nik.car_rental.entity.Car;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CarReadMapper implements Mapper<Car, CarReadDto>{

    private final ModelReadMapper modelReadMapper;

    @Override
    public CarReadDto map(Car object) {
        ModelReadDto modelReadDto = Optional.ofNullable(object.getModel())
                .map(modelReadMapper::map)
                .orElse(null);


        return  CarReadDto.builder()
                .id(object.getId())
                .brandName(object.getBrandName())
                .modelReadDto(modelReadDto)
                .color(object.getColor())
                .rentalPrice(object.getRentalPrice())
                .image(object.getImage())
                .carYear(object.getCarYear())
                .status(object.getStatus())
                .build();
    }
}


