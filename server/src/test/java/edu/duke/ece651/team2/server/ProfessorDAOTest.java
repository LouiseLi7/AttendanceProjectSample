package edu.duke.ece651.team2.server;

import edu.duke.ece651.team2.shared.*;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProfessorDAOTest {
    DAOFactory factory = new DAOFactory();
    ProfessorDAO professorDAO = new ProfessorDAO(factory);

    @Test
    void testCreateRemoveUpdate() {
        Professor test = new Professor("John Smith", "test@example.com", 0);
        professorDAO.create(test);
        assertNotNull(test.getProfessorID());
        assertThrows(IllegalArgumentException.class, () -> professorDAO.create(test));

        test.setName("Mary Jane");
        professorDAO.update(test);

        professorDAO.remove(test);
        assertNull(test.getProfessorID());
        assertThrows(IllegalArgumentException.class, () -> professorDAO.remove(test));
        assertThrows(IllegalArgumentException.class, () -> professorDAO.update(test));
    }

    @Test
    void testList() {
        List<Professor> profs = professorDAO.list();
        for(Professor prof : profs) {
            System.out.println(prof.getName());
        }
    }

    @Test
    void testGet() {
        Professor prof = new Professor("John Smith", "test@example.com", 0);
        professorDAO.create(prof);
        Professor got = professorDAO.get(prof.getProfessorID());
        assertEquals(prof, got);
        professorDAO.remove(prof);
    }

    // @Test
    // void testGetUniversityID(){
    //    Integer id = professorDAO.getUniversityID(2);
    //    assertEquals(1, id);
    // }

    @Test
    void testListByUniversity(){
        professorDAO.deleteAll();
        Professor p1 = new Professor("p1", "p1@duke.edu", 1);
        Professor p2 = new Professor("p2", "p2@school.edu", 2);
        Professor p3 = new Professor("p3", "p3@duke.edu", 1);
        professorDAO.create(p1);
        professorDAO.create(p2);
        professorDAO.create(p3);
        List<Professor> ps = professorDAO.listByUniversity(1);
        assertEquals(2, ps.size());
        assertEquals("p1",ps.get(0).getName());
        assertEquals("p3",ps.get(1).getName());
        professorDAO.deleteAll();
    }

    @Test
    void testGetUniversityID(){
        Professor p1 = new Professor("p1", "p1@duke.edu", 2);
        professorDAO.create(p1);

        assertEquals(2, professorDAO.getUniversityID(p1.getProfessorID()));
        professorDAO.remove(p1);
    }
}