package org.nik.car_rental.dto;




import lombok.Builder;
import lombok.Value;
import org.nik.car_rental.entity.RequestStatus;
import java.time.LocalDate;

@Value
@Builder
public class RentReadDto {
    Integer id;
    LocalDate dateStart;
    LocalDate terminationCarRental;
    CarReadDto carReadDto;
    RequestStatus requestStatus;
    UserReadDto userReadDto;
    String passport;
    Integer drivingExperience;
    String message;

}
