package org.nik.car_rental.repository;



import org.nik.car_rental.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {


}
