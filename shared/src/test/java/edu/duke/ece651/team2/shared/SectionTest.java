package edu.duke.ece651.team2.shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SectionTest {

    private Section section;

    @BeforeEach
    public void setUp() {
        Section s1 = new Section();
        section = new Section(1, 2, "Section 1");
        section.setSectionID(1001);
    }

    @Test
    public void testGetCourseId() {
        assertEquals(1, section.getCourseId());
    }

    @Test
    public void testGetInstructorId() {
        assertEquals(2, section.getInstructorId());
    }

    @Test
    public void testGetName() {
        assertEquals("Section 1", section.getName());
    }

    @Test
    public void testGetSectionID() {
        assertEquals(1001, section.getSectionID());
    }

    @Test
    public void testSetCourseId() {
        section.setCourseId(3);
        assertEquals(3, section.getCourseId());
    }

    @Test
    public void testSetInstructorId() {
        section.setInstructorId(4);
        assertEquals(4, section.getInstructorId());
    }

    @Test
    public void testSetName() {
        section.setName("Section 2");
        assertEquals("Section 2", section.getName());
    }

    @Test
    public void testSetSectionID() {
        section.setSectionID(1002);
        assertEquals(1002, section.getSectionID());
    }


    @Test
    public void testSetStudents() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("s1", "s1@test.edu", 1, "stu 1"));
        section.setStudents(students);
        assertEquals(students, section.getStudents());
    }


    @Test
    public void testSetLectures() {
        ArrayList<Lecture> lectures = new ArrayList<>();
        lectures.add(new Lecture(1001));
        section.setLecture(lectures);
        assertEquals(1, section.getLectures().size());
        assertEquals(lectures, section.getLectures());
    }
}
