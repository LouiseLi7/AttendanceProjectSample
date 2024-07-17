package edu.duke.ece651.team2.courseManagement;

import edu.duke.ece651.team2.server.*;
import edu.duke.ece651.team2.shared.*;

import java.util.List;

public class CourseManagement implements CourseManagementInterface {
    private static final DAOFactory daoFactory = new DAOFactory();
    private static final CourseDAO courseDAO = new CourseDAO(daoFactory);
    private static final EnrollmentDAO enrollmentDAO = new EnrollmentDAO(daoFactory);
    private static final SectionDAO sectionDAO = new SectionDAO(daoFactory);
    private static final StudentDAO studentDAO = new StudentDAO(daoFactory);
    private static final ProfessorDAO professorDAO = new ProfessorDAO(daoFactory);
    private static final LectureDAO lectureDAO = new LectureDAO(daoFactory);
    private final University university;
    private final MainMenuController mainMenuController;

    public CourseManagement(University university) {
        this.university = university;
        this.mainMenuController = new MainMenuController(this);
    }

    @Override
    public MainMenuController getMainMenuController() {
        return mainMenuController;
    }

    @Override
    public University getUniversity() {
        return university;
    }

    @Override
    public List<Course> listCourses() {
        return courseDAO.listByUniversity(university.getId());
    }

    @Override
    public void addCourse(Course course) {
        courseDAO.create(course);
    }

    @Override
    public void deleteAllCourses() {
        courseDAO.deleteAll();
    }

    @Override
    public void removeCourse(Course course) {
        courseDAO.remove(course);
    }

    @Override
    public Course getCourse(Integer id) {
        return courseDAO.get(id);
    }

    @Override
    public void updateCourse(Course course) {
        courseDAO.update(course);
    }

    @Override
    public List<Section> getSections(Course course) {
        return sectionDAO.listSectionsFromCourse(course.getCourseID());
    }


    /**
     * Enrolls a student to a section
     * @param section the section to enroll a student to
     * @param student the student to enroll
     * @return true if student is successfully enrolled, false otherwise
     */
    @Override
    public boolean addStudentToSection(Section section, Student student) {
        boolean enrolled = enrollmentDAO.checkEnrolled(section.getSectionID(), student.getStudentID());
        if(!enrolled) {
            Enrollment enrollment = new Enrollment(section.getSectionID(), student.getStudentID(), true);
            enrollmentDAO.create(enrollment);
            return true;
        }
        return false;
    }

    @Override
    public void addStudentToSection(Integer studentId, Section section) {
        Enrollment enrollment = new Enrollment(section.getSectionID(), studentId, true);
        enrollmentDAO.create(enrollment);
    }

    @Override
    public Section getSection(Integer sectionId) {
        return sectionDAO.get(sectionId);
    }

    @Override
    public void updateSection(Section section) {
        sectionDAO.update(section);
    }

    @Override
    public void removeSection(Section section) {
        sectionDAO.remove(section);
    }

    @Override
    public void removeStudent(Student student) {studentDAO.remove(student);}

    @Override
    public void unenrollStudent(Student student, Section section) {
        List<Enrollment> enrollments = enrollmentDAO.findEnrollmentsByStudentId(student.getStudentID());
        for(Enrollment e : enrollments) {
            if(e.getSectionId().equals(section.getSectionID())) {
                enrollmentDAO.remove(e);
            }
        }
    }

    @Override
    public List<Student> getStudentsBySection(Section section) {return studentDAO.getStudentsBySection(section);}

    @Override
    public List<Professor> listProfessors() {
        return professorDAO.listByUniversity(university.getId());
    }

    @Override
    public Professor getProfessor(Integer professorId) {
        return professorDAO.get(professorId);
    }

    @Override
    public void addSection(Section section) {
        sectionDAO.create(section);
    }

    @Override
    public void addLecture(Lecture lecture) {
        lectureDAO.create(lecture);
    }

    @Override
    public void deleteAllSections() {
        sectionDAO.deleteAll();
    }

    @Override
    public List<Student> getAllStudentsInUniversity() { return studentDAO.getStudentsByUniversity(university); }
}
