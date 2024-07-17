package edu.duke.ece651.team2.admin;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.IOContext;

import edu.duke.ece651.team2.shared.Section;
import edu.duke.ece651.team2.shared.University;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;


public class UserRegistrationController {
    @FXML
    TextField studentLegalName;
    @FXML
    TextField studentDisplayName;
    @FXML
    TextField studentEmail;
    @FXML
    TextField studentPassword;
    @FXML
    TextField studentIDNumber;
    @FXML
    TextField facultyLegalName;
    @FXML
    TextField facultyEmail;
    @FXML
    TextField facultyPassword;
    @FXML
    TextField facultyIDNumber;
    @FXML
    ComboBox<University> universityComboBox;

    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    UserRegistration userRegistration = new UserRegistration();
    UserRegistrationView controller = new UserRegistrationView(System.out, userRegistration, input);

    public UserRegistrationController(){}

    // public UserRegistrationController(UserRegistrationView controller) {
    //     this.controller = controller;
    // }

    public void setStudentLegalName(TextField studentLegalName){
        this.studentLegalName = studentLegalName;
    }
    public void setStudentDisplayName(TextField studentDisplayName){
        this.studentDisplayName = studentDisplayName;
    }
    public void setStudentEmail(TextField studentEmail){
        this.studentEmail = studentEmail;
    }
    public void setStudentPassword(TextField studentPassword){
        this.studentPassword = studentPassword;
    }
    public void setStudentIDNumber(TextField studentIDNumber){
        this.studentIDNumber = studentIDNumber;
    }
    public void setFacultyLegalName(TextField facultyLegalName){
        this.facultyLegalName = facultyLegalName;
    }
    public void setFacultyEmail(TextField facultyEmail){
        this.facultyEmail = facultyEmail;
    }
    public void setFacultyPassword(TextField facultyPassword){
        this.facultyPassword = facultyPassword;
    }
    public void setFacultyIDNumber(TextField facultyIDNumber){
        this.facultyIDNumber = facultyIDNumber;
    }

    public void showAlert(String prompt){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Alert Message");
        alert.setContentText(prompt);
        alert.showAndWait();
    }

    @FXML
    public void handleStudentRegistration(ActionEvent event){
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/StudentOptions.fxml"));
            TitledPane page = (TitledPane) loader.load();
            Scene newScene = new Scene(page);
            stage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleFacultyRegistration(ActionEvent event){
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/FacultyOptions.fxml"));
            TitledPane page = (TitledPane) loader.load();
            Scene newScene = new Scene(page);
            stage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToAddStudent(ActionEvent event){
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/AddStudent.fxml"));
        
            Parent root = loader.load();
            UserRegistrationController ucontroller = loader.getController();

            List<University> unis = controller.listUniversitiesController();
            ucontroller.setUniversities(unis);

            Scene newScene = new Scene(root);
            stage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //setUniversities(controller.listUniversitiesController());
    }

    @FXML
    public void goToAddFaculty(ActionEvent event){
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/AddFaculty.fxml"));

            Parent root = loader.load();
            UserRegistrationController ucontroller = loader.getController();

            List<University> unis = controller.listUniversitiesController();
            ucontroller.setUniversities(unis);

            Scene newScene = new Scene(root);
            stage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //setUniversities(controller.listUniversitiesController());
    }

    public void setUniversities(List<University> unis) {
        universityComboBox.getItems().addAll(unis);
        Callback<ListView<University>, ListCell<University>> cellFactory = new Callback<ListView<University>, ListCell<University>>() {
            @Override
            public ListCell<University> call(ListView<University> universityListView) {
                return new ListCell<University>() {
                    @Override
                    public void updateItem(University item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            setText(item.getName());
                        }
                    }
                };
            }
        };

        universityComboBox.setCellFactory(cellFactory);
        universityComboBox.setButtonCell(cellFactory.call(null));
    }


    @FXML
    public void onAddStudentSubmit(){
        String[] credentials = new String[5];
        credentials[0] = studentLegalName.getText();
        credentials[1] = studentDisplayName.getText();
        credentials[2] = studentEmail.getText();
        credentials[3] = studentPassword.getText();
        credentials[4] = String.valueOf(universityComboBox.getValue().getId());
        int res = controller.addStudentController(credentials);
        if(res != 0 &&
                (!Objects.equals(credentials[0], "") && !Objects.equals(credentials[1], "") &&
                !Objects.equals(credentials[2], "") && !Objects.equals(credentials[3], "")
                        && !Objects.equals(universityComboBox.getValue(), null))){
            showAlert("Sign-Up Successful!");
        }
        else if (Objects.equals(universityComboBox.getValue(), null)){
            showAlert("Sign-Up Failed, please University Selection!");
        }
        else{
            showAlert("Sign-Up Failed, please check and type again!");
        }
    }

    @FXML
    public void onAddFacultySubmit(){
        String[] credentials = new String[4];
        credentials[0] = facultyLegalName.getText();
        credentials[1] = facultyEmail.getText();
        credentials[2] = facultyPassword.getText();
        credentials[3] = String.valueOf(universityComboBox.getValue().getId());
        int res = controller.addFacultyController(credentials);
        if(res != 0 &&
                (!Objects.equals(credentials[0], "") && !Objects.equals(credentials[1], "")
                        && !Objects.equals(credentials[2], "")
                        && !Objects.equals(universityComboBox.getValue(), null))){
            showAlert("Sign-Up Successful!");
        }
        else if (Objects.equals(universityComboBox.getValue(), null)){
            showAlert("Sign-Up Failed, please University Selection!");
        }
        else{
            showAlert("Sign-Up Failed, please check and type again!");
        }
    }

    @FXML
    public void goToRemoveStudent(ActionEvent event){
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/RemoveStudent.fxml"));
            TitledPane page = (TitledPane) loader.load();
            Scene newScene = new Scene(page);
            stage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToRemoveFaculty(ActionEvent event){
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/RemoveFaculty.fxml"));
            TitledPane page = (TitledPane) loader.load();
            Scene newScene = new Scene(page);
            stage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onRemoveStudentSubmit() {
        String[] credentials = new String[1];
        credentials[0] = studentIDNumber.getText();
        int res = controller.removeStudentController(credentials);
        if(res != 0 && !Objects.equals(credentials[0], "")){
            showAlert("Removal Successful!");
        }
        else{
            showAlert("Removal Failed, please check and type again!");
        }
    }

    @FXML
    public void onRemoveFacultySubmit() {
        String[] credentials = new String[1];
        credentials[0] = facultyIDNumber.getText();
        int res = controller.removeFacultyController(credentials);
        if(res != 0 && !Objects.equals(credentials[0], "")){
            showAlert("Removal Successful!");
        }
        else{
            showAlert("Removal Failed, please check and type again!");
        }
    }

    @FXML
    public void goToUpdateStudent(ActionEvent event){
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/UpdateStudent.fxml"));
            TitledPane page = (TitledPane) loader.load();
            Scene newScene = new Scene(page);
            stage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToUpdateFaculty(ActionEvent event){
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/UpdateFaculty.fxml"));
            TitledPane page = (TitledPane) loader.load();
            Scene newScene = new Scene(page);
            stage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onUpdateStudentSubmit(){
        String[] credentials = new String[3];
        credentials[0] = studentIDNumber.getText();
        credentials[1] = studentPassword.getText();
        credentials[2] = studentDisplayName.getText();
        int res = controller.updateStudentController(credentials);
        if(res == 1 && (!Objects.equals(credentials[0], "")
                    && !Objects.equals(credentials[1], "")
                    && !Objects.equals(credentials[2], ""))){
            showAlert("Update Successful: \n" + "Display Name and Password have been changed!");
        }
        else if((res == 2 && (!Objects.equals(credentials[0], "")
                && !Objects.equals(credentials[1], "")
                && !Objects.equals(credentials[2], ""))) ||
                (res == 2 && (!Objects.equals(credentials[0], "")
                && !Objects.equals(credentials[1], "")))){
            showAlert("Update Successful: \n" + "Password has been changed!\n" + "(Display Name Access has Been Limited by Admin)");
        }
        else{
            showAlert("Update Failed, please check and type again!");
        }
    }

    @FXML
    public void onUpdateFacultySubmit(){
        String[] credentials = new String[2];
        credentials[0] = facultyIDNumber.getText();
        credentials[1] = facultyPassword.getText();
        int res = controller.updateFacultyController(credentials);
        if(res != 0 && (!Objects.equals(credentials[0], "")
                && !Objects.equals(credentials[1], ""))){
            showAlert("Update Successful!");
        }
        else{
            showAlert("Update Failed, please check and type again!");
        }
    }

    @FXML
    public void returnToMainRegistration(ActionEvent event){
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/UserSelect.fxml"));
            TitledPane page = (TitledPane) loader.load();
            Scene newScene = new Scene(page);
            stage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleExit(ActionEvent event) {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
        return;
    }

}

