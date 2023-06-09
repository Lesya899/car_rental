package org.nik.car_rental.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.nik.car_rental.dto.UserCreateDto;
import org.nik.car_rental.dto.UserReadDto;
import org.nik.car_rental.entity.Role;
import org.nik.car_rental.repository.IntegrationTestBase;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertSame;


@RequiredArgsConstructor
class UserServiceTest extends IntegrationTestBase {

    private final UserService userService;
    private static final int USER_ID = 1;

    @Test
    void findAll() {
        List<UserReadDto> listUser = userService.findAll();
        assertThat(listUser.size()).isEqualTo(3);
    }

    @Test
    void findById() {
        Optional<UserReadDto> maybeUser = userService.findById(USER_ID);
        assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(user -> assertEquals("timofeeva@mail.ru", user.getEmail()));
    }

    @Test
    void create() {
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .firstName("Alla")
                .lastName("Razumovskaya")
                .phoneNumber("+79286398745")
                .email("razumovskaya23@mail.ru")
                .password("fjkkti852")
                .role(Role.USER)
                .build();
        UserReadDto actualResult = userService.create(userCreateDto);
        assertEquals(userCreateDto.getFirstName(), actualResult.getFirstName());
        assertEquals(userCreateDto.getLastName(),actualResult.getLastName());
        assertEquals(userCreateDto.getPhoneNumber(), actualResult.getPhoneNumber());
        assertEquals(userCreateDto.getEmail(), actualResult.getEmail());
        assertEquals(userCreateDto.getRole(), actualResult.getRole());
        assertSame(userCreateDto.getRole(), actualResult.getRole());
    }

    @Test
    void update() {
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .firstName("Sergey")
                .lastName("Davydov")
                .phoneNumber("+79657412589")
                .email("dav8967@mail.ru")
                .password("dfhjt632547")
                .role(Role.USER)
                .build();

        Optional<UserReadDto> actualResult = userService.update(USER_ID, userCreateDto);
        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(user -> {
            assertEquals(userCreateDto.getFirstName(), user.getFirstName());
            assertEquals(userCreateDto.getLastName(),user.getLastName());
            assertEquals(userCreateDto.getPhoneNumber(), user.getPhoneNumber());
            assertEquals(userCreateDto.getEmail(), user.getEmail());
            assertEquals(userCreateDto.getRole(), user.getRole());
            assertSame(userCreateDto.getRole(), user.getRole());
        });
    }

    @Test
    void delete() {
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .firstName("Elena")
                .lastName("Sortseva")
                .phoneNumber("+79659632589")
                .email("sor741@mail.ru")
                .password("sertu5236")
                .role(Role.USER)
                .build();

        UserReadDto result = userService.create(userCreateDto);
        assertTrue(userService.delete(result.getId()));
    }
}