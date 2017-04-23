DO $$

DECLARE   pos           CHARACTER VARYING(1000) = 'Java Developer';
  DECLARE depart        CHARACTER VARYING(500) = 'Application Development department';
  DECLARE personName    CHARACTER VARYING(1000) = 'Dzmitry Barysevich';
  DECLARE birthDate     DATE = '1987-06-20';
  DECLARE truncateQuery TEXT = 'TRUNCATE TABLE person CASCADE; ' ||
                               'TRUNCATE TABLE department CASCADE; ' ||
                               'TRUNCATE TABLE position CASCADE;';

BEGIN
  EXECUTE truncateQuery;

  INSERT INTO position (name) VALUES (pos);
  INSERT INTO department (name) VALUES (depart);
  INSERT INTO person (name, department_id, position_id, birth_date)
  VALUES (personName,
          (SELECT department.id
           FROM department
           WHERE department.name = depart),
          (SELECT position.id
           FROM position
           WHERE position.name = pos),
          birthDate);

END $$;