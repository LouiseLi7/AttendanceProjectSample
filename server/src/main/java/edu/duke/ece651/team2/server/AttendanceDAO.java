package edu.duke.ece651.team2.server;

import edu.duke.ece651.team2.shared.AttendanceRecord;
import edu.duke.ece651.team2.shared.AttendanceStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AttendanceDAO extends DAO<AttendanceRecord> {

    private final DAOFactory daoFactory;

    public AttendanceDAO(DAOFactory daoFactory) {
        super();
        this.daoFactory = daoFactory;
    }

    @Override
    AttendanceRecord map(ResultSet resultSet) throws SQLException {
        return new AttendanceRecord(resultSet.getInt("studentId"),
                AttendanceStatus.valueOf(resultSet.getString("status")),
                resultSet.getInt("lectureId"));
    }

    void create(AttendanceRecord attendanceRecord) {
        List<Object> values = Arrays.asList(
                attendanceRecord.getLectureId(),
                attendanceRecord.getStudentId(),
                attendanceRecord.getStatus().toString());

        try {
            executeUpdate(daoFactory,
                    "INSERT INTO Attendance (lectureId, studentId, status) VALUES (?, ?, ?)",
                    values);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void update(AttendanceRecord attendanceRecord) {
        List<Object> values = Arrays.asList(
                attendanceRecord.getStatus().toString(),
                attendanceRecord.getLectureId(),
                attendanceRecord.getStudentId());

        try {
            executeUpdate(daoFactory,
                    "UPDATE Attendance SET status = ? WHERE lectureId = ? AND studentId = ?",
                    values);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void remove(AttendanceRecord attendanceRecord) {
        List<Object> values = Arrays.asList(
                attendanceRecord.getLectureId(),
                attendanceRecord.getStudentId());

        try {
            executeUpdate(daoFactory,
                    "DELETE FROM Attendance WHERE lectureId = ? AND studentId = ?",
                    values);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<AttendanceRecord> list() {
        return super.list(daoFactory, "SELECT * FROM Attendance ORDER BY lectureId, studentId", new ArrayList<>());
    }

    public AttendanceRecord get(Integer lectureId, Integer studentId) {
        List<Object> values = Arrays.asList(
                lectureId,
                studentId);
        return super.get(daoFactory, "SELECT * FROM Attendance WHERE lectureId = ? AND studentId = ?", values);
    }

    public List<AttendanceRecord> getAllAttendancesForLecture(Integer lectureId) {
        List<Object> values = Arrays.asList(lectureId);
        return super.list(daoFactory, "SELECT * FROM Attendance WHERE lectureId = ? ORDER BY studentId", values);
    }
}
