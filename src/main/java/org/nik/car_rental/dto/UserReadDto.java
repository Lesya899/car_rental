package org.nik.car_rental.dto;




import lombok.Builder;
import lombok.Value;
import org.nik.car_rental.entity.Role;



@Value
@Builder
public class UserReadDto {
    Integer id;
    String firstName;
    String lastName;
    String phoneNumber;
    String email;
    String password;
    Role role;
}
