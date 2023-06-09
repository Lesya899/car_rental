package org.nik.car_rental.repository;



import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.nik.car_rental.entity.User;
import java.util.Optional;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.nik.car_rental.entity.Role;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;




@RequiredArgsConstructor
class UserRepositoryTest extends IntegrationTestBase {

    private static final int USER_ID = 1;
    private final UserRepository userRepository;



    @Test
    void checkFindAll() {
        List<User> result = userRepository.findAll();
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    void checkFindById() {
        Optional<User> user = userRepository.findById(USER_ID);
        assertThat(user).isNotNull();
        user.ifPresent(value -> assertThat(value.getFirstName()).isEqualTo("Valeriya"));
        user.ifPresent(value -> assertThat(value.getLastName()).isEqualTo("Timofeeva"));
    }


    @Test
    void checkSaveUser() {
        User createUser = User.builder()
                .firstName("Tanya")
                .lastName("Sudakova")
                .phoneNumber("+79257415623")
                .email("sudak@mail.ru")
                .password("ghjkl15698dfg")
                .role(Role.USER)
                .build();
        userRepository.save(createUser);
        assertNotNull(createUser.getId());
    }

    @Test
    void checkDeleteUser() {
        User deleteUser = User.builder()
                .firstName("Oleg")
                .lastName("Strokov")
                .phoneNumber("+79657852365")
                .email("str@mail.com")
                .password("ghjklou11456")
                .role(Role.USER)
                .build();
        userRepository.save(deleteUser);
        userRepository.delete(deleteUser);
        Optional<User> user = userRepository.findById(deleteUser.getId());
        assertTrue(user.isEmpty());
    }

}

