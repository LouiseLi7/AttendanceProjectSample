package edu.duke.ece651.team2.admin;

import edu.duke.ece651.team2.shared.*;
import edu.duke.ece651.team2.server.*;
import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Objects;
import java.util.List;
public class UserRegistrationView {
    final PrintStream out;
    UniversityDAO universityDAO;
    private final BufferedReader reader;
    private final UserRegistration userRegistration;

    /**
     * Constructs a new UserRegistrationView object
     * 
     * @param out              The PrintStream used to write the output
     * @param userRegistration the userRegistration class initialization
     * @param reader           initialization for the reader taking for user input
     */
    public UserRegistrationView(PrintStream out, UserRegistration userRegistration,
            BufferedReader reader) {
        this.userRegistration = userRegistration;
        this.out = out;
        this.reader = reader;
        DAOFactory factory = new DAOFactory();
        this.universityDAO = new UniversityDAO(factory);
    }
  
    /**
     * Prints the specified prompt to the user.
     *
     * @param prompt The prompt to print to the user.
     */
    public void print(String prompt) {
        out.println(prompt);
    }

    protected String printPromptAndRead(String prompt) throws IOException {
        out.println(prompt);
        return reader.readLine();
    }

    /**
     * Method to list the universities in our database for selection
     * @return the amount of universities as options
     */
    public int listUniversities(){
        List<University> universities = universityDAO.list();
        for (University u : universities) {
             String str = u.getId() + ". " +
                     u.getName();
            out.println(str);
        }
        return universities.size();
    }

    /**
     * Method to list the universities in our database for selection
     * @return the universities as options
     */
    public List<University> listUniversitiesController(){
        List<University> universities = universityDAO.list();
        return universities;
    }

    public int addProfessorView() throws IOException {
        // add it to table password, get UniversityID from University
        String prompt = "What's your legal name:";
        String name = printPromptAndRead(prompt);
        prompt = "What's your E-Mail:";
        String email = printPromptAndRead(prompt);
        prompt = "What's Your University?";
        print(prompt);
        List<University> universities = universityDAO.list();
        listUniversities();
        prompt = "Please choose from above from the list above";
        String id = printPromptAndRead(prompt);
        University u = universities.get(Integer.valueOf(id)-1);
        Integer uniID = u.getId();
        prompt = "What would you like to set as your password?";
        String passkey = printPromptAndRead(prompt);
        Professor professor = new Professor(name, email, uniID);
        userRegistration.addProfessor(professor, passkey);
        Integer ID = professor.getProfessorID();
        return ID;
    }

    public int addFacultyController(String []credentials){
        String legalName = credentials[0];
        String email = credentials[1];
        String id = credentials[3];
        Integer uniID =  Integer.valueOf(id);
        String passkey = credentials[2];
        Professor professor = new Professor(legalName, email, uniID);
        userRegistration.addProfessor(professor, passkey);
        Integer ID = professor.getProfessorID();
        return ID;
    }

    public void removeProfessorView() throws IOException {
        String prompt = "You are removing an existing faculty member, please provide the required info:\n" +
                "What's the faculty's ID number:";
        String choiceTwo = printPromptAndRead(prompt);
        Integer id = Integer.valueOf(choiceTwo);
        if (userRegistration.getProfessorID(id) != null) {
            userRegistration.removeProfessor(id);
        } else {
            print("This faculty member does not seem to be in the registry...\n");
        }
    }

    public int removeFacultyController(String []credentials){
        String idString = credentials[0];
        int val;
        if (!Objects.equals(idString, "")){
            Integer id = Integer.valueOf(idString);
            if (userRegistration.getProfessorID(id) != null) {
                userRegistration.removeProfessor(id);
                val = 1;
            }
            else {
                print("This faculty member does not seem to be in the registry...\n");
                val = 0;
            }
        }
        else{val=0;}
        return val;
    }

    public void updateProfessorView() throws IOException {
        String prompt = "You are updating an existing faculty member, please provide the required info:\n" +
                "What's the faculty's ID number:";
        String choiceThree = printPromptAndRead(prompt);
        Integer id = Integer.valueOf(choiceThree);
        if (userRegistration.getProfessorID(id) != null) {
            prompt = "What would you like to set as your new password?\n";
            String newPassword = printPromptAndRead(prompt);
            userRegistration.updateProfessor(id, newPassword);
        } else {
            print("This faculty member does not seem to be in the registry...\n");
        }
    }

    public int updateFacultyController(String []credentials){
        String idString = credentials[0];
        int val;
        if (!Objects.equals(idString, "")) {
            Integer id = Integer.valueOf(idString);
            if (userRegistration.getProfessorID(id) != null) {
                String newPassword = credentials[1];
                userRegistration.updateProfessor(id, newPassword);
                val = 1;
            } else {
                print("This faculty member does not seem to be in the registry...\n");
                val = 0;
            }
        }
        else{val=0;}
        return val;
    }

    public int addStudentView() throws IOException {
        String prompt = "You are adding a new Student, please provide the required info:\nWhat's the student's legal name:";
        String legalName = printPromptAndRead(prompt);
        prompt = "What's the student's display name:";
        String displayName = printPromptAndRead(prompt);
        prompt = "What's the student's E-Mail:";
        String email = printPromptAndRead(prompt);
        prompt = "What's the University of the Student?";
        print(prompt);
        //prompt = String.valueOf(universityDAO.list());
        listUniversities();
        prompt = "Please choose from above from the list above";
        String id = printPromptAndRead(prompt);
        Integer uniID = Integer.valueOf(id);
        prompt = "What would you like to set as your password?";
        String passkey = printPromptAndRead(prompt);
        Student student = new Student(legalName, email, uniID, displayName);
        userRegistration.addStudent(student, passkey);
        Integer ID = student.getStudentID();
        // String studentID = String.valueOf(ID);
        // prompt = "The student's id is: " + studentID + "\n";
        // print(prompt);
        return ID;
    }

    public int addStudentController(String []credentials){
        String legalName = credentials[0];
        String displayName = credentials[1];
        String email = credentials[2];
        String id = credentials[4];
        Integer uniID =  Integer.valueOf(id);
        String passkey = credentials[3];
        Student student = new Student(legalName, email, uniID, displayName);
        userRegistration.addStudent(student, passkey);
        Integer ID = student.getStudentID();
        return ID;
    }

    public void removeStudentView() throws IOException {
        String prompt = "You are removing an existing Student, please provide the required info:\n" +
                "What's the student's ID number:";
        String choiceTwo = printPromptAndRead(prompt);
        Integer id = Integer.valueOf(choiceTwo);
        if (userRegistration.getStudentID(id) != null) {
            userRegistration.removeStudent(id);
        } else {
            print("This student does not seem to be in the registry...\n");
        }
    }

    public int removeStudentController(String []credentials){
        String idString = credentials[0];
        int val;
        if (!Objects.equals(idString, "")){
            Integer id = Integer.valueOf(idString);
            if (userRegistration.getStudentID(id) != null) {
                userRegistration.removeStudent(id);
                val = 1;
            }
            else {
                print("This student does not seem to be in the registry...\n");
                val = 0;
            }
        }
        else{val=0;}
        return val;
    }

    public void updateStudentView() throws IOException {
        String prompt = "You are updating an existing Student, please provide the required info:\n" +
                "What's the student's ID number:";
        String choiceThree = printPromptAndRead(prompt);
        Integer id = Integer.valueOf(choiceThree);
        if (userRegistration.getStudentID(id) != null) {
            prompt = "What would you like to set as your new password?\n";
            String newPassword = printPromptAndRead(prompt);
            prompt = "What would you like to change your display name to?\n";
            String newDisplayName = printPromptAndRead(prompt);
            userRegistration.updateStudent(id, newPassword, newDisplayName);
        } else {
            print("This student does not seem to be in the registry...\n");
        }
    }

    public int updateStudentController(String []credentials){
        String idString = credentials[0];
        int val;
        if (!Objects.equals(idString, "")) {
            Integer id = Integer.valueOf(idString);
            if (userRegistration.getStudentID(id) != null) {
                String newPassword = credentials[1];
                String newDisplayName = credentials[2];
                if(userRegistration.isUpdatable(id)) {
                    userRegistration.updateStudent(id, newPassword, newDisplayName);
                    val = 1;
                }
                else{
                    userRegistration.updateStudentPassword(id, newPassword);
                    val = 2;
                }
            } else {
                print("This student does not seem to be in the registry...\n");
                val = 0;
            }
        }
        else{val=0;}
        return val;
    }

    /**
     * View method for the student's options in the UserRegistration portal
     */
    public void studentOptions() throws IOException {
        String prompt = "Welcome, What Student-Option Would You Like?\n" +
                "   1. Register New Account\n" +
                "   2. Remove Existing Account\n" +
                "   3. Update Existing Account\n";
        while (true) {
            String choiceOne = printPromptAndRead(prompt);
            if (Objects.equals(choiceOne, "1")) {
                print("Account Registration Portal:\n");
                addStudentView();
                break;
            } else if (Objects.equals(choiceOne, "2")) {
                print("Account Deletion Portal:\n");
                removeStudentView();
                break;
            } else if (Objects.equals(choiceOne, "3")) {
                print("Account Update Portal:\n");
                updateStudentView();
                break;
            }
            print("Invalid Selection, please try again!");
        }
    }

    /**
     * View method for the professor's options in the UserRegistration portal
     */
    public void professorOptions() throws IOException {
        String prompt = "Welcome Faculty, What Would You Like to Do?\n" +
                "   1. Register New Account\n" +
                "   2. Remove Existing Account\n" +
                "   3. Update Existing Account\n";
        while (true) {
            String choiceOne = printPromptAndRead(prompt);
            if (Objects.equals(choiceOne, "1")) {
                print("Account Registration Portal:\n");
                addProfessorView();
                break;
            } else if (Objects.equals(choiceOne, "2")) {
                print("Account Deletion Portal:\n");
                removeProfessorView();
                break;
            } else if (Objects.equals(choiceOne, "3")) {
                print("Account Update Portal:\n");
                updateProfessorView();
                break;
            }
            print("Invalid Selection, please try again!");
        }
    }

    /**
     * View method for the general options in the UserRegistration portal
     */
    public void menuOptions() throws IOException {
        String prompt = "Welcome to the User Registration Portal.\n" +
                "Would You Like to Work With a Faculty Member or a Student?:\n" +
                "   1. Student\n" +
                "   2. Faculty Member\n" +
                "   3. Quit Program\n";
        while (true) {
            String choiceOne = printPromptAndRead(prompt);
            if (Objects.equals(choiceOne, "1")) {
                studentOptions();
            } else if (Objects.equals(choiceOne, "2")) {
                professorOptions();
            }
            else if(Objects.equals(choiceOne, "3")){
                break;
            }
            else {
                print("Invalid Selection, please try again!");
            }
        }
    }

    public void exitOption() {
        Platform.exit();
        System.exit(0);
    }

}

