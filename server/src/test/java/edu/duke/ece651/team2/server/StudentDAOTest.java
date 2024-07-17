package edu.duke.ece651.team2.server;

import edu.duke.ece651.team2.shared.*;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StudentDAOTest {
     DAOFactory factory = new DAOFactory();
     StudentDAO studentDAO = new StudentDAO(factory);
     EnrollmentDAO enrollmentDAO = new EnrollmentDAO(factory);
     AttendanceDAO attendanceDAO = new AttendanceDAO(factory);

     @Test
     void testCreateRemoveUpdate() {
         Student test = new Student("John Smith", "test@example.com", 0, "John");
         studentDAO.create(test);
         assertNotNull(test.getStudentID());
         assertThrows(IllegalArgumentException.class, () -> studentDAO.create(test));

         test.setLegalName("Mary Jane");
         studentDAO.update(test);

         studentDAO.remove(test);
         assertNull(test.getStudentID());
         assertThrows(IllegalArgumentException.class, () -> studentDAO.remove(test));
         assertThrows(IllegalArgumentException.class, () -> studentDAO.update(test));
     }

     @Test
     void testList() {
         List<Student> students = studentDAO.list();
         for(Student student : students) {
             System.out.println(student.getDisplayName());
         }
     }

     @Test
     void testGet() {
         Student test = new Student("John Smith", "test@example.com", 0, "John");
         studentDAO.create(test);
         Student got = studentDAO.get(test.getStudentID());
         assertEquals(test, got);
         studentDAO.remove(got);
     }

    //  @Test
    //  void testGetUniversityID(){
    //     Integer id = studentDAO.getUniversityID(1);
    //     assertEquals(1, id);
    //  }

    @Test
    void testGetUniversityID(){
        Student s1 = new Student("s1", "s1@stu.edu", 234, "s1");
        studentDAO.create(s1);
        assertEquals(234, studentDAO.getUniversityID(s1.getStudentID()));
        studentDAO.remove(s1);
    }


    @Test
    void testGetStudentsBySectionID(){
        Student s1 = new Student("s1", "s1@stu.edu", 234, "s1");
        Student s2 = new Student("s2", "s2@stu.edu", 234, "s2");
        studentDAO.create(s1);
        studentDAO.create(s2);
        Enrollment e1 = new Enrollment(3, s1.getStudentID(), true);
        Enrollment e2 = new Enrollment(3, s2.getStudentID(), true);

        enrollmentDAO.create(e1);
        enrollmentDAO.create(e2);

        Map<Student, String> res = studentDAO.getStudentsBySectionID(3);
        assertEquals(2, res.size());
        Set<Student> ss = res.keySet();
        Set<Integer> ids = new HashSet<>();
        for(Student s:ss){
            ids.add(s.getStudentID());
            assertEquals(AttendanceStatus.UNRECORDED.toString(), res.get(s));
        }
        assertTrue(ids.contains(s1.getStudentID()));
        assertTrue(ids.contains(s2.getStudentID()));
        studentDAO.remove(s1);
        studentDAO.remove(s2);
        enrollmentDAO.remove(e1);
        enrollmentDAO.remove(e2);
    }

    @Test
    void testgetAttendanceByLectureId(){
        Student s1 = new Student("s1", "s1@stu.edu", 234, "s1");
        Student s2 = new Student("s2", "s2@stu.edu", 234, "s2");
        studentDAO.create(s1);
        studentDAO.create(s2);
        Enrollment e1 = new Enrollment(3, s1.getStudentID(), true);
        Enrollment e2 = new Enrollment(3, s2.getStudentID(), true);

        enrollmentDAO.create(e1);
        enrollmentDAO.create(e2);

        Map<Student, String> res = studentDAO.getAttendanceByLectureId(3,4);
        assertFalse(studentDAO.ifRecorded(4));
        assertEquals(2, res.size());
        Set<Student> ss = res.keySet();
        Set<Integer> ids = new HashSet<>();
        for(Student s:ss){
            ids.add(s.getStudentID());
            assertEquals(AttendanceStatus.UNRECORDED.toString(), res.get(s));
        }
        assertTrue(ids.contains(s1.getStudentID()));
        assertTrue(ids.contains(s2.getStudentID()));

        AttendanceRecord r1 = new AttendanceRecord(s1.getStudentID(), AttendanceStatus.ABSENT, 4);
        AttendanceRecord r2 = new AttendanceRecord(s2.getStudentID(), AttendanceStatus.TARDY, 4);
        attendanceDAO.create(r1);
        attendanceDAO.create(r2);
        res = studentDAO.getAttendanceByLectureId(3,4);
        assertTrue(studentDAO.ifRecorded(4));
        assertEquals(2, res.size());
        ss = res.keySet();
        ids = new HashSet<>();
        for(Student s:ss){
            ids.add(s.getStudentID());
            if(s.getStudentID().equals(r1.getStudentId())){
                assertEquals(AttendanceStatus.ABSENT.toString(),res.get(s));
            }
            else{
                assertEquals(AttendanceStatus.TARDY.toString(),res.get(s));
            }
        }
        assertTrue(ids.contains(s1.getStudentID()));
        assertTrue(ids.contains(s2.getStudentID()));

        studentDAO.remove(s1);
        studentDAO.remove(s2);
        enrollmentDAO.remove(e1);
        enrollmentDAO.remove(e2);
        attendanceDAO.remove(r1);
        attendanceDAO.remove(r2);

    }
}