CREATE TABLE internship.skills(
skill_id int NOT NULL auto_increment,
skill_name varchar(50) DEFAULT NULL,
skill_type varchar(50) DEFAULT NULL,
PRIMARY KEY (skill_id)
);

--ALTER TABLE internship.skills AUTO_INCREMENT=1;

insert into internship.skills(skill_name, skill_type)
values
('Java', 'Backend'),
('JavaScript', 'Frontend'),
('SQL', 'Backend'),
('Spring', 'Backend'),
('CSS', 'Frontend'),
('Pascal', 'Backend'),
('Agile', 'QA'),
('Python', 'Backend');

select * from skills;

CREATE TABLE internship.projects (
project_id int NOT NULL AUTO_INCREMENT,
project_name varchar(50) DEFAULT NULL,
PRIMARY KEY (project_id)
);

insert into internship.projects(project_name)
values
('PUMA'),
('TARGETING'),
('MICROSCOPE'),
('HEROES5'),
('ALPHA'),
('TESTING');

select * from projects;

CREATE TABLE internship.mentors (
mentor_id int NOT NULL AUTO_INCREMENT,
mentor_firstname varchar(50) DEFAULT NULL,
mentor_lastname varchar(50) DEFAULT NULL,
mentor_age int DEFAULT NULL,
mentor_skill int,
PRIMARY KEY (mentor_id),
CONSTRAINT fk_mentors_skill_id
FOREIGN KEY (mentor_skill) REFERENCES internship.skills(skill_id)
);

insert into internship.mentors (mentor_firstname, mentor_lastname, mentor_age, mentor_skill)
values
('Sergei', 'Petrenko', 32, 1),
('Aleksei', 'Prokofiev', 44, 4),
('Mikhail', 'Ivanov', 39, 2),
('Anna', 'Prokopenko', 34, 7),
('Artem',  'Galustyan', 41, 8),
('Petr', 'Sergeev', 28, 1),
('Natalia', 'Kolteva', 39, 2),
('Vladimir', 'Monomach', 51, 5),
('Ivan', 'Semenovich', 35, 1);

select * from mentors;

CREATE TABLE internship.students (
student_id int NOT NULL AUTO_INCREMENT,
student_firstname varchar(50) DEFAULT NULL,
student_lastname varchar(50) DEFAULT NULL,
student_age int DEFAULT NULL,
student_skill int,
student_mentor int,
student_project int,
PRIMARY KEY (student_id),
CONSTRAINT fk_students_skill_id
FOREIGN KEY (student_skill) REFERENCES internship.skills(skill_id),
CONSTRAINT fk_students_mentor_id
FOREIGN KEY (student_mentor) REFERENCES internship.mentors(mentor_id),
CONSTRAINT fk_students_project_id
FOREIGN KEY (student_project) REFERENCES internship.projects(project_id)
);

insert into internship.students (student_firstname, student_lastname, student_age, student_skill, student_mentor, student_project)
values
('Bogdan', 'Efimov', 24, 4, 9, 1),
('Maria', 'Lesenova', 44, 7, 8, 2),
('Vladimir', 'Kotov', 22, 2, 5, 4),
('Mikhail', 'Perkin', 24, 1, 1, 6),
('Natalia', 'Svetlova', 30, 3, 1, 2),
('Aleksei', 'Svetlakov', 30, 4, 1, 2),
('Egor', 'Ivanov', 30, 1, 2, 2),
('Andrei', 'Korkin', 19, 2, 4, 2),
('Mikhail', 'Setenov', 38, 2, 3, 1),
('Pavel', 'Transuzov', 32, 2, 4, 4),
('Anna', 'Pudyakova', 22, 3, 4, 4),
('Daria', 'Partuzova', 19, 4, 6, 5),
('Artem', 'Kalotin', 21, 3, 8, 6),
('Pavel', 'Tyirsin', 34, 5, 7, 6),
('Artem', 'Zapashniy', 49, 7, 2, 1),
('Victoria', 'Chertova', 18, 1, 3, 2),
('Anton', 'Shirokov', 25, 8, 3, 4),
('Maksim', 'Buznikin', 32, 8, 4, 5),
('Egor', 'Karandashov', 24, 1, 2, 2),
('Elena', 'Sergeeva', 25, 8, 3, 4),
('Feodora', 'Gordeeva', 18, 7, 5, 5),
('Sergei', 'Penkin', 23, 3, 5, 3),
('Maksim', 'Sokolov', 29, 1, 6, 5),
('Aleksandra', 'Bukhteeva', 23, 6, 7, 4);

select * from students;

CREATE TABLE internship.info (
info_id int NOT NULL AUTO_INCREMENT,
info_student int,
info_from timestamp DEFAULT CURRENT_TIMESTAMP,
tasks_done int DEFAULT NULL,
PRIMARY KEY (info_id),
CONSTRAINT fk_info_student_id
FOREIGN KEY (info_student) REFERENCES internship.students(student_id)
);

insert into internship.info (info_student, info_from, tasks_done)
values
(1, '2021-03-04', 10),
(2, '2021-03-05', 10),
(3, '2021-01-15', 12),
(4, '2021-04-03', 8),
(5, '2021-02-12', 6),
(6, '2021-02-18', 14),
(7, '2021-01-13', 3),
(8, '2021-02-11', 2),
(9, '2021-03-10', 11),
(10, '2021-03-14', 7),
(11, '2021-05-04', 6),
(12, '2021-05-09', 7),
(13, '2021-03-10', 9),
(14, '2021-06-01', 4),
(15, '2021-05-25', 5),
(16, '2021-05-13', 5),
(17, '2021-04-16', 7),
(18, '2021-03-23', 9),
(19, '2021-06-22', 5),
(20, '2021-07-21', 4),
(21, '2021-06-14', 4),
(22, '2021-02-06', 12),
(23, '2021-01-07', 18),
(24, '2021-01-09', 12);

select * from students
join mentors on students.student_mentor = mentors.mentor_id
join skills on students.student_skill = skills.skill_id
join projects on students.student_project = projects.project_id
join info on students.student_id = info.info_student
;
