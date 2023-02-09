-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE hello_wolrd (
    label VARCHAR(256),
    PRIMARY KEY (label)
);
