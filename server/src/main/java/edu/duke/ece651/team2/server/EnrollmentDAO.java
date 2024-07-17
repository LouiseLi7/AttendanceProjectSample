package edu.duke.ece651.team2.server;

import edu.duke.ece651.team2.shared.Enrollment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;

public class EnrollmentDAO extends DAO<Enrollment> {
    private final DAOFactory daoFactory;

    public EnrollmentDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Enrollment map(ResultSet resultSet) throws SQLException {
        return new Enrollment(resultSet.getInt("sectionId"),
                resultSet.getInt("studentId"),
                resultSet.getBoolean("notify"));
    }

    public void create(Enrollment enrollment) {
        List<Object> values = Arrays.asList(
                enrollment.getSectionId(),
                enrollment.getStudentId(),
                enrollment.isNotify());

        try {
            executeUpdate(daoFactory,
                    "INSERT INTO Enrollment (sectionId, studentId, notify) VALUES (?, ?, ?)",
                    values);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // NO Update because enrollment is composed only of stable keys
    // If enrollment changes, should only need to remove

    public void remove(Enrollment enrollment) {
        List<Object> values = Arrays.asList(
                enrollment.getSectionId(),
                enrollment.getStudentId());

        try {
            executeUpdate(daoFactory,
                    "DELETE FROM Enrollment WHERE sectionId = ? AND studentId = ?",
                    values);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Enrollment> list() {
        return super.list(daoFactory, "SELECT * FROM Enrollment ORDER BY sectionId, studentId", new ArrayList<>());
    }

    public List<Enrollment> findEnrollmentsByStudentId(int studentId) {
        List<Enrollment> enrollments = new ArrayList<>();
        List<Object> values = Collections.singletonList(studentId);
        enrollments = super.list(daoFactory, "SELECT * FROM Enrollment WHERE studentId = ?", values);
        return enrollments;
    }

    /**
     *
     * @param sectionId
     * @param studentId
     * @return true if enrolled, false otherwise
     */
    public boolean checkEnrolled(Integer sectionId, Integer studentId) {
        List<Object> values = Arrays.asList(
                sectionId,
                studentId);
        Enrollment result = super.get(daoFactory, "SELECT * FROM Enrollment WHERE sectionId = ? AND studentId = ?",
                values);
        return result != null; // true if enrolled, false otherwise
    }

    public boolean checkNotify(Integer sectionId, Integer studentId) {
        List<Object> values = Arrays.asList(
                sectionId,
                studentId);
        Enrollment result = super.get(daoFactory,
                "SELECT * FROM Enrollment WHERE sectionId = ? AND studentId = ? AND notify = TRUE", values);
        return result != null; // true if notified, false otherwise
    }

    public void updateNotifyBySectionIdAndStudentId(int sectionId, int studentId) {
        try {
            List<Object> values = Arrays.asList(sectionId, studentId);
            Enrollment enrollment = super.get(daoFactory,
                    "SELECT * FROM Enrollment WHERE sectionId = ? AND studentId = ?", values);

            if (enrollment != null) {
                boolean newNotify = !enrollment.isNotify();
                List<Object> updateValues = Arrays.asList(newNotify, sectionId, studentId);
                executeUpdate(daoFactory, "UPDATE Enrollment SET notify = ? WHERE sectionId = ? AND studentId = ?",
                        updateValues);
            } else {
                throw new IllegalArgumentException(
                        "Enrollment not found for sectionId " + sectionId + " and studentId " + studentId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update notify for sectionId " + sectionId + " and studentId "
                    + studentId + ": " + e.getMessage(), e);
        }
    }

}
