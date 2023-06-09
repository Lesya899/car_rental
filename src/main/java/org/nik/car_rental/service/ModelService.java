package org.nik.car_rental.service;

import lombok.RequiredArgsConstructor;
import org.nik.car_rental.dto.CarCreateDto;
import org.nik.car_rental.dto.CarReadDto;
import org.nik.car_rental.dto.ModelCreateDto;
import org.nik.car_rental.dto.ModelReadDto;
import org.nik.car_rental.mapper.ModelCreateMapper;
import org.nik.car_rental.mapper.ModelReadMapper;
import org.nik.car_rental.repository.ModelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ModelService {

    private final ModelRepository modelRepository;
    private final ModelReadMapper modelReadMapper;
    private final ModelCreateMapper modelCreateMapper;

    public List<ModelReadDto> findAll() {
        return modelRepository.findAll().stream()
                .map(modelReadMapper::map)
                .collect(toList());
    }

    public Optional<ModelReadDto> findById(Integer id) {
        return modelRepository.findById(id)
                .map(modelReadMapper::map);
    }

    @Transactional
    public ModelReadDto create(ModelCreateDto modelCreateDto) {
        return Optional.of(modelCreateDto)
                .map(modelCreateMapper::map)
                .map(modelRepository::save)
                .map(modelReadMapper::map)
                .orElseThrow();
    }
}
