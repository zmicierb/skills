INSERT INTO position (id, name) VALUES (1, 'Java Developer'), (2, 'Senior System Integrator');
INSERT INTO department (id, name) VALUES (1, 'Application Development department');
INSERT INTO person (id, name, department_id, position_id, birth_date)
VALUES (1, 'Dzmitry Barysevich', 1, 1, '1987-06-20');

INSERT INTO row (id, name) VALUES (1, 'Programming Languages'),
  (2, 'Web Technologies'),
  (3, 'Application Servers'),
  (4, 'Databases'),
  (5, 'Operating Systems'),
  (6, 'Other Skills');

INSERT INTO skill (id, name) VALUES (1, 'Java 6'),
  (2, 'Java 7'), (3, 'Java 8'), (4, 'SQL'), (5, 'PL/SQL'),
  (6, 'JavaScript'), (7, 'Java EE 6'), (8, 'EJB 3.0'), (9, 'JPA/Hibernate'),
  (10, 'JSF 2.0'), (11, 'JSP'), (12, 'Spring 4.x'), (13, 'JQuery'),
  (14, 'Angular'), (15, 'Apache Velocity'), (16, 'Apache Tomcat'),
  (17, 'IBM WebSphere Application Server 8.5'), (18, 'IBM WebSphere Portal Server 8.5'),
  (19, 'Jetty'), (20, 'Liferay Enterprise'), (21, 'Oracle 11'), (22, 'Oracle 12'),
  (23, 'MySQL'), (24, 'MS SQL'), (25, 'PostgreSQL'), (26, 'Windows XP'), (27, 'Windows 7'),
  (28, 'Windows Server 2008'), (29, 'RHEL'), (30, 'CentOS'), (31, 'Mac OS'), (32, 'HP-UX'),
  (33, 'Jira'), (34, 'Maven'), (35, 'Git'), (36, 'Gulp'), (37, 'Gradle'), (38, 'Eclipse'),
  (39, 'InteliJ IDEA'), (40, 'SQL Developer'), (41, 'Toad for Oracle'), (42, 'JUnit'),
  (43, 'Sonar'), (44, 'Scrum'), (45, 'JDBC'), (46, 'Jasper Report'), (47, 'SAP BI'),
  (48, 'Crystal Report'), (49, 'XSLT'), (50, 'docx4j');

INSERT INTO skill_sum (id, person_id, skill_id, row_id, weight) VALUES (1, 1, 1, 1, 1), (2, 1, 2, 1, 2),
  (3, 1, 7, 2, 1), (4, 1, 8, 2, 2), (5, 1, 16, 3, 1),
  (6, 1, 17, 3, 2), (7, 1, 21, 4, 1), (8, 1, 23, 4, 2), (9, 1, 29, 5, 1), (10, 1, 30, 5, 2), (11, 1, 42, 6, 1),
  (12, 1, 45, 6, 2);

INSERT INTO project (id, position_id, description, result, responsibility, deleted)
VALUES (1, 1, 'Intranet web-portal for company.', 'Project in progress.', 'Developing portlet.', 0),
  (2, 1, 'Online-shop for sale valuables.', 'First phase is complete.', 'Developing catalog of items.', 0),
  (3, 1, 'Enterprise web-application for flow of documentations.', 'Working project from scratch in tight terms.',
   'Developing functionality for editing and printing documents from web-browser.', 0),
  (4, 1, 'Enterprise web-application for organization with branches in every regional city in Belarus.',
   'Long term cooperation in full-stack developer team.',
   'Developing functionality for printing indetifier card of applicants from web-browser.', 0),
  (5, 2, 'System integration.', 'All projects are in production.', 'Configuring OS, installing Oracle.', 0);

INSERT INTO company_info (id, name, start_date, end_date, position_id, deleted)
VALUES (1, 'IBA', '2016-11-01', NULL, 1, 0), (2, 'BELHARD', '2015-09-01', '2016-10-30', 1, 0),
  (3, 'BELHARD', '2010-09-01', '2015-08-30', 2, 0);

INSERT INTO project_sum (id, person_id, project_id, company_id)
VALUES (1, 1, 1, 1), (2, 1, 2, 1), (3, 1, 3, 2), (4, 1, 4, 2), (5, 1, 5, 3);

INSERT INTO environment_row (id, project_id, skill_id, weight)
VALUES (1, 1, 7, 1), (2, 1, 20, 2), (3, 1, 25, 3), (4, 2, 7, 1), (5, 2, 17, 2),
  (6, 2, 18, 3), (7, 3, 3, 1), (8, 3, 12, 2), (9, 3, 21, 3), (10, 4, 2, 1),
  (11, 4, 5, 2), (12, 4, 46, 3), (13, 5, 29, 2), (14, 5, 23, 1);