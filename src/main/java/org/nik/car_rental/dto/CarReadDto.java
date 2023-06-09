package org.nik.car_rental.dto;



import lombok.Builder;
import lombok.Value;
import org.nik.car_rental.entity.Status;

@Value
@Builder
public class CarReadDto {
    Integer id;
    String brandName;
    ModelReadDto modelReadDto;
    String color;
    Integer rentalPrice;
    String image;
    Integer carYear;
    Status status;


}
