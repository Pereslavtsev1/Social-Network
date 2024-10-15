CREATE TABLE roles
(
    role_id   BIGSERIAL PRIMARY KEY,
    authority VARCHAR(255) UNIQUE
);

CREATE TABLE users
(
    user_id           BIGSERIAL PRIMARY KEY,
    username          VARCHAR(255) UNIQUE,
    password          VARCHAR(255),
    first_name        VARCHAR(255),
    last_name         VARCHAR(255),
    date_of_birth     DATE,
    email             VARCHAR(255) UNIQUE,
    phone             VARCHAR(255),
    enabled           BOOLEAN,
    verification_code INTEGER
);
CREATE TABLE users_authority
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (role_id) REFERENCES roles (role_id)
);
