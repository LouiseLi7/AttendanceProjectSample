package edu.duke.ece651.team2.server;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import edu.duke.ece651.team2.shared.Enrollment;

public class EnrollmentDAOTest {

    DAOFactory daoFactory = new DAOFactory();

    EnrollmentDAO enrollmentDAO = new EnrollmentDAO(daoFactory);

    @Test
    public void testCreateAndList() {
        // Create a new Enrollment object
        Enrollment enrollment = new Enrollment(1, 1, true);

        // Insert the enrollment into the database
        enrollmentDAO.create(enrollment);

        // Retrieve the list of enrollments from the database
        List<Enrollment> enrollments = enrollmentDAO.list();

        // Check if the list is not null and not empty
        assertNotNull(enrollments);
        assertFalse(enrollments.isEmpty());
        enrollmentDAO.remove(enrollment);
    }


    @Test
    public void testFindEnrollmentsByStudentId() {
        // Create a DAOFactory instance (you need to replace this with your actual DAOFactory implementation)
        Enrollment enrollment = new Enrollment(1, 1, true);
        // Retrieve enrollments for a given student ID
        enrollmentDAO.create(enrollment);
        List<Enrollment> enrollments = enrollmentDAO.findEnrollmentsByStudentId(1);
        // Check if the list is not null
        assertNotNull(enrollments);
        enrollmentDAO.remove(enrollment);
    }

    @Test
    public void testCheckEnrolled() {
        Enrollment enrollment = new Enrollment(1, 1, true);
        // Retrieve enrollments for a given student ID
        enrollmentDAO.create(enrollment);

        // Check if a student is enrolled in a section
        boolean enrolled = enrollmentDAO.checkEnrolled(1, 1);

        // Check if the result is as expected
        assertTrue(enrolled); // Assuming the student is not enrolled initially
        enrollmentDAO.remove(enrollment);
    }

    @Test
    public void testCheckNotify() {
        Enrollment enrollment = new Enrollment(1, 1, true);
        // Retrieve enrollments for a given student ID
        enrollmentDAO.create(enrollment);
        // Check if a student is notified for a section
        boolean notified = enrollmentDAO.checkNotify(1, 1);

        // Check if the result is as expected
        assertTrue(notified); // Assuming the student is not notified initially
        enrollmentDAO.remove(enrollment);
    }

    @Test
    public void testUpdateNotifyBySectionIdAndStudentId() {
        Enrollment enrollment = new Enrollment(1, 1, false);
        // Retrieve enrollments for a given student ID
        enrollmentDAO.create(enrollment);
        assertFalse(enrollmentDAO.checkNotify(1, 1));
        // Update the notify status for a section and student
        enrollmentDAO.updateNotifyBySectionIdAndStudentId(1, 1);

        // Check if the notify status is updated successfully
        assertTrue(enrollmentDAO.checkNotify(1, 1)); // Assuming the notify status is updated successfully
        enrollmentDAO.remove(enrollment);
    }

}
