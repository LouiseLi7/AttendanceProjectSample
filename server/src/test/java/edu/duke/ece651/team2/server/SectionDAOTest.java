package edu.duke.ece651.team2.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.duke.ece651.team2.shared.Course;
import edu.duke.ece651.team2.shared.Section;

public class SectionDAOTest {
    DAOFactory daoFactory = new DAOFactory();
    SectionDAO sectionDAO = new SectionDAO(daoFactory);
    CourseDAO courseDAO = new CourseDAO(daoFactory);

    @Test
    public void testCreateAndGet() {
        sectionDAO.deleteAll();
        // Create a new Section object
        Section section = new Section(1, 1, "Test Section");

        // Insert the section into the database
        sectionDAO.create(section);
        assertThrows(IllegalArgumentException.class, ()->sectionDAO.create(section));

        // Retrieve the section from the database using its ID
        Section retrievedSection = sectionDAO.get(section.getSectionID());

        // Check if the retrieved section is not null
        assertNotNull(retrievedSection);

        // Check if the retrieved section matches the original section
        assertEquals(section.getCourseId(), retrievedSection.getCourseId());
        assertEquals(section.getInstructorId(), retrievedSection.getInstructorId());
        assertEquals(section.getName(), retrievedSection.getName());
        sectionDAO.remove(section);
        assertEquals(0, sectionDAO.list().size());
    }

    @Test
    public void testUpdate() {
        // Create a new Section object
        Section section = new Section(1, 1, "Test Section");

        assertThrows(IllegalArgumentException.class, ()->sectionDAO.update(section));
        assertThrows(IllegalArgumentException.class, ()->sectionDAO.remove(section));
        // Insert the section into the database
        sectionDAO.create(section);

        // Update the section's name
        section.setName("Updated Section Name");

        // Update the section in the database
        sectionDAO.update(section);

        // Retrieve the updated section from the database using its ID
        Section retrievedSection = sectionDAO.get(section.getSectionID());
        // Check if the retrieved section's name matches the updated name
        assertEquals("Updated Section Name", retrievedSection.getName());
        sectionDAO.remove(section);
    }

    @Test
    public void testNoInstructorSection() {
        Course c = new Course("test1c", 1);
        // Retrieve sections with no instructor for a given courseId
        courseDAO.create(c);
        Section s = new Section(c.getCourseID(), null, "Test Section");
        sectionDAO.create(s);
        List<Section> s2 = sectionDAO.listSectionsFromCourse(c.getCourseID());
        // Check if the list is not null
        assertEquals(1, s2.size());
        courseDAO.remove(c);
        sectionDAO.remove(s);
    }

    @Test
    public void testListWithInsID() {
        // Retrieve sections with no instructor for a given courseId
        Section s = new Section(1, 1, "Test Section");
        Section s1 = new Section(1, null, "Test Section2");
        sectionDAO.create(s);
        sectionDAO.create(s1);
        List<Section> sections = sectionDAO.noInstructorSection(1);

        // Check if the list is not null
        assertEquals(1, sections.size());
        sectionDAO.remove(s);
        sectionDAO.remove(s1);
    }


    @Test
    public void testGetBySectionId() {

        Section s = new Section(1, 1, "Test Section");
        sectionDAO.create(s);

        // Retrieve a section by its section ID
        Section section = sectionDAO.getBySectionId(s.getSectionID());

        // Check if the section is not null
        assertEquals("Test Section",section.getName());
        sectionDAO.remove(section);
    }

    @Test
    public void testGetCourseAndSectionNamesByInstructorId() {
        Course c = new Course("TestC", 1);
        courseDAO.create(c);
        Section section = new Section(c.getCourseID(), 1, "Test Section");
        sectionDAO.create(section);

        Map<Integer, String> res = sectionDAO.getCourseAndSectionNamesByInstructorId(1);
        assertEquals(1, res.size());
        courseDAO.remove(c);
        sectionDAO.remove(section);
        assertEquals(0, sectionDAO.list().size());
        assertEquals(0, courseDAO.list().size());
    }

    @Test
    public void testList(){
        Section s1 = new Section(3, 2, "S1");
        Section s2 = new Section(1,null,"S2");
        Section s3 = new Section(2, 2, "S3");
        sectionDAO.create(s1);
        sectionDAO.create(s2);
        sectionDAO.create(s3);
        List<Section> ss = sectionDAO.list(2);
        assertEquals(2, ss.size());
        assertEquals("S1", ss.get(0).getName());
        assertEquals("S3", ss.get(1).getName());
        sectionDAO.deleteAll();

    }
}
