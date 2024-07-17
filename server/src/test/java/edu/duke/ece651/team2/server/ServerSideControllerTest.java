package edu.duke.ece651.team2.server;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import java.net.Socket;
import java.util.*;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.security.GeneralSecurityException;

import org.apache.commons.collections4.map.HashedMap;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.services.gmail.GmailScopes;

import edu.duke.ece651.team2.shared.AttendanceRecord;
import edu.duke.ece651.team2.shared.AttendanceReport;
import edu.duke.ece651.team2.shared.AttendanceStatus;
import edu.duke.ece651.team2.shared.Course;
import edu.duke.ece651.team2.shared.Enrollment;
import edu.duke.ece651.team2.shared.GmailSetup;
import edu.duke.ece651.team2.shared.Lecture;
import edu.duke.ece651.team2.shared.Password;
import edu.duke.ece651.team2.shared.Section;
import edu.duke.ece651.team2.shared.Student;

public class ServerSideControllerTest {

    @Test
    public void testLogIn() throws IOException, GeneralSecurityException {
        PasswordDAO passwordDAO = mock(PasswordDAO.class);
        StudentDAO studentDAO = mock(StudentDAO.class);
        ProfessorDAO professorDAO = mock(ProfessorDAO.class);

        ServerSideView mockview = mock(ServerSideView.class);

        ServerSideController controller = new ServerSideController(mockview, null);
        controller.setPasswordDAO(passwordDAO);
        controller.setStudentDAO(studentDAO);
        controller.setProfessorDAO(professorDAO);

        int userId = 123;
        String password = "password";

        when(passwordDAO.get(userId)).thenReturn(null);

        String[] expected = { "0", "UserId not found!" };
        assertArrayEquals(expected, controller.validateLogin(userId, password));

        password = "correct_password";
        Password passwordEntity = new Password(userId, password, true);

        when(passwordDAO.get(userId)).thenReturn(passwordEntity);
        when(studentDAO.getUniversityID(userId)).thenReturn(12345);

        String[] expected1 = { "1", "Welcome to xxx system!" };
        assertArrayEquals(expected1, controller.validateLogin(userId, password));

        passwordEntity = new Password(userId, password, false);

        when(passwordDAO.get(userId)).thenReturn(passwordEntity);
        when(professorDAO.getUniversityID(userId)).thenReturn(12345);

        String[] expected2 = { "2", "Welcome to xxx system!" };
        assertArrayEquals(expected2, controller.validateLogin(userId, password));

        password = "wrong_password";
        String[] expected3 = { "0", "Wrong password!" };
        assertArrayEquals(expected3, controller.validateLogin(userId, password));

    }

    @Test
    public void testHandleLogin() throws ClassNotFoundException, IOException, GeneralSecurityException {
        Socket mockSocket = mock(Socket.class);
        ObjectMapper mockMapper = mock(ObjectMapper.class);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject("someSerializedData");
        objectOutputStream.flush();

        byte[] serializedData = outStream.toByteArray();
        ByteArrayInputStream inStream = new ByteArrayInputStream(serializedData);
        ObjectInputStream objectInputStream = new ObjectInputStream(inStream);

        when(mockMapper.readValue(anyString(), eq(Password.class))).thenReturn(null);

        ServerSideView mockview = mock(ServerSideView.class);
        ServerSideController controller = new ServerSideController(mockview, null);
        controller.setObjectInputStream(objectInputStream);
        controller.setObjectOutputStream(objectOutputStream);
        controller.setMapper(mockMapper);

        controller.handleLogin(mockSocket);

        Password p = new Password(1, "123", false);
        when(mockMapper.readValue(anyString(), eq(Password.class))).thenReturn(p);
        ProfessorDAO professorDAO = mock(ProfessorDAO.class);
        controller.setProfessorDAO(professorDAO);
        PasswordDAO passwordDAO = mock(PasswordDAO.class);
        controller.setPasswordDAO(passwordDAO);
        when(passwordDAO.get(anyInt())).thenReturn(p);
        controller.handleLogin(mockSocket);

    }

    @Test
    public void testSendConnectionStatus() throws IOException, GeneralSecurityException {
        Socket mockSocket = mock(Socket.class);
        ServerSideView mockview = mock(ServerSideView.class);
        ServerSideController controller = new ServerSideController(mockview, null);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
        when(mockSocket.getOutputStream()).thenReturn(objectOutputStream);
        objectOutputStream.writeObject("someSerializedData");
        objectOutputStream.flush();
        byte[] serializedData = outStream.toByteArray();
        ByteArrayInputStream inStream = new ByteArrayInputStream(serializedData);
        ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
        when(mockSocket.getInputStream()).thenReturn(objectInputStream);
        controller.setObjectOutputStream(objectOutputStream);
        controller.sendConnectionStatus(mockSocket);

    }

    @Test
    public void testGetCourseSectionList() throws IOException, GeneralSecurityException {
        List<String> sectionNames = Arrays.asList("Section A", "Section B", "Section C");
        List<String> courseNames = Arrays.asList("Course 1", "Course 2", "Course 3");

        ServerSideView mockview = mock(ServerSideView.class);
        ServerSideController controller = new ServerSideController(mockview, null);
        List<String> result = controller.getCourseSectionList(sectionNames, courseNames);

        List<String> expectedResult = Arrays.asList("Course 1 :Section A", "Course 2 :Section B",
                "Course 3 :Section C");

        assertEquals(expectedResult, result);
    }

    @Test
    public void testSendAllEnrolledSectionNames() throws IOException, GeneralSecurityException {
        ServerSideView mockview = mock(ServerSideView.class);
        ObjectMapper mockMapper = mock(ObjectMapper.class);
        ServerSideController controller = new ServerSideController(mockview, null);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject("someSerializedData");
        objectOutputStream.flush();
        byte[] serializedData = outStream.toByteArray();
        ByteArrayInputStream inStream = new ByteArrayInputStream(serializedData);
        ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
        EnrollmentDAO enrollmentDAO = mock(EnrollmentDAO.class);
        SectionDAO sectionDAO = mock(SectionDAO.class);
        CourseDAO courseDAO = mock(CourseDAO.class);
        controller.setEnrollmentDAO(enrollmentDAO);
        controller.setSectionDAO(sectionDAO);
        controller.setCourseDAO(courseDAO);
        controller.setMapper(mockMapper);
        controller.setObjectInputStream(objectInputStream);
        controller.setObjectOutputStream(objectOutputStream);
        when(enrollmentDAO.findEnrollmentsByStudentId(anyInt())).thenReturn(null);
        assertEquals(null, controller.sendAllEnrolledSectionNames(1));
        List<Enrollment> es = new ArrayList<>();
        es.add(new Enrollment(1, 1, true));
        when(enrollmentDAO.findEnrollmentsByStudentId(anyInt())).thenReturn(es);
        when(sectionDAO.getBySectionId(anyInt())).thenReturn(new Section(1, null, "s1"));
        when(courseDAO.getCourseByCourseId(anyInt())).thenReturn(new Course("c1", 1));
        when(mockMapper.writeValueAsString(anyList())).thenReturn("ok");
        assertEquals(1, controller.sendAllEnrolledSectionNames(1).size());

    }

    @Test
    public void testCheckEnrollmentStatusSubscribed() throws IOException, GeneralSecurityException {
        EnrollmentDAO enrollmentDAO = Mockito.mock(EnrollmentDAO.class);
        when(enrollmentDAO.checkNotify(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);

        Section section = new Section();
        section.setSectionID(1);
        List<Section> parseSections = new ArrayList<>();
        parseSections.add(section);

        ServerSideView mockview = mock(ServerSideView.class);
        ServerSideController controller = new ServerSideController(mockview, null);
        controller.setEnrollmentDAO(enrollmentDAO);

        String result = controller.checkEnrollmentStatus(parseSections, 1, 123);

        assertEquals("Subscribed", result);
        when(enrollmentDAO.checkNotify(Mockito.anyInt(), Mockito.anyInt())).thenReturn(false);
        result = controller.checkEnrollmentStatus(parseSections, 1, 123);

        assertEquals("Unsubscribed", result);
    }

    @Test
    public void testReceiveEmailPreferenceFromClient() throws IOException, GeneralSecurityException {
        ServerSideView mockview = mock(ServerSideView.class);
        ObjectMapper mockMapper = mock(ObjectMapper.class);
        ServerSideController controller = new ServerSideController(mockview, null);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject(1);
        objectOutputStream.flush();
        byte[] serializedData = outStream.toByteArray();
        ByteArrayInputStream inStream = new ByteArrayInputStream(serializedData);
        ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
        EnrollmentDAO enrollmentDAO = mock(EnrollmentDAO.class);
        controller.setEnrollmentDAO(enrollmentDAO);
        controller.setMapper(mockMapper);
        controller.setObjectInputStream(objectInputStream);
        controller.setObjectOutputStream(objectOutputStream);
        when(enrollmentDAO.findEnrollmentsByStudentId(anyInt())).thenReturn(null);
        assertNull(controller.sendAllEnrolledSectionNames(1));
        when(enrollmentDAO.get(Mockito.any(), Mockito.any(), Mockito.anyList())).thenReturn(null);
        when(mockMapper.writeValueAsString(anyString())).thenReturn("ok");
        controller.receiveEmailPreferenceFromClient(1, 2);
        outStream = new ByteArrayOutputStream();
        objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject(null);
        objectOutputStream.flush();
        serializedData = outStream.toByteArray();
        inStream = new ByteArrayInputStream(serializedData);
        objectInputStream = new ObjectInputStream(inStream);
        controller.setObjectInputStream(objectInputStream);
        controller.receiveEmailPreferenceFromClient(1, 2);
        outStream = new ByteArrayOutputStream();
        objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject(2);
        objectOutputStream.flush();
        serializedData = outStream.toByteArray();
        inStream = new ByteArrayInputStream(serializedData);
        objectInputStream = new ObjectInputStream(inStream);
        controller.setObjectInputStream(objectInputStream);
        controller.receiveEmailPreferenceFromClient(1, 2);
    }

    // @Test
    // public void testHandleChangeEmailPreference() throws IOException,
    // GeneralSecurityException{
    // ServerSideView mockview = mock(ServerSideView.class);
    // ObjectMapper mockMapper = mock(ObjectMapper.class);
    // EnrollmentDAO enrollmentDAO = mock(EnrollmentDAO.class);
    // ServerSideController controller = new ServerSideController(mockview);
    // ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    // ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
    // objectOutputStream.writeObject(0);
    // objectOutputStream.flush();
    // byte[] serializedData = outStream.toByteArray();
    // ByteArrayInputStream inStream = new ByteArrayInputStream(serializedData);
    // ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
    // controller.setMapper(mockMapper);
    // controller.setObjectInputStream(objectInputStream);
    // controller.setObjectOutputStream(objectOutputStream);
    // controller.setEnrollmentDAO(enrollmentDAO);
    // ServerSideController mockController = spy(controller);
    // Section section = new Section();
    // section.setSectionID(1);
    // List<Section> parseSections = new ArrayList<>();
    // parseSections.add(section);
    // section = new Section();
    // section.setSectionID(2);
    // parseSections.add(section);
    // Mockito.when(mockController.sendAllEnrolledSectionNames(anyInt())).thenReturn(parseSections);
    // when(enrollmentDAO.checkNotify(anyInt(), any())).thenReturn(true);
    // controller.handleChangeEmailPreference(0);

    // }

    @Test
    public void testgetInstructSectiongetNoFacultySection() throws IOException, GeneralSecurityException {
        SectionDAO sectionDAO = mock(SectionDAO.class);
        CourseDAO courseDAO = mock(CourseDAO.class);
        List<Course> courses = new ArrayList<>();
        Course c = new Course("c1", 0);
        c.setCourseID(0);
        courses.add(c);
        when(sectionDAO.list(anyInt())).thenReturn(null);
        when(sectionDAO.noInstructorSection(anyInt())).thenReturn(null);
        when(courseDAO.listByUniversity(anyInt())).thenReturn(courses);
        ServerSideView mockview = mock(ServerSideView.class);
        ServerSideController controller = new ServerSideController(mockview, null);
        controller.setSectionDAO(sectionDAO);
        controller.setCourseDAO(courseDAO);
        controller.getInstructSection();
        controller.getNoFacultySection();
    }

    @Test
    public void testGetChosenSectionSetFaculty() throws IOException, GeneralSecurityException, ClassNotFoundException {
        ServerSideView mockview = mock(ServerSideView.class);
        ObjectMapper mockMapper = mock(ObjectMapper.class);
        SectionDAO sectionDAO = mock(SectionDAO.class);
        ServerSideController controller = new ServerSideController(mockview, null);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject(1);
        objectOutputStream.flush();
        byte[] serializedData = outStream.toByteArray();
        ByteArrayInputStream inStream = new ByteArrayInputStream(serializedData);
        ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
        controller.setMapper(mockMapper);
        controller.setObjectInputStream(objectInputStream);
        controller.setObjectOutputStream(objectOutputStream);
        controller.setSectionDAO(sectionDAO);
        when(mockMapper.writeValueAsString(anyString())).thenReturn("ok");
        assertEquals(1, controller.getChosenSection(null));
        outStream = new ByteArrayOutputStream();
        objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject("ok");
        objectOutputStream.flush();
        serializedData = outStream.toByteArray();
        inStream = new ByteArrayInputStream(serializedData);
        objectInputStream = new ObjectInputStream(inStream);
        controller.setObjectInputStream(objectInputStream);
        assertEquals(-1, controller.getChosenSection(null));
        Mockito.doNothing().when(sectionDAO).update(Mockito.any(Section.class));
        controller.setFaculty(new Section());
    }

    @Test
    public void testReceiveUpdateAttendanceResult() throws IOException, GeneralSecurityException {
        ServerSideView mockview = mock(ServerSideView.class);
        ObjectMapper mockMapper = mock(ObjectMapper.class);
        AttendanceDAO attendanceDAO = mock(AttendanceDAO.class);
        ServerSideController controller = new ServerSideController(mockview, null);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject(1);
        objectOutputStream.flush();
        byte[] serializedData = outStream.toByteArray();
        ByteArrayInputStream inStream = new ByteArrayInputStream(serializedData);
        ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
        controller.setMapper(mockMapper);
        controller.setObjectInputStream(objectInputStream);
        controller.setObjectOutputStream(objectOutputStream);
        controller.setAttendanceDAO(attendanceDAO);
        List<Integer> res = new ArrayList<>();
        res.add(1);
        res.add(1);
        when(mockMapper.readValue(anyString(), eq(new TypeReference<List<Integer>>() {
        }))).thenReturn(res);
        when(mockMapper.writeValueAsString(anyString())).thenReturn("ok");
        Mockito.doNothing().when(attendanceDAO).update(Mockito.any(AttendanceRecord.class));
        controller.receiveUpdateAttendanceResult(0, res);
        res = new ArrayList<>();
        res.add(1);
        res.add(2);
        when(mockMapper.readValue(anyString(), eq(List.class))).thenReturn(res);
        controller.receiveUpdateAttendanceResult(0, res);
        res = new ArrayList<>();
        res.add(1);
        res.add(3);
        when(mockMapper.readValue(anyString(), eq(List.class))).thenReturn(res);
        controller.receiveUpdateAttendanceResult(0, res);
        res = new ArrayList<>();
        res.add(-1);
        res.add(0);
        when(mockMapper.readValue(anyString(), eq(List.class))).thenReturn(res);
        controller.receiveUpdateAttendanceResult(0, res);
    }

    // @Test
    // @Disabled
    // public void testReceiveReocrdAttendanceResult() throws IOException,
    // GeneralSecurityException {
    // ServerSideView mockview = mock(ServerSideView.class);
    // ObjectMapper mockMapper = mock(ObjectMapper.class);
    // AttendanceDAO attendanceDAO = mock(AttendanceDAO.class);
    // ServerSideController controller = new ServerSideController(mockview, null);
    // ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    // ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
    // objectOutputStream.writeObject(1);
    // objectOutputStream.flush();
    // byte[] serializedData = outStream.toByteArray();
    // ByteArrayInputStream inStream = new ByteArrayInputStream(serializedData);
    // ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
    // controller.setMapper(mockMapper);
    // controller.setObjectInputStream(objectInputStream);
    // controller.setObjectOutputStream(objectOutputStream);
    // controller.setAttendanceDAO(attendanceDAO);
    // List<Character> res = new ArrayList<>();
    // res.add('A');
    // res.add('P');
    // res.add('T');
    // List<Integer> sid = new ArrayList<>();
    // sid.add(1);
    // sid.add(2);
    // sid.add(3);
    // when(mockMapper.readValue(anyString(), eq(new
    // TypeReference<List<Character>>() {
    // }))).thenReturn(res);
    // when(mockMapper.writeValueAsString(anyString())).thenReturn("ok");
    // Mockito.doNothing().when(attendanceDAO).create(Mockito.any(AttendanceRecord.class));
    // Mockito.doNothing().when(attendanceDAO).update(Mockito.any(AttendanceRecord.class));
    // when(attendanceDAO.get(anyInt(), anyInt())).thenReturn(new
    // AttendanceRecord(1, AttendanceStatus.ABSENT, 0));
    // controller.receiveReocrdAttendanceResult(0, sid);
    // when(attendanceDAO.get(anyInt(), anyInt())).thenReturn(null);
    // controller.receiveReocrdAttendanceResult(0, sid);
    // res = new ArrayList<>();
    // res.add('N');
    // when(mockMapper.readValue(anyString(), eq(new
    // TypeReference<List<Character>>() {
    // }))).thenReturn(res);
    // controller.receiveReocrdAttendanceResult(0, sid);
    // }

    @Test
    public void testSendALLStudentsEnrolled() throws IOException, GeneralSecurityException {
        ServerSideView mockview = mock(ServerSideView.class);
        ObjectMapper mockMapper = mock(ObjectMapper.class);
        StudentDAO studentDAO = mock(StudentDAO.class);
        ServerSideController controller = new ServerSideController(mockview, null);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject(0);
        objectOutputStream.flush();
        byte[] serializedData = outStream.toByteArray();
        ByteArrayInputStream inStream = new ByteArrayInputStream(serializedData);
        ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
        controller.setMapper(mockMapper);
        controller.setObjectInputStream(objectInputStream);
        controller.setObjectOutputStream(objectOutputStream);
        controller.setStudentDAO(studentDAO);
        List<Integer> res = new ArrayList<>();
        res.add(1);
        when(mockMapper.writeValueAsString(anyString())).thenReturn("ok");
        when(studentDAO.getAttendanceByLectureId(anyInt(), anyInt())).thenReturn(new HashedMap<Student, String>());
        controller.sendALLStudentsEnrolled(res, 0, 0);
        outStream = new ByteArrayOutputStream();
        objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject(-1);
        objectOutputStream.flush();
        serializedData = outStream.toByteArray();
        inStream = new ByteArrayInputStream(serializedData);
        objectInputStream = new ObjectInputStream(inStream);
        controller.setObjectInputStream(objectInputStream);
        controller.setObjectOutputStream(objectOutputStream);
        controller.sendALLStudentsEnrolled(res, 0, 0);
        outStream = new ByteArrayOutputStream();
        objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject("s");
        objectOutputStream.flush();
        serializedData = outStream.toByteArray();
        inStream = new ByteArrayInputStream(serializedData);
        objectInputStream = new ObjectInputStream(inStream);
        controller.setObjectInputStream(objectInputStream);
        controller.sendALLStudentsEnrolled(res, 0, 0);
        Map<Student, String> test = new HashedMap<>();
        Student s = new Student("name", "email", 1, "name");
        s.setStudentID(1);
        test.put(s, "ok");
        outStream = new ByteArrayOutputStream();
        objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject(0);
        objectOutputStream.flush();
        serializedData = outStream.toByteArray();
        inStream = new ByteArrayInputStream(serializedData);
        objectInputStream = new ObjectInputStream(inStream);
        controller.setObjectInputStream(objectInputStream);
        when(studentDAO.getAttendanceByLectureId(anyInt(), anyInt())).thenReturn(test);
        controller.sendALLStudentsEnrolled(res, 0, 1);
        objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject(0);
        objectOutputStream.flush();
        serializedData = outStream.toByteArray();
        inStream = new ByteArrayInputStream(serializedData);
        objectInputStream = new ObjectInputStream(inStream);
        controller.setObjectInputStream(objectInputStream);
        controller.sendALLStudentsEnrolled(res, 0, 2);
    }

    @Test
    public void testWriteCSV() throws IOException, GeneralSecurityException {
        HashMap<Integer, List<AttendanceReport>> test = new HashMap<>();
        AttendanceReport r1 = new AttendanceReport(new AttendanceRecord(1, AttendanceStatus.UNRECORDED, 0), "OK Legal",
                "2024-1-1");
        List<AttendanceReport> l1 = new ArrayList<>();
        l1.add(r1);
        test.put(0, l1);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
        ServerSideView mockview = mock(ServerSideView.class);
        ServerSideController controller = new ServerSideController(mockview, null);
        controller.setObjectOutputStream(objectOutputStream);
        controller.writeRecordsToCSV(test);
    }

    @Test
    public void testSendAttendanceRecord() throws IOException, GeneralSecurityException {
        ServerSideView mockview = mock(ServerSideView.class);
        ObjectMapper mockMapper = mock(ObjectMapper.class);
        ServerSideController controller = new ServerSideController(mockview, null);
        LectureDAO lectureDAO = mock(LectureDAO.class);
        StudentDAO studentDAO = mock(StudentDAO.class);
        AttendanceDAO attendanceDAO = mock(AttendanceDAO.class);
        controller.setLectureDAO(lectureDAO);
        controller.setStudentDAO(studentDAO);
        controller.setAttendanceDAO(attendanceDAO);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject(0);
        objectOutputStream.flush();
        byte[] serializedData = outStream.toByteArray();
        ByteArrayInputStream inStream = new ByteArrayInputStream(serializedData);
        ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
        controller.setMapper(mockMapper);
        controller.setObjectInputStream(objectInputStream);
        controller.setObjectOutputStream(objectOutputStream);
        Map<Integer, String> test = new HashedMap<>();
        test.put(0, "ok");
        Map<Student, String> stest = new HashedMap<>();
        Student s = new Student("legal", "email", 1, "display");
        s.setStudentID(0);
        stest.put(s, "stu 1");
        Lecture l = new Lecture(0);
        List<Lecture> ltest = new ArrayList<>();
        ltest.add(l);
        HashSet<Integer> itest = new HashSet<>();
        itest.add(0);
        when(lectureDAO.getLecturesBySectionId(anyInt())).thenReturn(ltest);
        when(studentDAO.getStudentsBySectionID(anyInt())).thenReturn(stest);
        AttendanceRecord rcd1 = new AttendanceRecord(0, AttendanceStatus.UNRECORDED, 0);
        List<AttendanceRecord> rcdtest = new ArrayList<>();
        rcdtest.add(rcd1);
        when(lectureDAO.get(anyInt())).thenReturn(l);
        when(attendanceDAO.getAllAttendancesForLecture(anyInt())).thenReturn(rcdtest);
        when(studentDAO.get(anyInt())).thenReturn(s);
        controller.getAttendanceReportForLecture(0, itest);
        controller.sendAttendanceRecord(test);

    }

    @Test
    public void testSendEmailToClient() throws IOException, GeneralSecurityException {
        ServerSideView mockview = mock(ServerSideView.class);
        ObjectMapper mockMapper = mock(ObjectMapper.class);
        ServerSideController controller = new ServerSideController(mockview, null);
        LectureDAO lectureDAO = mock(LectureDAO.class);
        StudentDAO studentDAO = mock(StudentDAO.class);
        AttendanceDAO attendanceDAO = mock(AttendanceDAO.class);
        SectionDAO sectionDAO = mock(SectionDAO.class);
        CourseDAO courseDAO = mock(CourseDAO.class);
        GmailSetup setup = mock(GmailSetup.class);
        controller.setLectureDAO(lectureDAO);
        controller.setStudentDAO(studentDAO);
        controller.setAttendanceDAO(attendanceDAO);
        controller.setSectionDAO(sectionDAO);
        controller.setCourseDAO(courseDAO);
        controller.setGmailSetup(setup);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject(0);
        objectOutputStream.flush();
        byte[] serializedData = outStream.toByteArray();
        ByteArrayInputStream inStream = new ByteArrayInputStream(serializedData);
        ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
        controller.setMapper(mockMapper);
        controller.setObjectInputStream(objectInputStream);
        controller.setObjectOutputStream(objectOutputStream);
        Map<Integer, String> test = new HashedMap<>();
        test.put(0, "ok");
        Map<Student, String> stest = new HashedMap<>();
        Student s = new Student("legal", "email", 1, "display");
        s.setStudentID(0);
        stest.put(s, "stu 1");
        Lecture l = new Lecture(0);
        l.setLectureID(0);
        List<Lecture> ltest = new ArrayList<>();
        ltest.add(l);
        HashSet<Integer> itest = new HashSet<>();
        itest.add(0);
        Section sec = new Section(0, null, "ok");
        sec.setSectionID(0);
        Course course = new Course("course 0", 0);
        when(lectureDAO.getLecturesBySectionId(anyInt())).thenReturn(ltest);
        when(studentDAO.getStudentsBySectionID(anyInt())).thenReturn(stest);
        when(sectionDAO.getBySectionId(anyInt())).thenReturn(sec);
        when(courseDAO.getCourseByCourseId(anyInt())).thenReturn(course);
        AttendanceRecord rcd1 = new AttendanceRecord(0, AttendanceStatus.ABSENT, 0);
        when(lectureDAO.get(anyInt())).thenReturn(l);
        when(attendanceDAO.get(anyInt(), anyInt())).thenReturn(rcd1);
        when(studentDAO.get(anyInt())).thenReturn(s);
        doNothing().when(setup).sendEmail(anyString(), anyString(), anyString());
        controller.sendEmailToClient(0);
        rcd1 = new AttendanceRecord(0, AttendanceStatus.PRESENT, 0);
        when(attendanceDAO.get(anyInt(), anyInt())).thenReturn(rcd1);
        controller.sendEmailToClient(0);
        rcd1 = new AttendanceRecord(0, AttendanceStatus.TARDY, 0);
        when(attendanceDAO.get(anyInt(), anyInt())).thenReturn(rcd1);
        controller.sendEmailToClient(0);
        when(attendanceDAO.get(anyInt(), anyInt())).thenReturn(null);
        controller.sendEmailToClient(0);
        ltest = new ArrayList<>();
        when(lectureDAO.getLecturesBySectionId(anyInt())).thenReturn(ltest);
        assertThrows(IllegalStateException.class, () -> controller.sendEmailToClient(0));
    }

    @Test
    public void testSendWeeklyReport() throws IOException, GeneralSecurityException {
        ServerSideView mockview = mock(ServerSideView.class);
        ObjectMapper mockMapper = mock(ObjectMapper.class);
        ServerSideController controller = new ServerSideController(mockview, null);
        LectureDAO lectureDAO = mock(LectureDAO.class);
        StudentDAO studentDAO = mock(StudentDAO.class);
        AttendanceDAO attendanceDAO = mock(AttendanceDAO.class);
        SectionDAO sectionDAO = mock(SectionDAO.class);
        CourseDAO courseDAO = mock(CourseDAO.class);
        EnrollmentDAO enrollmentDAO = mock(EnrollmentDAO.class);
        GmailSetup setup = mock(GmailSetup.class);
        controller.setLectureDAO(lectureDAO);
        controller.setStudentDAO(studentDAO);
        controller.setAttendanceDAO(attendanceDAO);
        controller.setSectionDAO(sectionDAO);
        controller.setCourseDAO(courseDAO);
        controller.setEnrollmentDAO(enrollmentDAO);
        controller.setGmailSetup(setup);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject(0);
        objectOutputStream.flush();
        byte[] serializedData = outStream.toByteArray();
        ByteArrayInputStream inStream = new ByteArrayInputStream(serializedData);
        ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
        controller.setMapper(mockMapper);
        controller.setObjectInputStream(objectInputStream);
        controller.setObjectOutputStream(objectOutputStream);
        Student s = new Student("legal", "email", 1, "display");
        s.setStudentID(0);
        Lecture l = new Lecture(0);
        l.setLectureID(0);
        List<Lecture> ltest = new ArrayList<>();
        ltest.add(l);
        List<Enrollment> enrollments = new ArrayList<>();
        Enrollment e = new Enrollment(0, 0, true);
        enrollments.add(new Enrollment(0, 0, false));
        enrollments.add(e);
        List<Student> stus = new ArrayList<>();
        stus.add(s);
        Section sec = new Section(0, null, "ok");
        sec.setSectionID(0);
        Course course = new Course("course 0", 0);
        when(lectureDAO.getLecturesBySectionId(anyInt())).thenReturn(ltest);
        when(lectureDAO.getLecturesBySectionIdDECS(anyInt())).thenReturn(ltest);
        when(enrollmentDAO.findEnrollmentsByStudentId(anyInt())).thenReturn(enrollments);
        when(sectionDAO.getBySectionId(anyInt())).thenReturn(sec);
        when(courseDAO.getCourseByCourseId(anyInt())).thenReturn(course);
        when(studentDAO.list()).thenReturn(stus);
        AttendanceRecord rcd1 = new AttendanceRecord(0, AttendanceStatus.ABSENT, 0);
        when(lectureDAO.get(anyInt())).thenReturn(l);
        when(attendanceDAO.get(anyInt(), anyInt())).thenReturn(rcd1);
        when(studentDAO.get(anyInt())).thenReturn(s);
        doNothing().when(setup).sendEmail(anyString(), anyString(), anyString());
        controller.sendWeeklyReport();
        rcd1 = new AttendanceRecord(0, AttendanceStatus.PRESENT, 0);
        when(attendanceDAO.get(anyInt(), anyInt())).thenReturn(rcd1);
        controller.sendWeeklyReport();
        rcd1 = new AttendanceRecord(0, AttendanceStatus.TARDY, 0);
        when(attendanceDAO.get(anyInt(), anyInt())).thenReturn(rcd1);
        controller.sendWeeklyReport();
        when(attendanceDAO.get(anyInt(), anyInt())).thenReturn(null);
        controller.sendWeeklyReport();
        ltest = new ArrayList<>();
        when(lectureDAO.getLecturesBySectionIdDECS(anyInt())).thenReturn(ltest);
        controller.sendWeeklyReport();
        enrollments = new ArrayList<>();
        when(enrollmentDAO.findEnrollmentsByStudentId(anyInt())).thenReturn(enrollments);
        controller.sendWeeklyReport();
        stus = new ArrayList<>();
        when(studentDAO.list()).thenReturn(stus);
        controller.sendWeeklyReport();
    }

    @Test
    public void testgetAttendanceReport() throws IOException, GeneralSecurityException {
        ServerSideView mockview = mock(ServerSideView.class);
        ObjectMapper mockMapper = mock(ObjectMapper.class);
        ServerSideController controller = new ServerSideController(mockview, null);
        LectureDAO lectureDAO = mock(LectureDAO.class);
        StudentDAO studentDAO = mock(StudentDAO.class);
        AttendanceDAO attendanceDAO = mock(AttendanceDAO.class);
        controller.setLectureDAO(lectureDAO);
        controller.setStudentDAO(studentDAO);
        controller.setAttendanceDAO(attendanceDAO);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject(0);
        objectOutputStream.flush();
        byte[] serializedData = outStream.toByteArray();
        ByteArrayInputStream inStream = new ByteArrayInputStream(serializedData);
        ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
        controller.setMapper(mockMapper);
        controller.setObjectInputStream(objectInputStream);
        controller.setObjectOutputStream(objectOutputStream);
        Map<Integer, String> test = new HashedMap<>();
        test.put(0, "ok");
        Map<Student, String> stest = new HashedMap<>();
        Student s = new Student("legal", "email", 1, "display");
        s.setStudentID(0);
        stest.put(s, "stu 1");
        Lecture l = new Lecture(0);
        List<Lecture> ltest = new ArrayList<>();
        ltest.add(l);
        HashSet<Integer> itest = new HashSet<>();
        itest.add(1);
        itest.add(0);
        when(lectureDAO.getLecturesBySectionId(anyInt())).thenReturn(ltest);
        when(studentDAO.getStudentsBySectionID(anyInt())).thenReturn(stest);
        AttendanceRecord rcd1 = new AttendanceRecord(0, AttendanceStatus.UNRECORDED, 0);
        List<AttendanceRecord> rcdtest = new ArrayList<>();
        rcdtest.add(rcd1);
        rcdtest.add(new AttendanceRecord(2, AttendanceStatus.UNRECORDED, 0));
        when(lectureDAO.get(anyInt())).thenReturn(l);
        when(attendanceDAO.getAllAttendancesForLecture(anyInt())).thenReturn(rcdtest);
        when(studentDAO.get(anyInt())).thenReturn(s);
        controller.getAttendanceReportForLecture(0, itest);
        controller.sendAttendanceRecord(test);
    }

}
