package org.nik.car_rental.service;


import lombok.RequiredArgsConstructor;
import org.nik.car_rental.dto.CarCreateDto;
import org.nik.car_rental.dto.CarReadDto;
import org.nik.car_rental.entity.Car;
import org.nik.car_rental.mapper.CarCreateMapper;
import org.nik.car_rental.mapper.CarReadMapper;
import org.nik.car_rental.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CarService {

    private final CarRepository carRepository;
    private final CarReadMapper carReadMapper;
    private final CarCreateMapper carCreateMapper;
    private final ImageService imageService;

    public List<CarReadDto> findAll() {
        return carRepository.findAll().stream()
                .map(carReadMapper::map)
                .collect(toList());
    }

    public Optional<CarReadDto> findById(Integer id) {
        return carRepository.findById(id)
                .map(carReadMapper::map);
    }

    @Transactional
    public CarReadDto create(CarCreateDto carCreateDto) {
        return Optional.of(carCreateDto)
                .map(carCreateMapper::map)
                .map(carRepository::save)
                .map(carReadMapper::map)
                .orElseThrow();
    }



    @Transactional
    public boolean delete(Integer id) {
        return carRepository.findById(id)
                .map(entity -> {
                    carRepository.delete(entity);
                    carRepository.flush();
                    return true;
                })
                .orElse(false);
    }


    public Optional<byte[]> findCarImage(Integer id) {
        return carRepository.findById(id)
                .map(Car::getImage)
                .filter(StringUtils::hasText)
                .flatMap(imageService::get);
    }

}

