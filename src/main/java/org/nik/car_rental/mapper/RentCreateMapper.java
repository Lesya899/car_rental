package org.nik.car_rental.mapper;

import lombok.RequiredArgsConstructor;
import org.nik.car_rental.dto.RentCreateDto;
import org.nik.car_rental.entity.Car;
import org.nik.car_rental.entity.Rent;
import org.nik.car_rental.entity.User;
import org.nik.car_rental.repository.CarRepository;
import org.nik.car_rental.repository.UserRepository;
import org.springframework.stereotype.Component;


import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RentCreateMapper implements Mapper<RentCreateDto, Rent> {

    private final UserRepository userRepository;
    private final CarRepository carRepository;


    @Override
    public Rent map(RentCreateDto fromObject, Rent toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public Rent map(RentCreateDto object) {
        Rent rent = new Rent();
        copy(object, rent);
        return rent;
    }

    private void copy(RentCreateDto object, Rent rent) {
        rent.setDateStart(object.getDateStart());
        rent.setTerminationCarRental(object.getTerminationCarRental());
        rent.setCar(getCar(object.getCarId()));
        rent.setRequestStatus(object.getRequestStatus());
        rent.setUser(getUser(object.getUserId()));
        rent.setPassport(object.getPassport());
        rent.setDrivingExperience(object.getDrivingExperience());
        rent.setMessage(object.getMessage());

    }

        public Car getCar(Integer carId) {
            return Optional.ofNullable(carId)
                    .flatMap(carRepository::findById)
                    .orElse(null);
        }

        public User getUser(Integer userId) {
            return Optional.ofNullable(userId)
                    .flatMap(userRepository::findById)
                    .orElse(null);
        }
    }
