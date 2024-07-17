insert into University (id,name,changeName) values (1,'Duke',1);
insert into University (id,name,changeName) values (2,'UNC',1);
insert into University (id,name,changeName) values (3,'NCSU',0);

INSERT INTO Users (legalName, displayName, email, universityId, isStudent) VALUES ('Student One', 'Stu1', 'stud1@duke.edu' , 1,TRUE);
INSERT INTO Users (legalName, email, universityId, isStudent) VALUES ('Professor One', 'pro1@duke.edu', 1, FALSE);
INSERT INTO Users (legalName, displayName, email, universityId, isStudent) VALUES ('Student Two', 'Stu2', 'stud2@duke.edu' , 1,TRUE);
INSERT INTO Users (legalName, displayName, email, universityId, isStudent) VALUES ('Student Three', 'Stu3', 'stud3@duke.edu' , 1,TRUE);
INSERT INTO Users (legalName, displayName, email, universityId, isStudent) VALUES ('Student Four', 'Stu4', 'xl435@duke.edu' , 1,TRUE);
INSERT INTO Users (legalName, displayName, email, universityId, isStudent) VALUES ('Student Five', 'Stu5', 'xl435@duke.edu' , 1,TRUE);
INSERT INTO Users (legalName, email, universityId, isStudent) VALUES ('Professor Two', 'pro2@duke.edu' , 1,False);

INSERT INTO Passwords (id, password, isStudent) VALUES (1, 123456, TRUE);
INSERT INTO Passwords (id, password, isStudent) VALUES (2, 123456, FALSE);
INSERT INTO Passwords (id, password, isStudent) VALUES (3, 123456, TRUE);
INSERT INTO Passwords (id, password, isStudent) VALUES (4, 123456, TRUE);
INSERT INTO Passwords (id, password, isStudent) VALUES (5, 123456, TRUE);
INSERT INTO Passwords (id, password, isStudent) VALUES (6, 123456, TRUE);
INSERT INTO Passwords (id, password, isStudent) VALUES (7, 123456, FALSE);

INSERT INTO Enrollment (sectionId, studentId, notify) VALUES (1, 1, TRUE);
INSERT INTO Enrollment (sectionId, studentId, notify) VALUES (2, 1, FALSE);
INSERT INTO Enrollment (sectionId, studentId, notify) VALUES (1, 3, TRUE);
INSERT INTO Enrollment (sectionId, studentId, notify) VALUES (2, 3, FALSE);
INSERT INTO Enrollment (sectionId, studentId, notify) VALUES (1, 4, TRUE);
INSERT INTO Enrollment (sectionId, studentId, notify) VALUES (1, 5, TRUE);

INSERT INTO Course (name, universityId) VALUES ('Course 1', 1);
INSERT INTO Course (name, universityId) VALUES ('Course 2', 1);
INSERT INTO Course (name, universityId) VALUES ('Course 2.2', 2);

INSERT INTO Section (courseId, instructorId, name) VALUES (1, 2, 'Course1 Sec1');
INSERT INTO Section (courseId, instructorId, name) VALUES (2, 2, 'Course2 Sec1');

INSERT INTO Section (courseId, instructorId, name) VALUES (1, NULL, 'Course1 Sec2');
INSERT INTO Section (courseId, instructorId, name) VALUES (2, NULL, 'Course2 Sec2');

INSERT INTO Lecture (sectionId,date) VALUES (1,'2024-01-01');
INSERT INTO Lecture (sectionId,date) VALUES (1,'2024-01-03');
INSERT INTO Lecture (sectionId,date) VALUES (1,'2024-01-05');
INSERT INTO Lecture (sectionId,date) VALUES (2,'2024-01-03');
INSERT INTO Lecture (sectionId,date) VALUES (2,'2024-01-05');
