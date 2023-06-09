package org.nik.car_rental.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Value
@FieldNameConstants
public class ModelCreateDto {

    @NotEmpty
    String modelName;
    Integer capacity;

}
