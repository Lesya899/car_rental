INSERT INTO users (id, first_name, last_name, phone_number, email, password, role)
VALUES (1, 'Valeriya', 'Timofeeva', '+79258741236', 'timofeeva@mail.ru', 'fghjy456', 'USER'),
       (2, 'Anton', 'Popov', '+79258747456', 'popov@mail.ru', 'rfvbnm,k541', 'ADMIN'),
       (3, 'Elena', 'Valeeva', '+79058749646', 'valeeva@mail.ru', 'dfvcm,852', 'USER');
SELECT SETVAL('users_id_seq', (SELECT MAX(id) FROM users));

INSERT INTO model (id, model_name, capacity)
VALUES (1, 'CR-V', 5),
       (2, 'Creta', 5),
       (3, 'Mondeo', 5);
SELECT SETVAL('model_id_seq', (SELECT MAX(id) FROM model));

INSERT INTO car (id, brand_name, model_id, color, rental_price, image, car_year, status)
VALUES (1, 'Honda', (SELECT id FROM model WHERE model_name = 'CR-V'), 'red', 1700, 'honda_cr-v.jpg', 2020, 'NOT_RENTED'),
       (2, 'Hyundai', (SELECT id FROM model WHERE model_name = 'Creta'), 'grey', 1050, 'hyundai_creta.jpg', 2021, 'RENTED' ),
       (3, 'Ford', (SELECT id FROM model WHERE model_name = 'Mondeo'), 'white', 1700, 'ford_mondeo.png', 2022, 'NOT_RENTED' );
SELECT SETVAL('car_id_seq', (SELECT MAX(id) FROM car));

INSERT INTO rent (id, date_start, termination_car_rental, car_id, request_status, user_id, passport, driving_experience, mess)
VALUES (1, '2023-06-05', '2023-06-08', 2, 'PROCESSING', (SELECT id FROM users WHERE last_name = 'Timofeeva'), '051084123698',
        5, 'Car rental request is in processing'),
       (2, '2023-06-07', '2023-06-10', 1, 'PROCESSING', (SELECT id FROM users WHERE last_name = 'Valeeva'), '051074123698',
        5, 'Car rental request is in processing'),
       (3, '2023-06-11', '2023-06-15', 3, 'CONFIRMED', (SELECT id FROM users WHERE last_name = 'Popov'), '051084127412',
        8, 'Request approved');

SELECT SETVAL('rent_id_seq', (SELECT MAX(id) FROM rent));