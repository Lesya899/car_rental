package org.nik.car_rental.dto;



import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import org.nik.car_rental.entity.RequestStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Builder
@Data
public class RentCreateDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate dateStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate terminationCarRental;

    Integer carId;
    RequestStatus requestStatus;
    Integer userId;
    @NotEmpty
    String passport;
    @NotEmpty
    Integer drivingExperience;
    String message;
}
