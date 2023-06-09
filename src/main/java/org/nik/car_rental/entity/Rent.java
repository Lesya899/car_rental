package org.nik.car_rental.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"user", "car"})
@Entity
@Table(name = "rent", schema = "public")
public class Rent implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date_start")
    private LocalDate dateStart;

    @Column(name = "termination_car_rental")
    private LocalDate terminationCarRental;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status")
    private RequestStatus requestStatus;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String passport;

    @Column(name = "driving_experience")
    private Integer drivingExperience;

    @Column(name = "mess")
    private String message;
}
