package edu.duke.ece651.team2.server;

import edu.duke.ece651.team2.shared.AttendanceStatus;
import edu.duke.ece651.team2.shared.Section;
import edu.duke.ece651.team2.shared.Student;
import edu.duke.ece651.team2.shared.University;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class StudentDAO extends DAO<Student> {

    private final DAOFactory daoFactory;

    public StudentDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    Student map(ResultSet resultSet) throws SQLException {
        Student student = new Student(
                resultSet.getString("legalName"),
                resultSet.getString("email"),
                resultSet.getInt("universityId"),
                resultSet.getString("displayName"));
        student.setStudentID(resultSet.getInt("id"));
        return student;
    }

    public void create(Student student) {
        if (student.getStudentID() != null) {
            // Object already exists in database
            throw new IllegalArgumentException("Student object already exists in database");
        }

        List<Object> values = Arrays.asList(
                student.getLegalName(),
                student.getDisplayName(),
                student.getEmail(),
                student.getUniversityId());

        try {
            ResultSet generatedKeys = executeUpdate(daoFactory,
                    "INSERT INTO Users (legalName, displayName, email, universityId, isStudent) VALUES (?, ?, ?, ?, TRUE)",
                    values);
            if (generatedKeys.next()) {
                student.setStudentID(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Student student) {
        if (student.getStudentID() == null) {
            // Object does not exist in database
            throw new IllegalArgumentException("Student object does not exist in database");
        }

        List<Object> values = Arrays.asList(
                student.getLegalName(),
                student.getDisplayName(),
                student.getEmail(),
                student.getUniversityId(),
                student.getStudentID());

        try {
            executeUpdate(daoFactory,
                    "UPDATE Users SET legalName = ?, displayName = ?, email = ?, universityId = ? WHERE id = ?",
                    values);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Student student) {
        if (student.getStudentID() == null) {
            // Object does not exist in database
            throw new IllegalArgumentException("Student object does not exist in database");
        }

        List<Object> values = Collections.singletonList(student.getStudentID());

        try {
            executeUpdate(daoFactory, "DELETE FROM Users WHERE id = ?", values);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        student.setStudentID(null);
    }

    public List<Student> list() {
        return super.list(daoFactory, "SELECT * FROM Users WHERE isStudent=TRUE ORDER BY id", new ArrayList<>());
    }

    public Student get(Integer id) {
        List<Object> values = Collections.singletonList(id);
        return super.get(daoFactory, "SELECT * FROM Users WHERE id = ?", values);
    }

    public Integer getUniversityID(Integer id){
        List<Object> values = Collections.singletonList(id);
        String sql = "SELECT universityId FROM Users WHERE id = ?";
        try(ResultSet resultSet = executeQuery(daoFactory, sql, values)){
            if (resultSet.next()){
                return resultSet.getInt("universityId");
            }
            else{
                return null;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Failed to fetch attendance for UniversityID for student: " + id, e);
        }


    }

    //use for taking new record
    public Map<Student, String> getStudentsBySectionID(int sectionID) {
        Map<Student, String> attendanceMap = new HashMap<>();
        String sql = "SELECT u.id, u.legalName, u.displayName, u.email, u.universityId " +
                "FROM Enrollment e " +
                "JOIN Users u ON e.studentId = u.id " +
                "WHERE e.sectionId = ?";
        List<Object> values = Collections.singletonList(sectionID);

        try (ResultSet resultSet = executeQuery(daoFactory, sql, values)) {
            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getString("legalName"),
                        resultSet.getString("email"),
                        resultSet.getInt("universityId"),
                        resultSet.getString("displayName"));
                student.setStudentID(resultSet.getInt("id"));
                attendanceMap.put(student, AttendanceStatus.UNRECORDED.toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch attendance for sectionID: " + sectionID, e);
        }

        return attendanceMap;
    }

    public boolean ifRecorded(int lectureId){
        String sql = "SELECT * FROM Attendance WHERE lectureId = ?";
        List<Object> values = Collections.singletonList(lectureId);
        try (ResultSet resultSet = executeQuery(daoFactory, sql, values)) {
            if(resultSet.next()){
                return true;
            }
            return false;
        }
        catch (SQLException e) {
            throw new RuntimeException("Something went wrong with database, lectureId:" + lectureId, e);
        }
    }

    //use for updating old records
    public Map<Student, String> getAttendanceByLectureId(int sectionId,int lectureId) {
        Map<Student, String> attendanceMap = new HashMap<>();
        boolean recorded = ifRecorded(lectureId);
        List<Object> values;
        String sql;
        if(recorded){
            sql = "SELECT u.id, u.legalName, u.displayName, a.status, u.email, u.universityId " +
                "FROM Enrollment e " +
                "JOIN Attendance a ON e.studentId = a.studentId " +
                "JOIN Users u ON e.studentId = u.id " +
                "WHERE a.lectureId = ?";
            values = Collections.singletonList(lectureId);
        }
        else{
            sql = "SELECT u.id, u.legalName, u.displayName, u.email, u.universityId " +
                "FROM Enrollment e " +
                "JOIN Users u ON e.studentId = u.id " +
                "WHERE e.sectionId = ?";
            values = Collections.singletonList(sectionId);
        }  

        try (ResultSet resultSet = executeQuery(daoFactory, sql, values)) {
            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getString("legalName"),
                        resultSet.getString("email"),
                        resultSet.getInt("universityId"),
                        resultSet.getString("displayName"));
                student.setStudentID(resultSet.getInt("id"));
                String status;
                if(recorded){
                    status = resultSet.getString("status");
                }
                else{
                    status = AttendanceStatus.UNRECORDED.toString();
                }
                attendanceMap.put(student, status);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch attendance for lectureId: " + lectureId, e);
        }

        return attendanceMap;
    }

    public List<Student> getStudentsBySection(Section section) {
        List<Object> values = Collections.singletonList(section.getSectionID());
        return super.list(daoFactory, "SELECT u.id, u.legalName, u.displayName, u.email, u.universityId, u.isStudent " +
                "FROM Users u, Enrollment e WHERE u.isStudent=TRUE AND " +
                "u.id=e.studentId AND " +
                "e.sectionId=?", values);
    }

    public List<Student> getStudentsByUniversity(University university) {
        List<Object> values = Collections.singletonList(university.getId());
        return super.list(daoFactory, "SELECT * FROM Users WHERE isStudent=TRUE AND universityId = ?", values);
    }

}
