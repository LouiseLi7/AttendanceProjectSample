package edu.duke.ece651.team2.server;

import edu.duke.ece651.team2.shared.Lecture;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LectureDAO extends DAO<Lecture> {

    private final DAOFactory daoFactory;

    public LectureDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    Lecture map(ResultSet resultSet) throws SQLException {
        Lecture lecture = new Lecture(
                resultSet.getInt("sectionId"),
                resultSet.getDate("date").toLocalDate()
        );
        lecture.setLectureID(resultSet.getInt("id"));
        return lecture;
    }

    public void create(Lecture lecture) {
        if (lecture.getLectureID() != null) {
            throw new IllegalArgumentException("Lecture object already exists in database");
        }

        List<Object> values = Arrays.asList(
                lecture.getSectionId(),
                new Date(lecture.getYear()-1900, lecture.getMonth()-1, lecture.getDay())
        );

        try {
            ResultSet generatedKeys = executeUpdate(daoFactory,
                    "INSERT INTO Lecture (sectionId, date) VALUES (?, ?)",
                    values);
            if (generatedKeys.next()) {
                lecture.setLectureID(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Lecture lecture) {
        if (lecture.getLectureID() == null) {
            throw new IllegalArgumentException("Lecture object does not exist in database");
        }


        List<Object> values = Arrays.asList(
                lecture.getSectionId(),
                new Date(lecture.getYear()-1900, lecture.getMonth()-1, lecture.getDay()),
                lecture.getLectureID()
        );


        try {
            executeUpdate(daoFactory, "UPDATE Lecture SET sectionId = ?, date = ? WHERE id = ?", values);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Lecture lecture) {
        if (lecture.getLectureID() == null) {
            throw new IllegalArgumentException("Lecture object does not exist in database");
        }

        List<Object> values = Collections.singletonList(lecture.getLectureID());

        try {
            executeUpdate(daoFactory, "DELETE FROM Lecture WHERE id = ?", values);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        lecture.setLectureID(null);
    }

    public List<Lecture> list() {
        return super.list(daoFactory, "SELECT * FROM Lecture ORDER BY id", new ArrayList<>());
    }

    public Lecture get(Integer id) {
        List<Object> values = Collections.singletonList(id);
        return super.get(daoFactory, "SELECT * FROM Lecture WHERE id = ?", values);
    }

    public List<Lecture> getLecturesBySectionId(int sectionId) {
        List<Object> values = Collections.singletonList(sectionId);
        String sql = "SELECT * FROM Lecture WHERE sectionId = ? ORDER BY date ASC";
        return super.list(daoFactory, sql, values);
    }


    public List<Lecture> getLecturesBySectionIdDECS(int sectionId) {
        List<Object> values = Collections.singletonList(sectionId);
        String sql = "SELECT * FROM Lecture WHERE sectionId = ? ORDER BY date DeSC";
        return super.list(daoFactory, sql, values);
    }

    public void deleteAll(){
        super.deleteAll(daoFactory, "Lecture");
    }
}
