CREATE TABLE IF NOT EXISTS users
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(128) NOT NULL,
    last_name  VARCHAR(128) NOT NULL,
    phone_number VARCHAR(128) NOT NULL,
    email      VARCHAR(132) NOT NULL UNIQUE,
    password   VARCHAR(132) NOT NULL,
    role       VARCHAR(32)  NOT NULL
    );



CREATE TABLE IF NOT EXISTS model
(
    id          SERIAL PRIMARY KEY,
    model_name  VARCHAR(128)     NOT NULL,
    capacity    INT              NOT NULL
    );

CREATE TABLE IF NOT EXISTS car
(
    id              SERIAL PRIMARY KEY,
    brand_name      VARCHAR(32),
    model_id        INT REFERENCES model (id)        NOT NULL,
    color           VARCHAR(32)                      NOT NULL,
    rental_price    INT                              NOT NULL,
    image           VARCHAR(124)                     ,
    car_year        INT                              NOT NULL,
    status          VARCHAR(32)                      NOT NULL
    );

CREATE TABLE IF NOT EXISTS rent
(
    id SERIAL PRIMARY KEY,
    date_start DATE NOT NULL,
    termination_car_rental DATE NOT NULL,
    car_id     INT REFERENCES car (id) NOT NULL,
    request_status     VARCHAR(128) NOT NULL,
    user_id INT REFERENCES users (id) NOT NULL,
    passport VARCHAR(128) NOT NULL,
    driving_experience INT NOT NULL,
    mess TEXT
    );

