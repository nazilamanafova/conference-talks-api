-- liquibase formatted sql
-- changeset nazila:2
CREATE TABLE IF NOT EXISTS talks (
    id BIGSERIAL PRIMARY KEY,
    duration_minutes INTEGER,
    room VARCHAR(255),
    title VARCHAR(255),
    speaker_id BIGINT,
    status VARCHAR(255),
    scheduled_at TIMESTAMP,
    CONSTRAINT fk_talks_speaker FOREIGN KEY (speaker_id) REFERENCES speakers(id)

);