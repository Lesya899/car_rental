package org.nik.car_rental.http.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.nik.car_rental.dto.UserCreateDto;
import org.nik.car_rental.dto.UserReadDto;
import org.nik.car_rental.entity.Role;
import org.nik.car_rental.repository.IntegrationTestBase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RequiredArgsConstructor
@AutoConfigureMockMvc
class UserControllerTest extends IntegrationTestBase {

    private final MockMvc mockMvc;


    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/users"))
                .andExpect(model().attributeExists("users"));

    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/users/" + 1))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/user"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attributeExists("roles"))
                .andExpect(model().attribute("roles", Role.values()))
                .andExpect(model().attribute("user", getUser()));

    }

    @Test
    void registration() throws Exception {
        mockMvc.perform(get("/users/registration"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/registration"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attributeExists("roles"))
                .andExpect(model().attribute("roles", Role.values()))
                .andExpect(model().attribute("user", UserCreateDto.builder().build()));
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/users")
                        .param("firstName", "Anna")
                        .param("lastName", "Ivanova")
                        .param("phoneNumber", "+79634587125")
                        .param("email", "anna@mail.com")
                        .param("password", "fgyuopp4569")
                        .param("role", "USER")
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/login*")
                );
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(post("/users/1/update")
                        .param("firstName", "Anna")
                        .param("lastName", "Ivanova")
                        .param("phoneNumber", "+79634587125")
                        .param("email", "anna@mail.ru")
                        .param("password", "fgyuopp4569")
                        .param("role", "ADMIN")
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrl("/users/1")
                );
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(post("/users/1/delete"))
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrl("/users")
                );
    }

    private UserReadDto getUser() {
        return UserReadDto.builder()
                .id(1)
                .firstName("Valeriya")
                .lastName("Timofeeva")
                .phoneNumber("+79258741236")
                .email("timofeeva@mail.ru")
                .password("fghjy456")
                .role(Role.USER)
                .build();
    }
}