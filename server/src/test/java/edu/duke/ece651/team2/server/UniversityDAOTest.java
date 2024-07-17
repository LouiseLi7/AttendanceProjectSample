package edu.duke.ece651.team2.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import edu.duke.ece651.team2.shared.Password;
import edu.duke.ece651.team2.shared.University;

public class UniversityDAOTest {
    DAOFactory factory = new DAOFactory();
    UniversityDAO universityDAO = new UniversityDAO(factory);
    University u;

    @Test
    public void testCreate() {
        u = new University("testu1", false);
        universityDAO.create(u);
        assertThrows(IllegalArgumentException.class, () -> universityDAO.create(u));
        assertEquals(1, universityDAO.list().size());
        assertEquals("testu1", universityDAO.list().get(0).getName());
        universityDAO.remove(u);
        assertEquals(0, universityDAO.list().size());
    }

    @Test
    public void testupdate() {
        u = new University("testu1", false);
        assertThrows(IllegalArgumentException.class, () -> universityDAO.update(u));
        assertThrows(IllegalArgumentException.class, () -> universityDAO.remove(u));
        universityDAO.create(u);
        Integer id = u.getId();
        assertFalse(universityDAO.list().get(0).canChangeName());
        u = new University("testu2", true);
        u.setId(id);
        universityDAO.update(u);
        assertEquals("testu2", universityDAO.get(id).getName());
        assertTrue(universityDAO.list().get(0).canChangeName());
        assertEquals(1, universityDAO.list().size());
        universityDAO.deleteAll();
        assertEquals(0, universityDAO.list().size());
    }
}
