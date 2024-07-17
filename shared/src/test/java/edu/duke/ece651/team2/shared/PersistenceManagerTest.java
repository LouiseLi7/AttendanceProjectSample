//package edu.duke.ece651.team2.shared;
//
//import java.util.Date;
//import java.util.List;
//import java.util.ArrayList;
//
//import org.junit.jupiter.api.Test;
//
//public class PersistenceManagerTest {
//    private AttendanceRecord record1;
//    private AttendanceRecord record2;
//    private Date testDate;
//    private String testStudentID;
//    private AttendanceStatus testStatus;
//    private String testLectureID;
//    String studentName;
//
//    @Test
//    public void testWriteRecordsToCSV() {
//        testStudentID = "12345";
//        studentName = "A1 B1";
//        testStatus = AttendanceStatus.PRESENT; // Assume true means PRESENT
//        testLectureID = "CS101_Lecture1";
//        System.out.println(getClass().getClassLoader());
//        record1 = new AttendanceRecord(testDate, testStudentID, studentName, testStatus, testLectureID);
//        PersistenceManager m = new PersistenceManager();
//        AttendanceSession s = new AttendanceSession();
//        s.recordAttendance(testStudentID, studentName, testStatus, testLectureID);
//        m.writeRecordsToCSV(testLectureID, s);
//    }
//
//    @Test
//    public void testWriteRecordsToJSON() {
//        testStudentID = "12345";
//        studentName = "A1 B1";
//        testStatus = AttendanceStatus.PRESENT; // Assume true means present
//        testLectureID = "CS101_Lecture1";
//        System.out.println(getClass().getClassLoader());
//        record1 = new AttendanceRecord(testDate, testStudentID, studentName, testStatus, testLectureID);
//        PersistenceManager m = new PersistenceManager();
//        AttendanceSession s = new AttendanceSession();
//        s.recordAttendance(testStudentID, studentName, testStatus, testLectureID);
//        m.writeRecordsToJSON(testLectureID, "CS101", testLectureID, s);
//        m.writeRecordsToCSV(testLectureID, s);
//    }
//
//    @Test
//    public void testGenerateWholeReport() {
//        testStudentID = "12345";
//        studentName = "A1 B1";
//        testStatus = AttendanceStatus.PRESENT; // Assume true means PRESENT
//        testLectureID = "CS101_Lecture1";
//        System.out.println(getClass().getClassLoader());
//        record1 = new AttendanceRecord(testDate, testStudentID, studentName, testStatus, testLectureID);
//        record2 = new AttendanceRecord(testDate, testStudentID, studentName, testStatus, testLectureID);
//        List<AttendanceRecord> recordList = new ArrayList<>();
//        recordList.add(record1);
//        recordList.add(record2);
//        PersistenceManager m = new PersistenceManager();
//        m.generateWholeReport(testLectureID, recordList);
//    }
//}
