package edu.duke.ece651.team2.admin;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.util.WaitForAsyncUtils;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class UserRegistrationControllerTest {


    private UserRegistrationView mockController;
    private UserRegistrationController userController;
    TextField studentLegalName;
    TextField studentDisplayName;
    TextField studentEmail;
    TextField studentPassword;
    TextField studentIDNumber;
    TextField facultyLegalName;
    TextField facultyEmail;
    TextField facultyPassword;
    TextField facultyIDNumber;

    @BeforeEach
    void setUp() {
        // Create a mock GeneralController
        mockController = Mockito.mock(UserRegistrationView.class);
        userController = Mockito.mock(UserRegistrationController.class);
        studentLegalName = new TextField();
        studentDisplayName = new TextField();
        studentEmail = new TextField();
        studentPassword = new TextField();
        studentIDNumber = new TextField();
        facultyLegalName = new TextField();
        facultyEmail = new TextField();
        facultyPassword = new TextField();
        facultyIDNumber = new TextField(); 
        userController.setStudentLegalName(studentLegalName);
        userController.setStudentDisplayName(studentDisplayName);
        userController.setStudentEmail(studentEmail);
        userController.setStudentPassword(studentPassword);
        userController.setStudentIDNumber(studentIDNumber);
        userController.setFacultyLegalName(facultyLegalName);
        userController.setFacultyEmail(facultyEmail);
        userController.setFacultyPassword(facultyPassword);
        userController.setFacultyIDNumber(facultyIDNumber);
    }

    @Disabled
    @Test
    void testStudentController() throws ClassNotFoundException {
        when(mockController.addStudentController(any())).thenReturn(1); // Assuming 1 means success
        // Set up the test scenario
        Platform.runLater(() -> {
            studentLegalName.setText("Kenan Colak");
            studentDisplayName.setText("kc566");
            studentEmail.setText("kc566@duke.edu");
            studentPassword.setText("passwordStudent");
        });
        // Perform action
        Platform.runLater(() -> userController.onAddStudentSubmit());
        WaitForAsyncUtils.waitForFxEvents();

        // Verify behavior
        verify(mockController, times(1)).addStudentController(any());
        verify(userController, times(1)).showAlert("Sign-Up Successful!");
    }

}
