package org.nik.car_rental.repository;


import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.nik.car_rental.entity.Rent;
import org.nik.car_rental.entity.RequestStatus;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class RentRepositoryTest extends IntegrationTestBase{

    private static final int RENT_ID = 3;
    private final RentRepository rentRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    @Test
    void checkFindAllRent(){
        List<Rent> rentList = rentRepository.findAll();
        assertThat(rentList.size()).isEqualTo(3);
    }

    @Test
    void checkFindRentById(){
        Optional<Rent> rent = rentRepository.findById(RENT_ID);
        assertThat(rent).isNotNull();
        rent.ifPresent(value -> assertThat(value.getPassport()).isEqualTo("051084127412"));
    }

    @Test
    void checkSaveRent() {
        Rent createRent = Rent.builder()
                .dateStart(LocalDate.of(2023,6,11))
                .terminationCarRental(LocalDate.of(2023, 6,15))
                .car(carRepository.getReferenceById(2))
                .requestStatus(RequestStatus.PROCESSING)
                .user(userRepository.getReferenceById(3))
                .passport("05105236987")
                .drivingExperience(10)
                .message("Car rental request is in processing")
                .build();
        rentRepository.save(createRent);
        assertNotNull(createRent.getId());
    }

}