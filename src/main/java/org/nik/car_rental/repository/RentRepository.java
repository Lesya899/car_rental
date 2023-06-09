package org.nik.car_rental.repository;


import org.nik.car_rental.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RentRepository extends JpaRepository<Rent, Integer> {


}
