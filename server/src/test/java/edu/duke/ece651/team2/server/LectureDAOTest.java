package edu.duke.ece651.team2.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.duke.ece651.team2.shared.Lecture;

public class LectureDAOTest {

    DAOFactory daoFactory = new DAOFactory();

    // Create a LectureDAO instance
    LectureDAO lectureDAO = new LectureDAO(daoFactory);

    @Test
    public void testCreateAndGetAndUpdate() {

        // Create a new Lecture object
        LocalDate date = LocalDate.of(2024, 4, 11);
        Lecture lecture = new Lecture(1, date);

        // Insert the lecture into the database
        lectureDAO.create(lecture);
        assertThrows(IllegalArgumentException.class, ()->lectureDAO.create(lecture));

        // Retrieve the lecture from the database using its ID
        Lecture retrievedLecture = lectureDAO.get(lecture.getLectureID());

        // Check if the retrieved lecture is not null
        assertNotNull(retrievedLecture);

        lectureDAO.remove(lecture);
    }

    @Test
    public void testUpdate() {

        // Create a new Lecture object
        LocalDate date = LocalDate.of(2024, 4, 11);
        Lecture lecture = new Lecture(1, date);

        assertThrows(IllegalArgumentException.class, ()->lectureDAO.update(lecture));
        assertThrows(IllegalArgumentException.class, ()->lectureDAO.remove(lecture));
        // Insert the lecture into the database
        lectureDAO.create(lecture);

        // Update the lecture's date
        // LocalDate newDate = LocalDate.of(2024, 4, 12);
        // lecture.setDate(newDate);

        // Update the lecture in the database
        lectureDAO.update(lecture);

        // Retrieve the updated lecture from the database using its ID
        Lecture retrievedLecture = lectureDAO.get(lecture.getLectureID());

        // Check if the retrieved lecture is not null
        assertNotNull(retrievedLecture);

        List<Lecture> lecs = lectureDAO.getLecturesBySectionId(1);
        assertEquals(1, lecs.size());

        List<Lecture> lecturs = lectureDAO.getLecturesBySectionIdDECS(1);
        assertEquals(1, lecturs.size());

        lectureDAO.remove(lecture);
    }

    @Test
    public void testList(){
        LocalDate date = LocalDate.of(2024, 4, 27);
        LocalDate date2 = LocalDate.of(2024, 4, 26);
        Lecture lecture1 = new Lecture(1, date);
        Lecture lecture2 = new Lecture(2, date2);
        lectureDAO.create(lecture1);
        lectureDAO.create(lecture2);
        List<Lecture> ls = lectureDAO.list();
        assertEquals(2, ls.size());
        assertEquals(27, ls.get(0).getDay());
        assertEquals(26, ls.get(1).getDay());
        lectureDAO.deleteAll();
    }

}
