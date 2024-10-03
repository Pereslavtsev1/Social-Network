CREATE SEQUENCE IF NOT EXISTS roles_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS users_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE roles
(
    role_id   BIGSERIAL PRIMARY KEY,
    authority VARCHAR(255)
);

CREATE TABLE users
(
    user_id           BIGSERIAL PRIMARY KEY,
    first_name        VARCHAR(255),
    last_name         VARCHAR(255),
    email             VARCHAR(255) UNIQUE,
    phone_number      VARCHAR(255),
    password          VARCHAR(255),
    username          VARCHAR(255) UNIQUE,
    verification_code BIGINT,
    enabled           BOOLEAN NOT NULL,
    date_of_birth     DATE
);

CREATE TABLE users_authorities
(
    role_id BIGSERIAL NOT NULL REFERENCES roles (role_id) ON DELETE CASCADE,
    user_id BIGSERIAL NOT NULL REFERENCES users (user_id) ON DELETE CASCADE,
    PRIMARY KEY (role_id, user_id)
);
