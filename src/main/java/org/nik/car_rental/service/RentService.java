package org.nik.car_rental.service;

import lombok.RequiredArgsConstructor;
import org.nik.car_rental.dto.RentCreateDto;
import org.nik.car_rental.dto.RentReadDto;
import org.nik.car_rental.entity.RequestStatus;
import org.nik.car_rental.entity.Status;
import org.nik.car_rental.mapper.RentCreateMapper;
import org.nik.car_rental.mapper.RentReadMapper;
import org.nik.car_rental.repository.CarRepository;
import org.nik.car_rental.repository.RentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional
public class RentService {

    private final RentRepository rentRepository;
    private final RentReadMapper rentReadMapper;
    private final RentCreateMapper rentCreateMapper;
    private final CarRepository carRepository;


    public List<RentReadDto> findAllRequests() {
        return rentRepository.findAll().stream()
                .map(rentReadMapper::map)
                .collect(toList());
    }

    public Optional<RentReadDto> findById(Integer id) {
        return rentRepository.findById(id)
                .map(rentReadMapper::map);
    }


    public RentReadDto create(RentCreateDto rentCreateDto) {
        return Optional.of(rentCreateDto)
                .map(rentCreateMapper::map)
                .map(rentRepository::save)
                .map(rentReadMapper::map)
                .orElseThrow();
    }


    @Transactional
    public Optional<RentReadDto> process(Integer id, RentCreateDto rentCreateDto) {
        return rentRepository.findById(id)
                .map(rentEntity -> rentCreateMapper.map(processRentalRequest(rentCreateDto), rentEntity))
                .map(rentRepository::saveAndFlush)
                .map(rentReadMapper::map);
    }

    public RentCreateDto processRentalRequest(RentCreateDto rentCreateDto) {
        if (rentCreateDto.getRequestStatus().equals(RequestStatus.PROCESSING)) {
            String message = "Request approved";
            RequestStatus requestStatus = RequestStatus.CONFIRMED;
            Status status = carRepository.findById(rentCreateDto.getCarId()).orElseThrow().getStatus();
            if (status.equals(Status.RENTED) || status.equals(Status.UNDER_REPAIR)) {
                requestStatus = RequestStatus.REJECTED;
                message = "Choose another car to rent";
            }
            rentCreateDto.setRequestStatus(requestStatus);
            rentCreateDto.setMessage(message);
            return rentCreateDto;
        }
        return rentCreateDto;
    }
}




