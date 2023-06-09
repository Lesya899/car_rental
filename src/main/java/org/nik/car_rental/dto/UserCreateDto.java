package org.nik.car_rental.dto;



import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.nik.car_rental.entity.Role;


@Value
@Builder
@FieldNameConstants
public class UserCreateDto {
    @NotBlank(message = "First name shouldn't be empty")
    @Size(min = 3, max = 64)
    String firstName;

    @NotBlank(message = "Last name shouldn't be empty")
    @Size(min = 3, max = 64)
    String lastName;

    @NotEmpty
    @Size(min = 10)
    String phoneNumber;

    @Email
    String email;

    @Size(min = 6)
    String password;

    Role role;
}
