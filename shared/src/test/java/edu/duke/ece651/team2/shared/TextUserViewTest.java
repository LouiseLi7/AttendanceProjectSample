//package edu.duke.ece651.team2.shared;
//
//import org.junit.jupiter.api.Test;
//
//import java.io.*;
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class TextUserViewTest {
//    @Test
//    void testPrintError() {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        PrintStream output = new PrintStream(bytes, true);
//
//        TextUserView view = new TextUserView(output);
//        view.printError("Error message");
//        assertEquals("Error message\n", bytes.toString());
//    }
//
//    @Test
//    void testPrintCourse() {
//        BufferedReader input = new BufferedReader(new StringReader(""));
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        PrintStream output = new PrintStream(bytes, true);
//
//        TextUserView view = new TextUserView(output);
//
//        University university = new University("Duke", true);
//        Professor prof = new Professor("John", "123", "john@example.edu", university);
//        Course course1 = new Course("C1", "Course1", prof, 0, new ArrayList<Student>(), new ArrayList<Lecture>());
//        Course course2 = new Course("C2", "Course2", prof, 0, new ArrayList<Student>(), new ArrayList<Lecture>());
//
//        prof.addCourse(course1);
//        prof.addCourse(course2);
//
//        String expected = "===========================================================================\n" +
//                "Courses Taught by John\n" +
//                "===========================================================================\n" +
//                "1. C1 - Course1\n" +
//                "2. C2 - Course2\n";
//        view.printCourses(prof);
//        assertEquals(expected, bytes.toString());
//    }
//
//    @Test
//    void testPrintStudents() {
//        BufferedReader input = new BufferedReader(new StringReader(""));
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        PrintStream output = new PrintStream(bytes, true);
//
//        University university = new University("Duke", true);
//        Professor prof = new Professor("John", "123", "john@example.edu", university);
//        Course course1 = new Course("C1", "Course1", prof, 0, new ArrayList<Student>(), new ArrayList<Lecture>());
//        Student s1 = new Student("Kenan", "kc566", "kc566@duke.edu", "kencolak");
//        Student s2 = new Student("Charlie", "cgp", "cgp@duke.edu", "charliep");
//        TextUserView view = new TextUserView(output);
//
//        prof.addCourse(course1);
//
//        course1.addStudent(s1);
//        course1.addStudent(s2);
//
//        String expected = "===========================================================================\n" +
//                "Students in Course Course1\n" +
//                "===========================================================================\n" +
//                "1. Kenan\n" +
//                "2. Charlie\n";
//        view.printStudents(course1);
//        assertEquals(expected, bytes.toString());
//    }
//
//    @Test
//    void testPrintStudentStatuses() throws IOException {
//        BufferedReader input = new BufferedReader(new StringReader(""));
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        PrintStream output = new PrintStream(bytes, true);
//
//        TextUserView view = new TextUserView(output);
//
//        University university = new University("Duke", true);
//        Professor prof = new Professor("John", "123", "john@example.edu", university);
//        ArrayList<Student> students = new ArrayList<>();
//        Student student1 = new Student("John Doe", "1", "john.doe@example.edu", "John");
//        Student student2 = new Student("Qianyi Jane", "2", "mary.jane@example.edu", "Mary");
//        students.add(student1);
//        students.add(student2);
//        ArrayList<AttendanceStatus> statuses = new ArrayList<>();
//        statuses.add(AttendanceStatus.PRESENT);
//        statuses.add(AttendanceStatus.ABSENT);
//        Lecture lecture1 = new Lecture("Course1", "L1", students, prof);
//        lecture1.recordAttendance(statuses);
//        view.printStudentStatus(lecture1);
//        assertEquals("===========================================================================\n" +
//                "Student Statuses\n" +
//                "===========================================================================\n" +
//                "John - PRESENT\n" +
//                "Mary - ABSENT\n", bytes.toString());
//    }
//
//    @Test
//    void testPrintLectures() {
//        BufferedReader input = new BufferedReader(new StringReader(""));
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        PrintStream output = new PrintStream(bytes, true);
//
//        University university = new University("Duke", true);
//        Professor prof = new Professor("John", "123", "john@example.edu", university);
//        ArrayList<Lecture> lectures = new ArrayList<>();
//        Lecture l1 = new Lecture("Course1", "C1", null, prof);
//        Lecture l2 = new Lecture("Course1", "C2", null, prof);
//        lectures.add(l1);
//        lectures.add(l2);
//        Course course1 = new Course("C1", "Course1", prof, 0, new ArrayList<Student>(), lectures);
//
//        TextUserView view = new TextUserView(output);
//
//        prof.addCourse(course1);
//
//        String expected = "===========================================================================\n" +
//                "Lectures in Course1\n" +
//                "===========================================================================\n" +
//                "1. C1\n" +
//                "2. C2\n";
//        view.printLectures(course1);
//        assertEquals(expected, bytes.toString());
//    }
//
//}
