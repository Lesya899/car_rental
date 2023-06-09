package org.nik.car_rental.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.nik.car_rental.dto.CarCreateDto;
import org.nik.car_rental.dto.CarReadDto;
import org.nik.car_rental.entity.Status;
import org.nik.car_rental.repository.IntegrationTestBase;
import org.springframework.mock.web.MockMultipartFile;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RequiredArgsConstructor
class CarServiceTest extends IntegrationTestBase {

    private final CarService carService;
    private static final int CAR_ID = 2;

    @Test
    void findAll() {
        List<CarReadDto> listCar = carService.findAll();
        assertThat(listCar.size()).isEqualTo(3);
    }

    @Test
    void findById() {
        Optional<CarReadDto> maybeCar = carService.findById(CAR_ID);
        assertTrue(maybeCar.isPresent());
        maybeCar.ifPresent(car -> assertEquals("Hyundai", car.getBrandName()));
    }

    @Test
    void create() {
        CarCreateDto carCreateDto = CarCreateDto.builder()
                .brandName("Hyundai")
                .modelId(2)
                .color("blue")
                .rentalPrice(1800)
                .image(new MockMultipartFile("test", new byte[0]))
                .carYear(2022)
                .status(Status.NOT_RENTED)
                .build();

        CarReadDto actualResult = carService.create(carCreateDto);
        assertEquals(carCreateDto.getBrandName(), actualResult.getBrandName());
        assertEquals(carCreateDto.getModelId(),actualResult.getModelReadDto().id());
        assertEquals(carCreateDto.getColor(), actualResult.getColor());
        assertEquals(carCreateDto.getRentalPrice(), actualResult.getRentalPrice());
        assertEquals(carCreateDto.getCarYear(), actualResult.getCarYear());
        assertEquals(carCreateDto.getStatus(), actualResult.getStatus());
    }



    @Test
    void delete() {
        CarCreateDto carCreateDto = CarCreateDto.builder()
                .brandName("Honda")
                .modelId(1)
                .color("grey")
                .rentalPrice(1600)
                .image(new MockMultipartFile("test", new byte[0]))
                .carYear(2021)
                .status(Status.NOT_RENTED)
                .build();

        CarReadDto result = carService.create(carCreateDto);
        assertTrue(carService.delete(result.getId()));
    }
}