package org.nik.car_rental.mapper;


import lombok.RequiredArgsConstructor;
import org.nik.car_rental.dto.CarReadDto;
import org.nik.car_rental.dto.RentReadDto;
import org.nik.car_rental.dto.UserReadDto;
import org.nik.car_rental.entity.Rent;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RentReadMapper implements Mapper<Rent, RentReadDto> {

    private final CarReadMapper carReadMapper;
    private final UserReadMapper userReadMapper;

    @Override
    public RentReadDto map(Rent object) {
        CarReadDto carReadDto = Optional.ofNullable(object.getCar())
                .map(carReadMapper::map)
                .orElse(null);
        UserReadDto userReadDto = Optional.ofNullable(object.getUser())
                .map(userReadMapper::map)
                .orElse(null);

        return  RentReadDto.builder()
                .id(object.getId())
                .dateStart(object.getDateStart())
                .terminationCarRental(object.getTerminationCarRental())
                .carReadDto(carReadDto)
                .requestStatus(object.getRequestStatus())
                .userReadDto(userReadDto)
                .passport(object.getPassport())
                .drivingExperience(object.getDrivingExperience())
                .message(object.getMessage())
                .build();
    }
}

