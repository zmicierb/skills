INSERT INTO position (name) VALUES ('Java Developer'), ('Senior System Integrator');
INSERT INTO department (name) VALUES ('Application Development department');
INSERT INTO person (name, department_id, position_id, birth_date)
VALUES ('Dzmitry Barysevich', 1, 1, '1987-06-20');

INSERT INTO row (name) VALUES ('Programming Languages'),
  ('Web Technologies'),
  ('Application Servers'),
  ('Databases'),
  ('Operating Systems'),
  ('Other Skills');

INSERT INTO skill (name) VALUES ('Java 6'),
  ('Java 7'), ('Java 8'), ('SQL'), ('PL/SQL'),
  ('JavaScript'), ('Java EE 6'), ('EJB 3.0'), ('JPA/Hibernate'),
  ('JSF 2.0'), ('JSP'), ('Spring 4.x'), ('JQuery'),
  ('Angular'), ('Apache Velocity'), ('Apache Tomcat'),
  ('IBM WebSphere Application Server 8.5'), ('IBM WebSphere Portal Server 8.5'),
  ('Jetty'), ('Liferay Enterprise'), ('Oracle 11'), ('Oracle 12'),
  ('MySQL'), ('MS SQL'), ('PostgreSQL'), ('Windows XP'), ('Windows 7'),
  ('Windows Server 2008'), ('RHEL'), ('CentOS'), ('Mac OS'), ('HP-UX'),
  ('Jira'), ('Maven'), ('Git'), ('Gulp'), ('Gradle'), ('Eclipse'),
  ('InteliJ IDEA'), ('SQL Developer'), ('Toad for Oracle'), ('JUnit'),
  ('Sonar'), ('Scrum'), ('JDBC'), ('Jasper Report'), ('SAP BI'),
  ('Crystal Report'), ('XSLT'), ('docx4j');

INSERT INTO skill_sum (person_id, skill_id, row_id, weight) VALUES (1, 1, 1, 1), (1, 2, 1, 2),
  (1, 7, 2, 1), (1, 8, 2, 2), (1, 16, 3, 1),
  (1, 17, 3, 2), (1, 21, 4, 1), (1, 23, 4, 2), (1, 29, 5, 1), (1, 30, 5, 2), (1, 42, 6, 1),
  (1, 45, 6, 2);

INSERT INTO company_info (name, start_date, end_date)
VALUES ('IBA', '2016-11-01', NULL), ('BELHARD', '2010-09-01', '2016-10-30');

INSERT INTO project (person_id, position_id, description, result, responsibility, company_id, deleted)
VALUES (1, 1, 'Intranet web-portal for company.', 'Project in progress.', 'Developing portlet.', 1, 0),
  (1, 1, 'Online-shop for sale valuables.', 'First phase is complete.', 'Developing catalog of items.', 1, 0),
  (1, 1, 'Enterprise web-application for flow of documentations.', 'Working project from scratch in tight terms.',
   'Developing functionality for editing and printing documents from web-browser.', 2, 0),
  (1, 1, 'Enterprise web-application for organization with branches in every regional city in Belarus.',
   'Long term cooperation in full-stack developer team.',
   'Developing functionality for printing indetifier card of applicants from web-browser.', 2, 0),
  (1, 2, 'System integration.', 'All projects are in production.', 'Configuring OS, installing Oracle.', 2, 0);

INSERT INTO ENVIRONMENT_SKILL (project_id, skill_id, weight)
VALUES (1, 7, 1), (1, 20, 2), (1, 25, 3), (2, 7, 1), (2, 17, 2),
  (2, 18, 3), (3, 3, 1), (3, 12, 2), (3, 21, 3), (4, 2, 1),
  (4, 5, 2), (4, 46, 3), (5, 29, 2), (5, 23, 1);