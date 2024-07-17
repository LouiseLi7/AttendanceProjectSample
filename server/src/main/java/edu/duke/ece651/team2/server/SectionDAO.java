package edu.duke.ece651.team2.server;

import edu.duke.ece651.team2.shared.Lecture;
import edu.duke.ece651.team2.shared.Section;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class SectionDAO extends DAO<Section> {
    private final DAOFactory daoFactory;

    public SectionDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    Section map(ResultSet resultSet) throws SQLException {
        Section section = new Section(resultSet.getInt("courseId"),
                resultSet.getInt("instructorId"),
                resultSet.getString("name"));
        section.setSectionID(resultSet.getInt("id"));
        return section;
    }

    public void create(Section section) {
        if (section.getSectionID() != null) {
            throw new IllegalArgumentException("Section object already exists in database");
        }

        List<Object> values = Arrays.asList(
                section.getCourseId(),
                section.getInstructorId(),
                section.getName());

        try {
            ResultSet generatedKeys = executeUpdate(daoFactory,
                    "INSERT INTO Section (courseId, instructorId, name) VALUES (?, ?, ?)",
                    values);
            if (generatedKeys.next()) {
                section.setSectionID(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Section section) {
        if (section.getSectionID() == null) {
            throw new IllegalArgumentException("Section object does not exist in database");
        }

        List<Object> values = Arrays.asList(
                section.getCourseId(),
                section.getInstructorId(),
                section.getName(),
                section.getSectionID());

        try {
            executeUpdate(daoFactory, "UPDATE Section SET courseId = ?, instructorId = ?, name = ? WHERE id = ?",
                    values);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Section section) {
        if (section.getSectionID() == null) {
            throw new IllegalArgumentException("Section object does not exist in database");
        }

        List<Object> values = Collections.singletonList(section.getSectionID());

        try {
            executeUpdate(daoFactory, "DELETE FROM Section WHERE id = ?", values);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        section.setSectionID(null);
    }

    public List<Section> list() {
        return super.list(daoFactory, "SELECT * FROM Section ORDER BY courseId", new ArrayList<>());
    }

    public List<Section> noInstructorSection(Integer courseId) {
        return super.list(daoFactory, "SELECT * FROM Section WHERE instructorId IS NULL AND courseId = "+courseId, new ArrayList<>());
    }
    
    public List<Section> list(Integer userID){
        List<Object> values = Collections.singletonList(userID);
        return super.list(daoFactory, "SELECT * FROM Section WHERE instructorId = ?", values);
    }

    public List<Section> listSectionsFromCourse(Integer courseID) {
        List<Object> values = Collections.singletonList(courseID);
        return super.list(daoFactory, "SELECT * FROM Section WHERE courseId = ?", values);
    }

    public Section get(Integer sectionID) {
        List<Object> values = Collections.singletonList(sectionID);
        return super.get(daoFactory, "SELECT * FROM Section WHERE id = ?", values);
    }

    // Not sure what get methods to write
    public Section getBySectionId(int sectionId) {

        List<Object> values = Collections.singletonList(sectionId);
        return super.get(daoFactory, "SELECT * FROM Section WHERE id = ?", values);

    }

    public Map<Integer, String> getCourseAndSectionNamesByInstructorId(int instructorId) {
        Map<Integer, String> namesMap = new HashMap<>();
        String sql = "SELECT s.id AS sectionId, c.name AS courseName, s.name AS sectionName " +
                "FROM Section s " +
                "JOIN Course c ON s.courseId = c.id " +
                "WHERE s.instructorId = ?";

        List<Object> values = new ArrayList<>();
        values.add(instructorId);

        try (ResultSet resultSet = executeQuery(daoFactory, sql, values)) {
            while (resultSet.next()) {
                String combinedName = resultSet.getString("courseName") + " - " +
                        resultSet.getString("sectionName") + " (Section ID: " +
                        resultSet.getInt("sectionId") + ")";
                namesMap.put(resultSet.getInt("sectionId"), combinedName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return namesMap;
    }

    public void deleteAll() {
        super.deleteAll(daoFactory, "Section");
    }
}
