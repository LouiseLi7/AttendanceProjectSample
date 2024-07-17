package edu.duke.ece651.team2.admin;

import edu.duke.ece651.team2.admin.UserRegistration;
import org.junit.jupiter.api.Test;
import edu.duke.ece651.team2.shared.*;
import org.junit.jupiter.api.Disabled;

import static org.junit.jupiter.api.Assertions.*;
public class UserRegistrationTest {
  
  @Test
    void testAddGetStudentAndProfessor(){
        UserRegistration userRegistration = new UserRegistration();
        Student student = new Student("Kenan Colak", "kc566@duke.edu",1,
                "kencolak");
        userRegistration.addStudent(student,"password");
        Professor professor = new Professor("Charlie Prior","CharlieP@duke.edu",1);
        userRegistration.addProfessor(professor,"passwords");
        userRegistration.getStudentID(student.getStudentID());
        userRegistration.getProfessorID(professor.getProfessorID());
        userRegistration.removeStudent(student.getStudentID());
        userRegistration.removeProfessor(professor.getProfessorID());
    }

    @Test
    void testRemoveAndUpdate(){
        UserRegistration userRegistration = new UserRegistration();
        University u = new University("u1", false);
        userRegistration.universityDAO.create(u);
        Student student = new Student("Kenan Colak", "kc566@duke.edu",u.getId(),
                "kencolak");
        userRegistration.addStudent(student,"password");
        Professor professor = new Professor("Charlie Prior","CharlieP@duke.edu",u.getId());
        userRegistration.addProfessor(professor,"passwords");
        userRegistration.updateStudent(student.getStudentID(),"newpassword", "newDisplayName");
        userRegistration.updateStudentPassword(student.getStudentID(), "newpassword1");
        userRegistration.isUpdatable(student.getStudentID());
        userRegistration.removeStudent(student.getStudentID());
        assertNull(userRegistration.studentDAO.get(student.getStudentID()));
        userRegistration.updateProfessor(professor.getProfessorID(),"newpassword2");
        userRegistration.removeProfessor(professor.getProfessorID());
        assertNull(userRegistration.professorDAO.get(professor.getProfessorID()));
        userRegistration.updateStudent(student.getStudentID(), "newpassword", "newName");
        userRegistration.updateStudentPassword(professor.getProfessorID(), "newpassword");
        userRegistration.universityDAO.remove(u);
        userRegistration.studentDAO.remove(student);
        userRegistration.professorDAO.remove(professor);
    }

}
