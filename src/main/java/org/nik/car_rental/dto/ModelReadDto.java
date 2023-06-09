package org.nik.car_rental.dto;

import lombok.Builder;

@Builder
public record ModelReadDto (Integer id, String modelName, Integer capacity) {

}
