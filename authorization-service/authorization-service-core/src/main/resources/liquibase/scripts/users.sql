--liquibase format sql

--changeset barysevich:users_table
CREATE TABLE users
(
  id         BIGINT DEFAULT nextval('entities_id_seq') NOT NULL PRIMARY KEY,
  login      VARCHAR(300) UNIQUE,
  created_on TIMESTAMP,
  state_id   INTEGER                                   NOT NULL REFERENCES user_states
);
