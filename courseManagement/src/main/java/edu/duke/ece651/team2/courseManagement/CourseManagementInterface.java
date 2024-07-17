package edu.duke.ece651.team2.courseManagement;

import edu.duke.ece651.team2.shared.*;

import java.util.List;

public interface CourseManagementInterface {
    MainMenuController getMainMenuController();
    University getUniversity();

    List<Course> listCourses();

    void addCourse(Course course);

    void deleteAllCourses();

    void removeCourse(Course course);

    Course getCourse(Integer id);

    void updateCourse(Course course);

    List<Section> getSections(Course course);

    boolean addStudentToSection(Section section, Student student);

    void addStudentToSection(Integer studentId, Section section);

    Section getSection(Integer sectionId);

    void updateSection(Section section);

    void removeSection(Section section);

    void removeStudent(Student student);

    void unenrollStudent(Student student, Section section);

    List<Student> getStudentsBySection(Section section);

    List<Professor> listProfessors();

    Professor getProfessor(Integer professorId);

    void addSection(Section section);

    void addLecture(Lecture lecture);

    void deleteAllSections();

    List<Student> getAllStudentsInUniversity();
}
