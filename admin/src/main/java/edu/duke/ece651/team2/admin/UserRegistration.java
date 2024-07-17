
package edu.duke.ece651.team2.admin;

import edu.duke.ece651.team2.shared.*;
import edu.duke.ece651.team2.server.*;

public class UserRegistration {
  StudentDAO studentDAO;
  private final PasswordDAO passwordDAO;
  ProfessorDAO professorDAO;
  UniversityDAO universityDAO;

  public UserRegistration() {
    DAOFactory factory = new DAOFactory();
    this.studentDAO = new StudentDAO(factory);
    this.passwordDAO = new PasswordDAO(factory);
    this.professorDAO = new ProfessorDAO(factory);
    this.universityDAO = new UniversityDAO(factory);
  }

  /**
   * Adds a student into the database and their password
   * 
   * @param student object passed to create the user in the database
   * @param passkey The password of the student
   */
  public void addStudent(Student student, String passkey) {
    studentDAO.create(student);
    Password password = new Password(student.getStudentID(), passkey, true);
    passwordDAO.create(password);
  }

  /**
   * Adds a professor into the database and their password
   * 
   * @param professor object passed to create the user in the database
   * @param passkey   The password of the professor
   */
  public void addProfessor(Professor professor, String passkey) {
    professorDAO.create(professor);
    Password password = new Password(professor.getProfessorID(), passkey, false);
    passwordDAO.create(password);
  }

  /**
   * Removes the student from the database in UserRegistration
   * 
   * @param id The id of the student who is being removed
   */
  public void removeStudent(Integer id) {
    Password password = passwordDAO.get(studentDAO.get(id).getStudentID());
    studentDAO.remove(studentDAO.get(id));
    passwordDAO.remove(password);
  }

  /**
   * Removes a professor from the database and their password
   * 
   * @param id The id of the professor who is being removed
   */
  public void removeProfessor(Integer id) {
    Password password = passwordDAO.get(professorDAO.get(id).getProfessorID());
    professorDAO.remove(professorDAO.get(id));
    passwordDAO.remove(password);
  }

  /**
   * Updates a student from the database and their password + display Name
   * 
   * @param id      The id of the student whose password will be updated
   * @param passkey the new password to update account
   * @param newDisplayName the new displayName for the student
   */
  public void updateStudent(Integer id, String passkey, String newDisplayName) {
    if (studentDAO.get(id) != null) {
      Student updateStu = studentDAO.get(id);
      updateStu.setDisplayName(newDisplayName);
      // studentDAO.get(id).setDisplayName(newDisplayName);
      studentDAO.update(updateStu);
      Password newPassword = new Password(studentDAO.get(id).getStudentID(), passkey, true);
      passwordDAO.update(newPassword);
    }
  }

  /**
   * Updates a student from the database and their password
   *
   * @param id      The id of the student whose password will be updated
   * @param passkey the new password to update account
   */
  public void updateStudentPassword(Integer id, String passkey) {
    if (studentDAO.get(id) != null) {
      studentDAO.update(studentDAO.get(id));
      Password newPassword = new Password(studentDAO.get(id).getStudentID(), passkey, true);
      passwordDAO.update(newPassword);
    }
  }

  /**
   * Updates a student from the database and their password
   *
   * @param id      The id of the student whose password will be updated
   */
  public boolean isUpdatable(Integer id) {
    return universityDAO.get(studentDAO.get(id).getUniversityId()).canChangeName();
  }

  /**
   * Returns a student from the database through their id
   * 
   * @param id The id of the student who is being returned
   */
  public Student getStudentID(Integer id) {
    return studentDAO.get(id);
  }

  /**
   * Returns a faculty member from the database through their id
   * 
   * @param id The id of the faculty member who is being returned
   */
  public Professor getProfessorID(Integer id) {
    return professorDAO.get(id);
  }

  /**
   * Updates a professor from the database and their password
   * 
   * @param id      the id of the professor whose password will be updated
   * @param passkey the new password to update account
   */
  public void updateProfessor(Integer id, String passkey) {
    professorDAO.update(professorDAO.get(id));
    Password newPassword = new Password(professorDAO.get(id).getProfessorID(), passkey, false);
    passwordDAO.update(newPassword);
  }
}
