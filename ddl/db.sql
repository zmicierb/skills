SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = ON;
SET edb_redwood_date = OFF;
SET default_with_rowids = OFF;
SET check_function_bodies = FALSE;
SET client_min_messages = WARNING;
SET row_security = OFF;
SET default_with_oids = FALSE;

DROP TABLE SKILL_SUM;
DROP TABLE ENVIRONMENT_SKILL;
DROP TABLE PROJECT;
DROP TABLE PERSON;
DROP TABLE ROW;
DROP TABLE COMPANY_INFO;
DROP TABLE SKILL;
DROP TABLE POSITION;
DROP TABLE DEPARTMENT;

CREATE SEQUENCE DEPARTMENT_ID_SEQ
START WITH 1000
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE DEPARTMENT (
  id   INTEGER DEFAULT nextval('DEPARTMENT_ID_SEQ' :: REGCLASS) NOT NULL,
  name CHARACTER VARYING(500)                                   NOT NULL,
  CONSTRAINT DEPARTMENT_NAME_UNQ UNIQUE (name)
);

ALTER SEQUENCE DEPARTMENT_ID_SEQ OWNED BY DEPARTMENT.id;

CREATE SEQUENCE COMPANY_INFO_ID_SEQ
START WITH 1000
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE COMPANY_INFO (
  id         INTEGER DEFAULT nextval('COMPANY_INFO_ID_SEQ') NOT NULL,
  name       CHARACTER VARYING(1000),
  start_date TIMESTAMP WITHOUT TIME ZONE,
  end_date   TIMESTAMP WITHOUT TIME ZONE
);

ALTER SEQUENCE COMPANY_INFO_ID_SEQ OWNED BY COMPANY_INFO.id;

CREATE SEQUENCE ENVIRONMENT_SKILL_ID_SEQ
START WITH 1000
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE ENVIRONMENT_SKILL (
  id         INTEGER DEFAULT nextval('ENVIRONMENT_SKILL_ID_SEQ') NOT NULL,
  project_id INTEGER,
  skill_id   INTEGER,
  weight     INTEGER
);

ALTER SEQUENCE ENVIRONMENT_SKILL_ID_SEQ OWNED BY ENVIRONMENT_SKILL.id;

CREATE SEQUENCE PERSON_ID_SEQ
START WITH 1000
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE PERSON (
  id            INTEGER DEFAULT nextval('PERSON_ID_SEQ') NOT NULL,
  name          CHARACTER VARYING(1000)                  NOT NULL,
  department_id INTEGER,
  position_id   INTEGER,
  deleted       SMALLINT                                 NOT NULL DEFAULT 0,
  birth_date    TIMESTAMP WITHOUT TIME ZONE
);

ALTER SEQUENCE PERSON_ID_SEQ OWNED BY PERSON.id;

CREATE SEQUENCE POSITION_ID_SEQ
START WITH 1000
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE POSITION (
  id   INTEGER DEFAULT nextval('POSITION_ID_SEQ') NOT NULL,
  name CHARACTER VARYING(1000)                    NOT NULL,
  CONSTRAINT POSITION_NAME_UNQ UNIQUE (name)
);

ALTER SEQUENCE POSITION_ID_SEQ OWNED BY POSITION.id;

CREATE SEQUENCE PROJECT_ID_SEQ
START WITH 1000
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE PROJECT (
  id             INTEGER DEFAULT nextval('PROJECT_ID_SEQ') NOT NULL,
  person_id      INTEGER,
  position_id    INTEGER,
  description    CHARACTER VARYING,
  result         CHARACTER VARYING,
  responsibility CHARACTER VARYING,
  company_id     INTEGER,
  deleted        SMALLINT                                  NOT NULL DEFAULT 0
);

ALTER SEQUENCE PROJECT_ID_SEQ OWNED BY PROJECT.id;

CREATE SEQUENCE ROW_ID_SEQ
START WITH 1000
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE ROW (
  id   INTEGER DEFAULT nextval('ROW_ID_SEQ') NOT NULL,
  name CHARACTER VARYING(100)                NOT NULL,
  CONSTRAINT ROW_NAME_UNQ UNIQUE (name)
);

ALTER SEQUENCE ROW_ID_SEQ OWNED BY ROW.id;

CREATE SEQUENCE SKILL_ID_SEQ
START WITH 1000
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE SKILL (
  id   INTEGER DEFAULT nextval('SKILL_ID_SEQ') NOT NULL,
  name CHARACTER VARYING(100)                  NOT NULL,
  CONSTRAINT SKILL_NAME_UNQ UNIQUE (name)
);

ALTER SEQUENCE SKILL_ID_SEQ OWNED BY SKILL.id;

CREATE SEQUENCE SKILL_SUM_ID_SEQ
START WITH 1000
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE SKILL_SUM (
  id        INTEGER DEFAULT nextval('SKILL_SUM_ID_SEQ') NOT NULL,
  person_id INTEGER,
  skill_id  INTEGER,
  row_id    INTEGER,
  weight    INTEGER
);

ALTER SEQUENCE SKILL_SUM_ID_SEQ OWNED BY SKILL_SUM.id;

ALTER TABLE ONLY DEPARTMENT
  ADD CONSTRAINT DEPARTMENT_PK PRIMARY KEY (id);

ALTER TABLE ONLY COMPANY_INFO
  ADD CONSTRAINT COMPANY_INFO_PK PRIMARY KEY (id);

ALTER TABLE ONLY ENVIRONMENT_SKILL
  ADD CONSTRAINT ENVIRONMENT_PK PRIMARY KEY (id);

ALTER TABLE ONLY PERSON
  ADD CONSTRAINT PERSON_PK PRIMARY KEY (id);

ALTER TABLE ONLY POSITION
  ADD CONSTRAINT POSITION_PK PRIMARY KEY (id);

ALTER TABLE ONLY PROJECT
  ADD CONSTRAINT PROJECT_PK PRIMARY KEY (id);

ALTER TABLE ONLY ROW
  ADD CONSTRAINT ROW_PK PRIMARY KEY (id);

ALTER TABLE ONLY SKILL
  ADD CONSTRAINT SKILL_PK PRIMARY KEY (id);

ALTER TABLE ONLY SKILL_SUM
  ADD CONSTRAINT SKILL_SUM_PK PRIMARY KEY (id);

CREATE INDEX fki_ENVIRONMENT_SKILL_PROJECT_ID_FK
  ON ENVIRONMENT_SKILL USING BTREE (project_id);

CREATE INDEX fki_ENVIRONMENT_SKILL_SKILL_ID
  ON ENVIRONMENT_SKILL USING BTREE (skill_id);

CREATE INDEX fki_PERSON_POSITION_ID_FK
  ON PERSON USING BTREE (position_id);

CREATE INDEX fki_PERSON_DEPARTMENT_ID_FK
  ON PERSON USING BTREE (department_id);

CREATE INDEX fki_PROJECT_POSITION_ID_FK
  ON PROJECT USING BTREE (position_id);

CREATE INDEX fki_PROJECT_COMPANY_ID_FK
  ON PROJECT USING BTREE (company_id);

CREATE INDEX fki_PROJECT_PERSON_ID_FK
  ON PROJECT USING BTREE (person_id);

CREATE INDEX fki_SKILL_SUM_ROW_ID_FK
  ON SKILL_SUM USING BTREE (row_id);

CREATE INDEX fki_SKILL_SUM_SKILL_ID
  ON SKILL_SUM USING BTREE (skill_id);

CREATE INDEX fki_SKILL_SUM_PERSON_ID_FK
  ON SKILL_SUM USING BTREE (person_id);

ALTER TABLE ONLY ENVIRONMENT_SKILL
  ADD CONSTRAINT ENVIRONMENT_SKILL_PROJECT_ID_FK FOREIGN KEY (project_id) REFERENCES PROJECT (id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY ENVIRONMENT_SKILL
  ADD CONSTRAINT ENVIRONMENT_SKILL_SKILL_ID_FK FOREIGN KEY (skill_id) REFERENCES SKILL (id) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE ONLY PERSON
  ADD CONSTRAINT PERSON_POSITION_ID_FK FOREIGN KEY (position_id) REFERENCES POSITION (id) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE ONLY PERSON
  ADD CONSTRAINT PERSON_DEPARTMENT_ID_FK FOREIGN KEY (department_id) REFERENCES DEPARTMENT (id) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE ONLY PROJECT
  ADD CONSTRAINT PROJECT_POSITION_ID_FK FOREIGN KEY (position_id) REFERENCES POSITION (id) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE ONLY PROJECT
  ADD CONSTRAINT PROJECT_COMPANY_ID_FK FOREIGN KEY (company_id) REFERENCES COMPANY_INFO (id) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE ONLY PROJECT
  ADD CONSTRAINT PROJECT_PERSON_ID_FK FOREIGN KEY (person_id) REFERENCES PERSON (id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY SKILL_SUM
  ADD CONSTRAINT SKILL_SUM_ROW_ID_FK FOREIGN KEY (row_id) REFERENCES ROW (id) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE ONLY SKILL_SUM
  ADD CONSTRAINT SKILL_SUM_SKILL_ID_FK FOREIGN KEY (skill_id) REFERENCES SKILL (id) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE ONLY SKILL_SUM
  ADD CONSTRAINT SKILL_SUM_PERSON_ID_FK FOREIGN KEY (person_id) REFERENCES PERSON (id) ON UPDATE CASCADE ON DELETE CASCADE;
