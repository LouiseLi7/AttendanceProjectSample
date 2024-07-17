//package edu.duke.ece651.team2.client;
//
//import edu.duke.ece651.team2.shared.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//
//import java.io.*;
//import java.security.GeneralSecurityException;
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ClientSideController {
//    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//    private final PrintStream originalOut = System.out;
//    private ClientSideController controller;
//
//    @BeforeEach
//    public void setUp() {
//        System.setOut(new PrintStream(outContent)); // Redirect System.out to capture print statements
//    }
//
//    @Disabled
//    @Test
//    public void testReadNewStudents() throws IOException {
//        String simulatedInputs = "John Doe\nJohnD\n123456\njohndoe@example.com\n";
//        BufferedReader reader = new BufferedReader(new StringReader(simulatedInputs));
//
//        ClientSideController controller = new ClientSideController(reader, System.out);
//
//        Student student = controller.readNewStudents();
//        assertEquals("John Doe", student.getLegalName());
//        assertEquals("JohnD", student.getDisplayName());
//        assertEquals("123456", student.getStudentID());
//        assertEquals("johndoe@example.com", student.getEmail());
//        String expectedPrompts = "You are adding new Student, please provide the required info:\n" +
//                "Whats the student's legal name:\n" +
//                "Whats the student's display name:\n" +
//                "Whats the student's UID:\n" +
//                "Whats the student's E-Mail:\n";
//        assertEquals(expectedPrompts, outContent.toString());
//    }
//
//    @Disabled
//    @Test
//    public void testReadStudentStatus() throws IOException {
//        Student dummyStudent = new Student("John Doe", "123", "johndoe@example.com", "John");
//        String startInput = "p\n";
//        String endInput = "l\n";
//
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        PrintStream output = new PrintStream(bytes, true);
//
//        TextUserController controllerStart = new TextUserController(new BufferedReader(new StringReader(startInput)),
//                output);
//        System.out.println(bytes);
//        AttendanceStatus statusStart = controllerStart.readStudentStatus(dummyStudent.getDisplayName(), true);
//        assertEquals(AttendanceStatus.PRESENT, statusStart);
//
//        TextUserController controllerEnd = new TextUserController(new BufferedReader(new StringReader(endInput)),
//                System.out);
//        AttendanceStatus statusEnd = controllerEnd.readStudentStatus(dummyStudent.getDisplayName(), false);
//        assertEquals(AttendanceStatus.ABSENT, statusEnd);
//
//        assertTrue(outContent.toString()
//                .contains("You are recording students status, please type the status of the student:"));
//    }
//
//    @Disabled
//    @Test
//    public void testReadNewProfessor() throws IOException {
//        String input = "987654321\n" + "17778888aaa\n" + "Jane Doe\njane.doe@example.com\n";
//        TextUserController controller = new TextUserController(new BufferedReader(new StringReader(input)), System.out);
//        University university = new University("Duke", true);
//        ProtectedInfo info = new ProtectedInfo();
//        Professor newProfessor = controller.register(info, university);
//
//        assertNotNull(newProfessor, "New professor should not be null.");
//        assertEquals("Jane Doe", newProfessor.getName());
//        assertEquals("987654321", newProfessor.getProfessorID());
//        assertEquals("jane.doe@example.com", newProfessor.getEmail());
//
//        String outputText = outContent.toString();
//        assertTrue(outputText.contains("Hi, new Professor, this is " + university.getName() + ". What is your id?"));
//        assertTrue(outputText.contains("What is your password?"));
//        assertTrue(outputText.contains("What's your legal name:"));
//        assertTrue(outputText.contains("What's your E-Mail:"));
//    }
//
//    @Disabled
//    @Test
//    public void testLogIn() throws IOException {
//        String input = "987654321\n" + "17778888aaa\n" + "Jane Doe\njane.doe@example.com\n" + "987654321\n"
//                + "17778888aaa\n";
//        TextUserController controller = new TextUserController(new BufferedReader(new StringReader(input)), System.out);
//        University university = new University("Duke", true);
//        ProtectedInfo info = new ProtectedInfo();
//        Professor newProfessor = controller.register(info, university);
//        // ProtectedInfo login = new ProtectedInfo();
//        // login.storeProtectedInfo("987654321\n","17778888aaa\n");
//        controller.logIn(info);
//    }
//
//    @Disabled
//    @Test
//    public void testLogInFails() throws IOException {
//        String input = "987654321\n" + "17778888aaa\n" + "Jane Doe\njane.doe@example.com\n" + "987654321\n"
//                + "17778889aaa\n" + "987654321\n" + "17778888aaa\n";
//        TextUserController controller = new TextUserController(new BufferedReader(new StringReader(input)), System.out);
//        University university = new University("Duke", true);
//        ProtectedInfo info = new ProtectedInfo();
//        Professor newProfessor = controller.register(info, university);
//        controller.logIn(info);
//    }
//
//    @Disabled
//    @Test
//    public void testReadUniversity() throws IOException {
//        String input = "Duke\n" + "y\n" + "987654321\n" + "17778888aaa\n" + "Jane Doe\njane.doe@example.com\n"
//                + "987654321\n" + "17778889aaa\n" + "987654321\n" + "17778888aaa\n";
//        TextUserController controller = new TextUserController(new BufferedReader(new StringReader(input)), System.out);
//        University university = controller.readUniversity();
//        ProtectedInfo info = new ProtectedInfo();
//        Professor newProfessor = controller.register(info, university);
//        controller.logIn(info);
//    }
//
//    @Disabled
//    @Test
//    public void testReadUniversityNo() throws IOException {
//        String input = "Duke\n" + "n\n" + "987654321\n" + "17778888aaa\n" + "Jane Doe\njane.doe@example.com\n"
//                + "987654321\n" + "17778889aaa\n" + "987654321\n" + "17778888aaa\n";
//        TextUserController controller = new TextUserController(new BufferedReader(new StringReader(input)), System.out);
//        University university = controller.readUniversity();
//        ProtectedInfo info = new ProtectedInfo();
//        Professor newProfessor = controller.register(info, university);
//        controller.logIn(info);
//    }
//
//    @Disabled
//    @Test
//    public void testReadNewCourse() throws IOException {
//        String courseId = "CS101";
//        String input = "Introduction to Programming\nn\n";
//        TextUserController controller = new TextUserController(new BufferedReader(new StringReader(input)), System.out);
//
//        Professor professor;
//        ArrayList<Student> students;
//
//        University university = new University("Duke", true);
//        professor = new Professor("Prof. John", "001", "prof.john@example.com", university);
//
//        students = new ArrayList<>();
//        students.add(new Student("Student1", "1001", "student1@example.com", "Stu1"));
//        students.add(new Student("Student2", "1002", "student2@example.com", "Stu2"));
//
//        Course newCourse = controller.readNewCourse(courseId, professor);
//        assertNotNull(newCourse);
//        assertEquals(courseId, newCourse.getCourseID());
//        assertEquals("Introduction to Programming", newCourse.getName());
//        assertEquals("Prof. John", newCourse.getProfessor());
//        assertEquals(0, newCourse.numberOfStudents());
//
//        // Optional: Check if prompts are correctly printed
//        String outputText = outContent.toString();
//        assertTrue(outputText.contains("What's the course's name"));
//    }
//
//    @Disabled
//    @Test
//    public void testReadActionValidInput() throws IOException {
//        String input = "1\n";
//        TextUserController controller = new TextUserController(new BufferedReader(new StringReader(input)), System.out);
//
//        int action = controller.readAction("Choose an action:");
//        assertEquals(1, action);
//    }
//
//    @Disabled
//    @Test
//    public void testReadActionInvalidThenValidInput() throws IOException {
//        String input = "invalid\na\n2\n";
//        TextUserController controller = new TextUserController(new BufferedReader(new StringReader(input)), System.out);
//
//        int action = controller.readAction("Choose an action:");
//        assertEquals(2, action);
//    }
//
//    @Disabled
//    @Test
//    public void testKeepAddingStudent() throws IOException {
//        String input = "y\na\nb\nc\nd\ny\na\nb\nc\nd\nn\n";
//        TextUserController controller = new TextUserController(new BufferedReader(new StringReader(input)), System.out);
//        ArrayList<Student> ans = controller.keepAddingStudents();
//        assertEquals(2, ans.size());
//    }
//
//    @Disabled
//    @Test
//    void testChooseCourse() throws IOException {
//        String input = "-1\n";
//        Professor professor = new Professor("11", "22", "33", new University("Duke", true));
//        professor.addCourse(new Course("c1", "ecs", professor, new ArrayList<>()));
//        TextUserController controller = new TextUserController(new BufferedReader(new StringReader(input)), System.out);
//        assertEquals(1, controller.chooseCourse(professor));
//        String input1 = "";
//        TextUserController controller1 = new TextUserController(new BufferedReader(new StringReader(input1)),
//                System.out);
//        assertEquals(1, controller1.chooseCourse(professor));
//        String input2 = "1\n";
//        TextUserController controller2 = new TextUserController(new BufferedReader(new StringReader(input2)),
//                System.out);
//        assertEquals(0, controller2.chooseCourse(professor));
//
//    }
//
//    @Disabled
//    @Test
//    public void testReadHeaderExists() throws IOException {
//        String input = "y\n";
//        TextUserController controller1 = new TextUserController(new BufferedReader(new StringReader(input)),
//                System.out);
//        assertTrue(controller1.readHeaderExists()); // Test when user enters 'y'
//        String input2 = "n\n";
//        TextUserController controller2 = new TextUserController(new BufferedReader(new StringReader(input2)),
//                System.out);
//        assertFalse(controller2.readHeaderExists()); // Test when user enters 'n'
//        String input3 = "\n";
//        TextUserController controller3 = new TextUserController(new BufferedReader(new StringReader(input3)),
//                System.out);
//        assertFalse(controller3.readHeaderExists()); // Test when user enters 'n'
//    }
//
//    @Disabled
//    @Test
//    public void testExtractColumns() throws IOException {
//        TextUserController controller = new TextUserController(new BufferedReader(new StringReader(",")), System.out);
//        String[] exp = new String[5];
//        exp[0] = "rohini";
//        exp[1] = "rohiniR";
//        exp[2] = "rrrrr";
//        exp[3] = "23456@stu.edu";
//        exp[4] = "3.2";
//        String[] test = controller.extractColumns("rohini,rohiniR,rrrrr,23456@stu.edu,3.2", ",");
//        for (int i = 0; i < test.length; i++) {
//            assertEquals(exp[i], test[i]);
//        }
//    }
//
//    @Disabled
//    @Test
//    public void testGetNumberOfColumns() throws Exception {
//        String input = "y\n";
//        TextUserController controller = new TextUserController(new BufferedReader(new StringReader(input)), System.out);
//        // FileReader filereader = new
//        // FileReader("/Users/louiseli/Desktop/Assignments/ECE/ECE651/project/project1/app/import/students.csv");
//        // BufferedReader breader = new BufferedReader(filereader);// Provide a
//        // CSVReader instance
//        // String line;
//        // ArrayList<String> lines = new ArrayList<>();
//        // while((line = breader.readLine())!=null){
//        // lines.add(line);
//        // }
//        assertEquals(5, controller.getNumberOfColumns("rohini,rohiniR,rrrrr,23456@stu.edu,3.1", ",")); // Assuming CSV
//                                                                                                       // has 5 columns
//        assertEquals(0, controller.getNumberOfColumns("", null));
//        assertEquals(1, controller.getNumberOfColumns("1", ","));
//    }
//
//    @Disabled
//    @Test
//    public void testReadColumnNumber() throws Exception {
//        String input = "-1\n6\n0\n1\n3\n4\n2\n";
//        TextUserController controller = new TextUserController(new BufferedReader(new StringReader(input)), System.out);
//        ArrayList<Integer> ans = new ArrayList<>();
//        ans.add(controller.readColumnNumber(5, "1"));
//        ans.add(controller.readColumnNumber(5, "3"));
//        ans.add(controller.readColumnNumber(5, "4"));
//        ans.add(controller.readColumnNumber(5, "2"));
//        ArrayList<Integer> expect = new ArrayList<>();
//        expect.add(0);
//        expect.add(2);
//        expect.add(3);
//        expect.add(1);
//        assertEquals(expect, ans); // Ensure all columns are added to the list
//    }
//
//    @Disabled
//    @Test
//    public void testGetStudentsFromCSV() throws Exception {
//        String input = ",\n1\n3\n4\n2\n";
//        TextUserController controller = new TextUserController(new BufferedReader(new StringReader(input)), System.out);
//        FileReader filereader = new FileReader("import/students.csv");
//        BufferedReader breader = new BufferedReader(filereader);// Provide a CSVReader instance
//        String line;
//        breader.readLine();
//        ArrayList<String> lines = new ArrayList<>();
//        line = breader.readLine();
//        while (line != null) {
//            lines.add(line);
//            line = breader.readLine();
//        }
//        breader.close();
//        System.out.println("testReadStudents");
//        System.out.println(lines);
//        ArrayList<Student> students = controller.getStudentsFromCSV(lines);
//        ArrayList<Student> expect = new ArrayList<>();
//        Student s1 = new Student("amar", "aaaa", "12345@stu.edu", "amarA");
//        Student s2 = new Student("rohini", "rrrrr", "23456@stu.edu", "rohiniR");
//        Student s3 = new Student("aman", "34567", "34567@stu.edu", "amanA");
//        Student s4 = new Student("rahul", "rrr567", "45678@stu.edu", "rahulR");
//        Student s5 = new Student("pratik", "ppp", "56789@stu.edu", "pratikP");
//        expect.add(s1);
//        expect.add(s2);
//        expect.add(s3);
//        expect.add(s4);
//        expect.add(s5);
//        assertEquals(5, students.size()); // Ensure all columns are added to the list
//        for (int i = 0; i < 5; i++) {
//            assertEquals(expect.get(i).getDisplayName(), students.get(i).getDisplayName());
//            assertEquals(expect.get(i).getEmail(), students.get(i).getEmail());
//            assertEquals(expect.get(i).getLegalName(), students.get(i).getLegalName());
//            assertEquals(expect.get(i).getStudentID(), students.get(i).getStudentID());
//        }
//    }
//
//    @Disabled
//    @Test
//    public void testReadCSVFiles() throws Exception {
//        String input = "import/students.csv\ny\n,\n1\n3\n4\n2\n";
//        TextUserController controller = new TextUserController(new BufferedReader(new StringReader(input)), System.out);
//        FileReader filereader = new FileReader("import/students.csv");
//        BufferedReader breader = new BufferedReader(filereader);// Provide a CSVReader instance
//        String line;
//        breader.readLine();
//        ArrayList<String> lines = new ArrayList<>();
//        line = breader.readLine();
//        while (line != null) {
//            lines.add(line);
//            line = breader.readLine();
//        }
//        breader.close();
//        ArrayList<Student> students = controller.readCSVFiles();
//        ArrayList<Student> expect = new ArrayList<>();
//        Student s1 = new Student("amar", "aaaa", "12345@stu.edu", "amarA");
//        Student s2 = new Student("rohini", "rrrrr", "23456@stu.edu", "rohiniR");
//        Student s3 = new Student("aman", "34567", "34567@stu.edu", "amanA");
//        Student s4 = new Student("rahul", "rrr567", "45678@stu.edu", "rahulR");
//        Student s5 = new Student("pratik", "ppp", "56789@stu.edu", "pratikP");
//        expect.add(s1);
//        expect.add(s2);
//        expect.add(s3);
//        expect.add(s4);
//        expect.add(s5);
//        assertEquals(5, students.size()); // Ensure all columns are added to the list
//        for (int i = 0; i < 5; i++) {
//            assertEquals(expect.get(i).getDisplayName(), students.get(i).getDisplayName());
//            assertEquals(expect.get(i).getEmail(), students.get(i).getEmail());
//            assertEquals(expect.get(i).getLegalName(), students.get(i).getLegalName());
//            assertEquals(expect.get(i).getStudentID(), students.get(i).getStudentID());
//        }
//    }
//
//    @Disabled
//    @Test
//    public void testReadCSVFilesNoHeader() throws Exception {
//        String input = "import/studentsNoheader.csv\nn\n,\n1\n3\n4\n2\n";
//        TextUserController controller = new TextUserController(new BufferedReader(new StringReader(input)), System.out);
//        FileReader filereader = new FileReader("import/studentsNoheader.csv");
//        BufferedReader breader = new BufferedReader(filereader);// Provide a CSVReader instance
//        String line;
//        ArrayList<String> lines = new ArrayList<>();
//        line = breader.readLine();
//        while (line != null) {
//            lines.add(line);
//            line = breader.readLine();
//        }
//        breader.close();
//        ArrayList<Student> students = controller.readCSVFiles();
//        ArrayList<Student> expect = new ArrayList<>();
//        Student s1 = new Student("amar", "aaaa", "12345@stu.edu", "amar A");
//        Student s2 = new Student("rr", "rrrrr", "23456@stu.edu", "rohi R");
//        Student s3 = new Student("amen", "34567", "34567@stu.edu", "amen A");
//        Student s4 = new Student("rahu", "rrr567", "45678@stu.edu", "rahul R");
//        Student s5 = new Student("prati", "ppp", "56789@stu.edu", "pratik P");
//        expect.add(s1);
//        expect.add(s2);
//        expect.add(s3);
//        expect.add(s4);
//        expect.add(s5);
//        assertEquals(5, students.size()); // Ensure all columns are added to the list
//        for (int i = 0; i < 5; i++) {
//            assertEquals(expect.get(i).getDisplayName(), students.get(i).getDisplayName());
//            assertEquals(expect.get(i).getEmail(), students.get(i).getEmail());
//            assertEquals(expect.get(i).getLegalName(), students.get(i).getLegalName());
//            assertEquals(expect.get(i).getStudentID(), students.get(i).getStudentID());
//        }
//    }
//
//    @Disabled
//    @Test
//    public void testLoadStudents() {
//        String input = "n\n";
//        TextUserController controller = new TextUserController(new BufferedReader(new StringReader(input)), System.out);
//        assertEquals(0, controller.loadStudents().size());
//        String input1 = "y\nimport/studentsNoheader.csv\nn\n,\n1\n3\n4\n2\n";
//        controller = new TextUserController(new BufferedReader(new StringReader(input1)), System.out);
//        assertEquals(5, controller.loadStudents().size());
//    }
//
//    @Disabled
//    @Test
//    public void testUpdateRecordForStudent() throws IOException, GeneralSecurityException {
//        ArrayList<Student> stu = new ArrayList<>();
//        Student s = new Student("11", "22", "33", "44");
//        stu.add(s);
//        University university = new University("Duke", true);
//        Professor professor = new Professor("11", "22", "33", university);
//        Lecture l = new Lecture("!234", "1_1", stu, professor);
//        String input = "11";
//        TextUserController controller = new TextUserController(new BufferedReader(new StringReader(input)), System.out);
//        EventManager m = new EventManager();
//        controller.updateRecordForStudent(l, m);
//        String outputText = outContent.toString();
//        assertEquals("What is your Student ID?\nNo updated record. The student may not be a student in this lecture\n",
//                outputText);
//    }
//
//    @Disabled
//    @Test
//    public void testPrevious() throws IOException, GeneralSecurityException {
//        ArrayList<Student> stu = new ArrayList<>();
//        Student s = new Student("11", "22", "33", "44");
//        stu.add(s);
//        University university = new University("Duke", true);
//        Professor professor = new Professor("11", "22", "33", university);
//        Lecture l = new Lecture("!234", "1_1", stu, professor);
//        ArrayList<AttendanceStatus> sta = new ArrayList<>();
//        sta.add(AttendanceStatus.ABSENT);
//        l.recordAttendance(sta);
//        String input1 = "22";
//        TextUserController controller1 = new TextUserController(new BufferedReader(new StringReader(input1)),
//                System.out);
//        EventManager m = new EventManager();
//        controller1.updateRecordForStudent(l, m);
//        String outputText = outContent.toString();
//        assertEquals("What is your Student ID?\n" + //
//                "Successfully create and write csv\n" + //
//                "Successfully update the record for this student. An email may send to the student's email\n",
//                outputText);
//
//    }
//
//    @Disabled
//    @Test
//    public void testchangeStudentDisplayName() throws IOException {
//        ArrayList<Student> stu = new ArrayList<>();
//        Student s = new Student("11", "22", "33", "44");
//        stu.add(s);
//        University university = new University("Duke", true);
//        Professor professor = new Professor("11", "22", "33", university);
//        Course c = new Course("c1", "ecs", professor, stu);
//        professor.addCourse(c);
//        String input1 = "1\n22\n222\n";
//        TextUserController controller1 = new TextUserController(new BufferedReader(new StringReader(input1)),
//                System.out);
//        controller1.changeStudentDisplayName(professor);
//        String outputText = outContent.toString();
//        assertEquals("Please type the number in front of the target course, invalid selection will return.\n" + //
//                "What is the student's ID?\nWhat is your new preferred display name?\nSuccessfully!\n", outputText);
//        assertEquals("222", s.getDisplayName());
//        outContent.reset();
//        input1 = "1\n11\n";
//        controller1 = new TextUserController(new BufferedReader(new StringReader(input1)), System.out);
//        controller1.changeStudentDisplayName(professor);
//        outputText = outContent.toString();
//        assertEquals("Please type the number in front of the target course, invalid selection will return.\n" + //
//                "What is the student's ID?\nWhat is your new preferred display name?\n" + //
//                "The id may be wrong.\n", outputText);
//    }
//
//    @Disabled
//    @Test
//    public void testdisplayAttendanceFromCourse() throws IOException {
//        ArrayList<Student> stu = new ArrayList<>();
//        Student s1 = new Student("11", "22", "33", "44");
//        Student s2 = new Student("12", "23", "34", "45");
//        stu.add(s1);
//        stu.add(s2);
//        University university = new University("Duke", true);
//        Professor professor = new Professor("11", "22", "33", university);
//
//        Course c = new Course("c1", "ecs", professor, stu);
//        professor.addCourse(c);
//
//        String input1 = "1\n";
//        TextUserController controller1 = new TextUserController(new BufferedReader(new StringReader(input1)),
//                System.out);
//        ArrayList<AttendanceStatus> status1 = new ArrayList<>();
//        status1.add(AttendanceStatus.PRESENT);
//        status1.add(AttendanceStatus.PRESENT);
//        c.endLecture(c.startLecture(status1));
//        ArrayList<AttendanceStatus> status2 = new ArrayList<>();
//        status2.add(AttendanceStatus.ABSENT);
//        status2.add(AttendanceStatus.TARDY);
//        c.endLecture(c.startLecture(status2));
//        controller1.displayAttendanceFromCourse(professor);
//        String outputText = outContent.toString();
//        outputText.contains("Lecture 0:");
//        outputText.contains("Lecture 1:");
//    }
//
//    @Disabled
//    @Test
//    public void testremoveStudentsFromCourse() throws IOException {
//        ArrayList<Student> stu = new ArrayList<>();
//        Student s = new Student("11", "22", "33", "44");
//        stu.add(s);
//        University university = new University("Duke", true);
//        Professor professor = new Professor("11", "22", "33", university);
//        Course c = new Course("c1", "ecs", professor, stu);
//        professor.addCourse(c);
//        String input1 = "1\n22\n";
//        TextUserController controller1 = new TextUserController(new BufferedReader(new StringReader(input1)),
//                System.out);
//        controller1.removeStudentsFromCourse(professor);
//        String outputText = outContent.toString();
//        assertEquals("Please type the number in front of the target course, invalid selection will return.\n" + //
//                "What is the student's UID?\nSuccessfully!\n", outputText);
//        assertEquals(0, c.getStudents().size());
//        outContent.reset();
//        input1 = "1\n11\n";
//        controller1 = new TextUserController(new BufferedReader(new StringReader(input1)), System.out);
//        controller1.removeStudentsFromCourse(professor);
//        outputText = outContent.toString();
//        assertEquals("Please type the number in front of the target course, invalid selection will return.\n" + //
//                "What is the student's UID?\n" + //
//                "The id may be wrong.\n", outputText);
//    }
//}
