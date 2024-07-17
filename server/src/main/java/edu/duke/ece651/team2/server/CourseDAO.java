package edu.duke.ece651.team2.server;

import edu.duke.ece651.team2.shared.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CourseDAO extends DAO<Course> {

    private final DAOFactory daoFactory;

    public CourseDAO(DAOFactory daoFactory) {
        super();
        this.daoFactory = daoFactory;
    }

    @Override
    Course map(ResultSet resultSet) throws SQLException {
        Course course = new Course(
                resultSet.getString("name"),
                resultSet.getInt("universityId"));
        course.setCourseID(resultSet.getInt("id"));

        return course;
    }

    public void create(Course course) {
        if (course.getCourseID() != null) {
            // Object already exists in database
            throw new IllegalArgumentException("Course object already exists in database");
        }

        List<Object> values = Arrays.asList(
                course.getName(),
                course.getUniversityId());

        try {
            ResultSet generatedKeys = executeUpdate(daoFactory,
                    "INSERT INTO Course (name, universityId) VALUES (?, ?)",
                    values);
            if (generatedKeys.next()) {
                course.setCourseID(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Course course) {
        if (course.getCourseID() == null) {
            // Object does not exist in database
            throw new IllegalArgumentException("Course object does not exist in database");
        }

        List<Object> values = Arrays.asList(
                course.getName(),
                course.getUniversityId(),
                course.getCourseID());

        try {
            executeUpdate(daoFactory, "UPDATE Course SET name = ?, universityId = ? WHERE id = ?", values);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Course course) {
        if (course.getCourseID() == null) {
            // Object does not exist in database
            throw new IllegalArgumentException("Course object does not exist in database");
        }

        List<Object> values = Collections.singletonList(course.getCourseID());

        try {
            executeUpdate(daoFactory, "DELETE FROM Course WHERE id = ?", values);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        course.setCourseID(null);
    }

    public List<Course> list() {
        return super.list(daoFactory, "SELECT * FROM Course ORDER BY id", new ArrayList<>());
    }

    public Course get(Integer id) {
        List<Object> values = Collections.singletonList(id);
        return super.get(daoFactory, "SELECT * FROM Course WHERE id = ?", values);
    }

    public List<Course> listByUniversity(Integer universityId) {
        List<Object> values = Collections.singletonList(universityId);
        return super.list(daoFactory, "SELECT * FROM Course WHERE universityId = ? ORDER BY id", values);
    }

    public void deleteAll() {
        super.deleteAll(daoFactory, "Course");
    }

    public Course getCourseByCourseId(int courseId) {
        List<Object> values = Collections.singletonList(courseId);
        return super.get(daoFactory, "SELECT * FROM Course WHERE id = ?", values);

    }


}
