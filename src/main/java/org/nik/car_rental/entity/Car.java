package org.nik.car_rental.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "car", schema = "public")
public class Car implements BaseEntity<Integer>{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "brand_name")
    private String brandName;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private Model model;

    private String color;

    @Column(name = "rental_price")
    private Integer rentalPrice;

    private String image;

    @Column(name = "car_year")
    private Integer carYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Rent> rent = new ArrayList<>();

}

