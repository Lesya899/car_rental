package org.nik.car_rental.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.nik.car_rental.entity.Role.ADMIN;
import static org.nik.car_rental.entity.Role.USER;


@Configuration
@EnableWebSecurity
public class AuthorizeUrlsSecurityConfig {


    @Bean
    @Primary
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/login",  "/users/registration","/users").permitAll()
                        .requestMatchers("/cars/available-cars/", "/users/**", "/user/*", "/add-car/*").hasAuthority(ADMIN.getAuthority())
                        .requestMatchers("/cars/available-cars/").hasAuthority(USER.getAuthority())
                        .anyRequest().authenticated()
                )
                        .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/cars/available-cars", true))
                        .logout(logout -> logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login")
                                .deleteCookies("JSESSIONID"));
        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();

    }
}




