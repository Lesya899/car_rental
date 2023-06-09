package org.nik.car_rental.dto;



import lombok.Builder;
import lombok.Value;
import org.nik.car_rental.entity.Status;
import org.springframework.web.multipart.MultipartFile;

@Value
@Builder
public class CarCreateDto {

    String brandName;
    Integer modelId;
    String color;
    Integer rentalPrice;
    MultipartFile image;
    Integer carYear;
    Status status;
}
