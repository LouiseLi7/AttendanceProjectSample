package edu.duke.ece651.team2.shared;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * An EventListener that sends email alerts when the attendance of a Student
 * changes.
 */
public class EmailAlertsListener implements EventListener {

    private final GmailSetup gmailSetup;

    /**
     * Creates a new EmailAlertsListener.
     *
     * @throws GeneralSecurityException If there is a security error.
     * @throws IOException              If there is an I/O error.
     */
    public EmailAlertsListener() throws GeneralSecurityException, IOException {
        this.gmailSetup = new GmailSetup();
    }

    /**
     * Sends an email alert when the attendance of a Student changes.
     *
     * @param student The student whose attendance has changed.
     * @param record  The attendance record for the student.
     * @throws GeneralSecurityException If there is a security error.
     * @throws IOException              If there is an I/O error.
     */
    @Override
    public void attendanceChanged(Student student, AttendanceRecord record) {
//            throws GeneralSecurityException, IOException {
//        String email = student.getEmail();
//        System.out.println("Sending email alert to " + email + "...");
//        gmailSetup.sendEmail(email, "Attendance Changed for Lecture " + record.getLectureID(),
//                "Dear " + student.getDisplayName() + ",\n" +
//                        "Your attendance has been changed to " + record.getStatus() + " in Lecture "
//                        + record.getLectureID());
    }
}
