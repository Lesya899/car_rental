package org.nik.car_rental.repository;


import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.nik.car_rental.entity.Car;
import org.nik.car_rental.entity.Status;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
class CarRepositoryTest extends IntegrationTestBase{

    private static final int CAR_ID = 3;
    private final CarRepository carRepository;
    private final ModelRepository modelRepository;



    @Test
    void checkFindAllCar() {
        List<Car> result = carRepository.findAll();
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    void checkFindCarById() {
        Optional<Car> car = carRepository.findById(CAR_ID);
        assertThat(car).isNotNull();
        car.ifPresent(value -> assertThat(value.getBrandName()).isEqualTo("Ford"));
        car.ifPresent(value -> assertThat(value.getModel().getModelName()).isEqualTo("Mondeo"));
    }


    @Test
    void checkSaveCar() {
        Car createCar = Car.builder()
                .brandName("Hyundai")
                .model(modelRepository.getReferenceById(2))
                .color("blue")
                .rentalPrice(1800)
                .image("Hyundai_Creta_N_Line.jpg")
                .carYear(2022)
                .status(Status.NOT_RENTED)
                .build();
        carRepository.save(createCar);
        assertNotNull(createCar.getId());
    }

    @Test
    void checkDeleteCar() {
        Car deleteCar = Car.builder()
                .brandName("Ford")
                .model(modelRepository.getReferenceById(3))
                .color("black")
                .rentalPrice(1700)
                .image("ford_mondeo_black.jpg")
                .carYear(2021)
                .status(Status.NOT_RENTED)
                .build();
        carRepository.save(deleteCar);
        carRepository.delete(deleteCar);
        Optional<Car> car = carRepository.findById(deleteCar.getId());
        assertTrue(car.isEmpty());
    }

}