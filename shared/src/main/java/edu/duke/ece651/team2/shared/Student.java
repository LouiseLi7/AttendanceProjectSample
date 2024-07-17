package edu.duke.ece651.team2.shared;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The Student class represents a student.
 */
public class Student {
    /**
     * The student ID of the student.
     */
    Integer studentID;
    /**
     * The display name of the student.
     */
    String displayName;
    /**
     * The legal name of the student.
     */
    private String legalName;
    /**
     * The email of the student.
     */
    private String email;
    private Integer universityId;

    /**
     * Constructs a new Student object with the specified legal name, student ID,
     * email, and display name.
     *
     * @param legalName   The legal name of the student.
     * @param universityId The university ID of the student.
     * @param email       The email of the student.
     * @param displayName The display name of the student.
     */
    public Student(String legalName, String email, Integer universityId, String displayName) {
        this.legalName = legalName;
        this.email = email;
        this.universityId = universityId;
        this.displayName = displayName;
    }

    public Integer getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Integer universityId) {
        this.universityId = universityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(legalName, student.legalName) && Objects.equals(studentID, student.studentID) && Objects.equals(email, student.email) && Objects.equals(displayName, student.displayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(legalName, studentID, email, displayName);
    }

    /**
     * Get the legal name of the student.
     *
     * @return The legal name of the student.
     */
    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    /**
     * Get the student ID of the student.
     *
     * @return The student ID of the student.
     */
    public Integer getStudentID() {
        return studentID;
    }

    /**
     * Set the student ID of the student.
     *
     * @param studentID The student ID of the student.
     */
    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    /**
     * Get the email of the student.
     *
     * @return The email of the student.
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the display name of the student.
     *
     * @return The display name of the student.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Set the displayName of the student.
     *
     * @param displayName The display name of the student.
     */

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }


}
