drop table if exists javabase.Users;
create table javabase.Users
(
    id          int NOT NULL auto_increment,
    legalName   VARCHAR(100)    NOT NULL,
    displayName VARCHAR(100) NULL,
    email       VARCHAR(100)    NOT NULL,
    universityId int not null,
    isStudent bool not null,
    PRIMARY KEY (id)
);

drop table if exists javabase.Student;
# create table javabase.Student
# (
#     id          int NOT NULL auto_increment,
#     legalName   VARCHAR(100)    NOT NULL,
#     displayName VARCHAR(100)    NOT NULL,
#     email       VARCHAR(100)    NOT NULL,
#     universityId int not null,
#     PRIMARY KEY (id)
# ) auto_increment=1;
#
drop table if exists javabase.Professor;
# create table javabase.Professor
# (
#     id   int NOT NULL auto_increment,
#     name  VARCHAR(100)    NOT NULL,
#     email VARCHAR(100)    NOT NULL,
#     universityId int not null,
#     PRIMARY KEY (id)
# ) auto_increment=2;

drop table if exists javabase.Course;
create table javabase.Course
(
    id          int NOT NULL auto_increment,
    name         varchar(100)    not null,
    universityId int NOT NULL,
    primary key (id)
);

drop table if exists javabase.Section;
create table javabase.Section
(
    id           int NOT NULL auto_increment,
    courseId     int NOT NULL,
    instructorId int,
    name varchar(100) not null,
    primary key (id)
);

drop table if exists javabase.Lecture;
create table javabase.Lecture
(
    id        int NOT NULL auto_increment,
    sectionId int NOT NULL,
    date      date            not null,
    primary key (id)
);

drop table if exists javabase.Enrollment;
create table javabase.Enrollment
(
    sectionId int NOT NULL,
    studentId int NOT NULL,
    notify bool not null,
    primary key (sectionId, studentId)
);

drop table if exists javabase.Attendance;
create table javabase.Attendance
(
    lectureId int NOT NULL,
    studentId int NOT NULL,
    status    varchar(10)     not null,
    primary key (lectureId, studentId)
);

drop table if exists javabase.Passwords;
create table javabase.Passwords
(
    id int NOT NULL,
    password  varchar(100)    not null,
    isStudent bool not null,
    primary key (id)
);

drop table if exists javabase.University;
create table javabase.University
(
    id         int NOT NULL auto_increment,
    name       varchar(100)    not null,
    changeName bool            not null,
    primary key (id)
);