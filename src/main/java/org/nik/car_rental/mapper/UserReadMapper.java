package org.nik.car_rental.mapper;

import lombok.RequiredArgsConstructor;
import org.nik.car_rental.dto.UserReadDto;
import org.nik.car_rental.entity.User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {

    @Override
    public UserReadDto map(User object) {
        return UserReadDto.builder()
                .id(object.getId())
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .phoneNumber(object.getPhoneNumber())
                .email(object.getEmail())
                .password(object.getPassword())
                .role(object.getRole())
                .build();

    }
}
