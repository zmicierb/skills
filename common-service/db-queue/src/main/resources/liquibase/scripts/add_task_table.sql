--liquibase formatted sql

--changeset barysevich:task_table

CREATE TABLE tasks (
  id             BIGSERIAL PRIMARY KEY             NOT NULL,
  create_date    TIMESTAMP WITH TIME ZONE          NOT NULL,
  change_date    TIMESTAMP WITH TIME ZONE          NOT NULL,
  execution_date TIMESTAMP WITH TIME ZONE          NOT NULL,
  attempts       INT DEFAULT 0                     NOT NULL,
  task           TEXT                              NOT NULL,
  queue_name     VARCHAR(50)                       NOT NULL
);

CREATE INDEX tasks_idx
  ON tasks (queue_name, execution_date);

ALTER TABLE tasks ADD COLUMN uuid UUID UNIQUE      NOT NULL;