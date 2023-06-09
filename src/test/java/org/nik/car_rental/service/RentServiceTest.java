package org.nik.car_rental.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.nik.car_rental.dto.RentCreateDto;
import org.nik.car_rental.dto.RentReadDto;
import org.nik.car_rental.entity.RequestStatus;
import org.nik.car_rental.repository.IntegrationTestBase;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RequiredArgsConstructor
class RentServiceTest extends IntegrationTestBase {

    private final RentService rentService;
    private static final int RENT_ID = 2;

    @Test
    void findAllRequests() {
        List<RentReadDto> listRent = rentService.findAllRequests();
        assertThat(listRent.size()).isEqualTo(3);
    }

    @Test
    void findById() {
        Optional<RentReadDto> maybeRent = rentService.findById(RENT_ID);
        assertTrue(maybeRent.isPresent());
        maybeRent.ifPresent(rent -> assertEquals("051074123698", rent.getPassport()));
    }

    @Test
    void create() {
        RentCreateDto rentCreateDto = RentCreateDto.builder()
                .dateStart(LocalDate.of(2023,6,11))
                .terminationCarRental(LocalDate.of(2023,6,18))
                .carId(2)
                .requestStatus(RequestStatus.PROCESSING)
                .userId(1)
                .passport("051096354127")
                .drivingExperience(8)
                .message("Car rental request is in processin")
                .build();
        RentReadDto actualResult = rentService.create(rentCreateDto);
        assertEquals(rentCreateDto.getDateStart(), actualResult.getDateStart());
        assertEquals(rentCreateDto.getTerminationCarRental(), actualResult.getTerminationCarRental());
        assertEquals(rentCreateDto.getCarId(), actualResult.getCarReadDto().getId());
        assertEquals(rentCreateDto.getRequestStatus(), actualResult.getRequestStatus());
        assertEquals(rentCreateDto.getUserId(), actualResult.getUserReadDto().getId());
        assertEquals(rentCreateDto.getPassport(), actualResult.getPassport());
        assertEquals(rentCreateDto.getDrivingExperience(), actualResult.getDrivingExperience());
        assertEquals(rentCreateDto.getMessage(), actualResult.getMessage());
    }

}