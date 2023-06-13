package org.nik.car_rental.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Value
@Builder
public class ModelCreateDto {

    @NotEmpty
    String modelName;
    Integer capacity;

}
