package edu.duke.ece651.team2.shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProfessorTest {

    private Professor professor;

    @BeforeEach
    public void setUp() {
        professor = new Professor("John", "john@test.com", 12345);
        professor.setProfessorID(1001);
    }

    @Test
    public void testGetName() {
        assertEquals("John", professor.getName());
    }

    @Test
    public void testGetEmail() {
        assertEquals("john@test.com", professor.getEmail());
    }

    @Test
    public void testGetProfessorID() {
        assertEquals(1001, professor.getProfessorID());
    }

    @Test
    public void testGetUniversityId() {
        assertEquals(12345, professor.getUniversityId());
    }

    @Test
    public void testSetUniversityId() {
        professor.setUniversityId(54321);
        assertEquals(54321, professor.getUniversityId());
    }

    @Test
    public void testEquals() {
        Professor anotherProfessor = new Professor("John", "john@test.com", 12345);
        anotherProfessor.setProfessorID(1001);
        assertEquals(professor, professor);
        assertEquals(professor, anotherProfessor);
    }

    @Test
    public void testNotEquals() {
        Professor anotherProfessor = new Professor("Jane", "jane@test.com", 54321);
        anotherProfessor.setProfessorID(1002);
        assertNotEquals(professor, null);
        assertNotEquals(professor, new Student("student", "123@stu.com", 54321, "student"));
        assertNotEquals(professor, anotherProfessor);
    }

    @Test
    public void testHashCode() {
        Professor sameProfessor = new Professor("John", "john@test.com", 12345);
        sameProfessor.setProfessorID(1001);
        assertEquals(professor.hashCode(), sameProfessor.hashCode());
    }

    @Test
    public void testSetName() {
        professor.setName("Jane");
        assertEquals("Jane", professor.getName());
    }

    @Test
    public void testSetEmail() {
        professor.setEmail("jane222@test.com");
        assertEquals("jane222@test.com", professor.getEmail());
    }

    @Test
    public void testSetProfessorID() {
        professor.setProfessorID(1002);
        assertEquals(1002, professor.getProfessorID());
    }
}
