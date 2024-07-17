// package edu.duke.ece651.team2.client;

// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.never;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.Mockito;
// import org.testfx.framework.junit5.ApplicationExtension;
// import org.testfx.framework.junit5.Start;
// import org.testfx.util.WaitForAsyncUtils;

// import edu.duke.ece651.team2.client.controller.ButtonController;
// import edu.duke.ece651.team2.client.controller.GeneralController;
// import javafx.application.Platform;
// import javafx.event.ActionEvent;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.TextField;
// import javafx.scene.layout.StackPane;
// import javafx.stage.Stage;

// @ExtendWith(ApplicationExtension.class)
// public class ButtonControllerTest {


//     private ButtonController buttonController;
//     private GeneralController mockController;
//     TextField logInFieldID;
//     TextField logInFieldPassword;

//     @BeforeEach
//     void setUp() {
//         // Create a mock GeneralController
//         mockController = Mockito.mock(GeneralController.class);
        
//         // Create the ButtonController instance with the mock GeneralController
//         buttonController = new ButtonController(mockController);
//         logInFieldID = new TextField();
//         logInFieldPassword = new TextField();
//         buttonController.setlogInFieldID(logInFieldID);
//         buttonController.setlogInFieldPassword(logInFieldPassword);
//     }

//     @Test
//     void testOnLogInButtonStudent() throws ClassNotFoundException {
//         // Set up the test scenario
//         logInFieldID.setText("student id");
//         logInFieldPassword.setText("student password");

//         // Mock the GeneralController's behavior
//         when(mockController.getConnected()).thenReturn(true);
//         when(mockController.login(any(String[].class))).thenReturn(1); // Successful login

//         Button b = new Button("Log In");

//         Platform.runLater(() -> {
//             StackPane root = new StackPane(b);
//             Scene scene = new Scene(root);

//             Stage stage = new Stage();
//             stage.setScene(scene);
//             stage.show();
//             stage.close();

//             try {
//                 buttonController.onLogInButton(new ActionEvent(b, null));
//             } catch (ClassNotFoundException e) {
//                 e.printStackTrace();
//             }
//         });

//         WaitForAsyncUtils.waitForFxEvents();
//         // // Verify that the correct methods are called
//         // verify(mockController, times(1)).getConnected();
//         // verify(mockController, times(1)).login(any(String[].class));
//     }

//     @Test
//     void testOnLogInButtonFaculty() throws ClassNotFoundException {
//         // Set up the test scenario
//         logInFieldID.setText("professor id");
//         logInFieldPassword.setText("professor password");

//         // Mock the GeneralController's behavior
//         when(mockController.getConnected()).thenReturn(true);
//         when(mockController.login(any(String[].class))).thenReturn(2); // Successful login

//         Button b = new Button("Log In");

//         Platform.runLater(() -> {
//             StackPane root = new StackPane(b);
//             Scene scene = new Scene(root);

//             Stage stage = new Stage();
//             stage.setScene(scene);
//             stage.show();
//             stage.close();

//             try {
//                 buttonController.onLogInButton(new ActionEvent(b, null));
//             } catch (ClassNotFoundException e) {
//                 e.printStackTrace();
//             }
//         });

//         WaitForAsyncUtils.waitForFxEvents();
//         // // Verify that the correct methods are called
//         // verify(mockController, times(1)).getConnected();
//         // verify(mockController, times(1)).login(any(String[].class));
//     }

//     @Test
//     void testOnLogInButtonAlert() throws ClassNotFoundException {
//         // Set up the test scenario
//         logInFieldID.setText("no id");
//         logInFieldPassword.setText("no password");

//         // Mock the GeneralController's behavior
//         when(mockController.getConnected()).thenReturn(true);
//         when(mockController.login(any(String[].class))).thenReturn(-1);

//         Button b = new Button("Log In");

//         Platform.runLater(() -> {
//             StackPane root = new StackPane(b);
//             Scene scene = new Scene(root);

//             Stage stage = new Stage();
//             stage.setScene(scene);
//             stage.show();
//             stage.close();

//             try {
//                 buttonController.onLogInButton(new ActionEvent(b, null));
//             } catch (ClassNotFoundException e) {
//                 e.printStackTrace();
//             }
//         });

//         WaitForAsyncUtils.waitForFxEvents();
    
//     }


//     @Test
//     void testOnReturnStudent() throws ClassNotFoundException {

//         Button b = new Button("Return");

//         Platform.runLater(() -> {
//             StackPane root = new StackPane(b);
//             Scene scene = new Scene(root);

//             Stage stage = new Stage();
//             stage.setScene(scene);
//             stage.show();
//             stage.close();

//             try {
//                 buttonController.onReturnStudent(new ActionEvent(b, null));
//                 //buttonController.onReturnFaculty(new ActionEvent(b, null));
//             } catch (Exception e) {
//                 e.printStackTrace();
//             }
//         });

//         WaitForAsyncUtils.waitForFxEvents();
//     }

//     @Test
//     void testOnReturnFaculty() throws ClassNotFoundException {

//         Button b = new Button("Return");

//         Platform.runLater(() -> {
//             StackPane root = new StackPane(b);
//             Scene scene = new Scene(root);

//             Stage stage = new Stage();
//             stage.setScene(scene);
//             stage.show();
//             stage.close();

//             try {
//                 buttonController.onReturnFaculty(new ActionEvent(b, null));
//             } catch (Exception e) {
//                 e.printStackTrace();
//             }
//         });

//         WaitForAsyncUtils.waitForFxEvents();
//     }

    
// }
