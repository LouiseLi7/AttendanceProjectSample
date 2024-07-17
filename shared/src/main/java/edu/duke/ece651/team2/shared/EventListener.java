package edu.duke.ece651.team2.shared;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * The EventListener interface is implemented by classes that wish to be
 * notified when the attendance of a Student changes.
 */
public interface EventListener {

    /**
     * Sends an alert when the attendance of a Student changes.
     *
     * @param student The student whose attendance has changed.
     * @param record  The attendance record for the student.
     * @throws GeneralSecurityException If there is a security error.
     * @throws IOException              If there is an I/O error.
     */
    void attendanceChanged(Student student, AttendanceRecord record) throws GeneralSecurityException, IOException;
}
