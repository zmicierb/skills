--liquibase formatted sql

--changeset barysevich:sequences
CREATE SEQUENCE entities_id_seq START 1000;