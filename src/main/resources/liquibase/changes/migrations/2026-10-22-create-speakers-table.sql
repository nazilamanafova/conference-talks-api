-- liquibase formatted sql
-- changeset nazila:1
CREATE TABLE IF NOT EXISTS speakers(
    id BIGSERIAL PRIMARY KEY,
    company VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    full_name VARCHAR(255)

);