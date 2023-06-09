package org.nik.car_rental.mapper;


import lombok.RequiredArgsConstructor;
import org.nik.car_rental.dto.CarCreateDto;
import org.nik.car_rental.entity.Car;
import org.nik.car_rental.entity.Model;
import org.nik.car_rental.repository.ModelRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import static java.util.function.Predicate.not;

@Component
@RequiredArgsConstructor
public class CarCreateMapper implements Mapper<CarCreateDto, Car> {

    private final ModelRepository modelRepository;

    @Override
    public Car map(CarCreateDto fromObject, Car toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public Car map(CarCreateDto object) {
        Car car = new Car();
        copy(object, car);
        return car;
    }

    private void copy(CarCreateDto object, Car car) {
        car.setBrandName(object.getBrandName());
        car.setModel(getModel(object.getModelId()));
        car.setColor(object.getColor());
        car.setRentalPrice(object.getRentalPrice());
        Optional.ofNullable(object.getImage())
                .filter(not(MultipartFile::isEmpty))
                .ifPresent(image -> car.setImage(image.getOriginalFilename()));
        car.setCarYear(object.getCarYear());
        car.setStatus(object.getStatus());
    }

    public Model getModel(Integer modelId) {
        return Optional.ofNullable(modelId)
                .flatMap(modelRepository::findById)
                .orElse(null);
    }
}
