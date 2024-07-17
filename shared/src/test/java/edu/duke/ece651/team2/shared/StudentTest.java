package edu.duke.ece651.team2.shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class StudentTest {

    private Student student;

    @BeforeEach
    public void setUp() {
        student = new Student("Some Student", "stu1@test.com", 876, "s1");
        student.setStudentID(1001);
    }

    @Test
    public void testGetLegalName() {
        assertEquals("Some Student", student.getLegalName());
    }

    @Test
    public void testGetEmail() {
        assertEquals("stu1@test.com", student.getEmail());
    }

    @Test
    public void testGetStudentID() {
        assertEquals(1001, student.getStudentID());
    }

    @Test
    public void testGetDisplayName() {
        assertEquals("s1", student.getDisplayName());
    }

    @Test
    public void testGetUniversityId() {
        assertEquals(876, student.getUniversityId());
    }

    @Test
    public void testSetUniversityId() {
        student.setUniversityId(123456);
        assertEquals(123456, student.getUniversityId());
    }

    @Test
    public void testEquals() {
        Student anotherStudent = new Student("Some Student", "stu1@test.com", 876, "s1");
        anotherStudent.setStudentID(1001);
        assertEquals(student, student);
        assertEquals(student, anotherStudent);
    }

    @Test
    public void testNotEquals() {
        Student anotherStudent = new Student("Some Student", "stu2@test.com", 876, "s2");
        anotherStudent.setStudentID(1002);
        assertNotEquals(student, null);
        assertNotEquals(student, new Professor("Some professor", "Pro@test.com", 876));
        assertNotEquals(student, anotherStudent);
    }

    @Test
    public void testHashCode() {
        Student sameStudent = new Student("Some Student", "stu1@test.com", 876, "s1");
        sameStudent.setStudentID(1001);
        assertEquals(student.hashCode(), sameStudent.hashCode());
    }

    @Test
    public void testSetLegalName() {
        student.setLegalName("Ok");
        assertEquals("Ok", student.getLegalName());
    }

    @Test
    public void testSetEmail() {
        student.setEmail("Not okay");
        assertEquals("Not okay", student.getEmail());
    }

    @Test
    public void testSetStudentID() {
        student.setStudentID(1002);
        assertEquals(1002, student.getStudentID());
    }

    @Test
    public void testSetDisplayName() {
        student.setDisplayName("fine");
        assertEquals("fine", student.getDisplayName());
    }

}