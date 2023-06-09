package org.nik.car_rental.util;

import jakarta.persistence.EntityManager;
import lombok.experimental.UtilityClass;
import org.nik.car_rental.entity.*;
import java.time.LocalDate;


@UtilityClass
public class TestDataImporter {


    private User saveUser(EntityManager entityManager,
                          String firstName,
                          String lastName,
                          String phoneNumber,
                          String email,
                          String password,
                          Role role) {
        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .email(email)
                .password(password)
                .role(role)
                .build();
        entityManager.persist(user);
        return user;
    }


    private Model saveModel(EntityManager entityManager, String modelName, Integer capacity) {
        Model model = Model.builder()
                .modelName(modelName)
                .capacity(capacity)
                .build();
        entityManager.persist(model);
        return model;
    }

    private Car saveCar(EntityManager entityManager, String brandName, Model model, String color, Integer rentalPrice,
                        String image, Integer carYear, Status status) {
        Car car = Car.builder()
                .brandName(brandName)
                .model(model)
                .color(color)
                .rentalPrice(rentalPrice)
                .image(image)
                .status(status)
                .build();
        entityManager.persist(car);

        return car;
    }

    private Rent saveRent(EntityManager entityManager, LocalDate dateStart, LocalDate terminationCarRental, Car car,
                          RequestStatus requestStatus, User user, String passport, Integer drivingExperience, String mess)
                             {
        Rent rent = Rent.builder()
                .dateStart(dateStart)
                .terminationCarRental(terminationCarRental)
                .car(car)
                .requestStatus(requestStatus)
                .user(user)
                .passport(passport)
                .drivingExperience(drivingExperience)
                .message(mess)
                .build();
        entityManager.persist(rent);
        return rent;
    }

    public void importData(EntityManager entityManager) {
        User user1 = saveUser(entityManager, "Valeriya", "Timofeeva", "+79258741236", "timofeeva@mail.ru", "fghjy456", Role.USER);
        User user2 = saveUser(entityManager, "Anton", "Popov", "+79258747456", "popov@mail.ru", "rfvbnm,k541", Role.ADMIN);
        User user3 = saveUser(entityManager, "Elena", "Valeeva", "+79058749646", "valeeva@mail.ru", "dfvcm,852", Role.USER);


        Model model1 = saveModel(entityManager, "CR-V", 5);
        Model model2 = saveModel(entityManager, "Creta", 5);
        Model model3 = saveModel(entityManager, "Mondeo", 5);

        Car car1 = saveCar(entityManager, "Honda", model1,"red", 1700, "honda_cr-v.jpg", 2020, Status.NOT_RENTED);
        Car car2 = saveCar(entityManager, "Hyundai", model2,"grey", 1050, "hyundai_creta.jpg", 2021, Status.NOT_RENTED);
        Car car3 = saveCar(entityManager, "Ford", model3,"white", 1700, "ford_mondeo.png", 2022, Status.NOT_RENTED);


        Rent rent1 = saveRent(entityManager,
                LocalDate.of(2023, 6, 5),
                LocalDate.of(2023, 6, 8),
                car2,
                RequestStatus.PROCESSING,
                user1,
                "051084123698",
                5,
                "Car rental request is in processing");
        Rent rent2 = saveRent(entityManager,
                LocalDate.of(2023, 6, 7),
                LocalDate.of(2023, 6, 10),
                car1,
                RequestStatus.PROCESSING,
                user3,
                "051074123698",
                5,
                "Car rental request is in processing");
        Rent rent3 = saveRent(entityManager,
                LocalDate.of(2023, 6, 11),
                LocalDate.of(2023, 6, 15),
                car3,
                RequestStatus.CONFIRMED,
                user2,
                "051084127412",
                8,
                "Request approved");
    }
}
