package org.nik.car_rental.mapper;

import lombok.RequiredArgsConstructor;
import org.nik.car_rental.dto.UserCreateDto;
import org.nik.car_rental.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateMapper implements Mapper<UserCreateDto, User> {

    private final PasswordEncoder passwordEncoder;


    @Override
    public User map(UserCreateDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public User map(UserCreateDto object) {
        User user = new User();
        copy(object, user);
        return user;
    }

    private void copy(UserCreateDto object, User user) {
        user.setFirstName(object.getFirstName());
        user.setLastName(object.getLastName());
        user.setPhoneNumber(object.getPhoneNumber());
        user.setEmail(object.getEmail());
        Optional.ofNullable(object.getPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(user::setPassword);
        user.setRole(object.getRole());
    }
}
