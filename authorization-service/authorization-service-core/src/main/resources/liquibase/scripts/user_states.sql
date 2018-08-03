--liquibase format sql

--changeset barysevich:user_states_table
CREATE TABLE IF NOT EXISTS user_states
(
  id    SERIAL       NOT NULL PRIMARY KEY,
  value VARCHAR(128) NOT NULL
);

--changeset barysevich:inserts_user_states
INSERT INTO user_states (value) VALUES (unnest(array ['NEW', 'ACTIVE', 'BLOCKED']));
