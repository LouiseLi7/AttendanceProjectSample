package edu.duke.ece651.team2.client.controller;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.IOContext;

import edu.duke.ece651.team2.shared.Section;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

public class ButtonController {
    @FXML
    TextField logInFieldID;
    @FXML
    TextField logInFieldPassword;

    @FXML
    TextField codeID;

    @FXML
    ComboBox<String> chooseSection;

    @FXML
    ComboBox<String> chooseLecture;

    @FXML
    ComboBox<String> chooseStudent;

    GeneralController controller;

    public ButtonController(GeneralController controller) {
        this.controller = controller;
    }

    public void setlogInFieldID(TextField logTextField) {
        this.logInFieldID = logTextField;
    }

    public void setlogInFieldPassword(TextField logInFieldPassword) {
        this.logInFieldPassword = logInFieldPassword;
    }

    public void StudentLogIn(Object source) {
        Button b = (Button) source;
        Stage stage = (Stage) b.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/StudentPage.fxml"));
            loader.setControllerFactory(controller -> new ButtonController(this.controller));
            TitledPane page = (TitledPane) loader.load();
            Scene newScene = new Scene(page);
            stage.setScene(newScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ProfessorLogIn(Object source) {
        Button b = (Button) source;
        Stage stage = (Stage) b.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/ProfessorPage.fxml"));
            loader.setControllerFactory(controller -> new ButtonController(this.controller));
            TitledPane page = (TitledPane) loader.load();
            Scene newScene = new Scene(page);
            stage.setScene(newScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAlert(String prompt) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Alert Message");
        alert.setContentText(prompt);
        alert.showAndWait();
    }

    public void showAlertTF(String prompt) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Alert Message");
        alert.setContentText(prompt);
        ButtonType buttonTrue = new ButtonType("True");
        ButtonType buttonFalse = new ButtonType("False");
        alert.getButtonTypes().setAll(buttonTrue, buttonFalse);
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonTrue) {
                try {
                    controller.sendObject(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (buttonType == buttonFalse) {
                try {
                    controller.sendObject(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ButtonType chooseAttendance(String title, String content) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        ButtonType buttonP = new ButtonType("Present");
        ButtonType buttonT = new ButtonType("Tardy");
        ButtonType buttonA = new ButtonType("Absent");
        alert.getButtonTypes().setAll(buttonP, buttonT, buttonA);
        return alert.showAndWait().get();
    }

    @FXML
    public void onLogInButton(ActionEvent ae) throws ClassNotFoundException {
        if (!controller.getConnected()) {
            controller.connectToServer();
        }
        String[] credentials = new String[2];
        credentials[0] = logInFieldID.getText();
        credentials[1] = logInFieldPassword.getText();
        int res = controller.login(credentials);
        Object source = ae.getSource();
        if (res == 1) {
            StudentLogIn(source);
        } else if (res == 2) {
            ProfessorLogIn(source);
        } else {
            showAlert("Log In fail, please check and type again!");
        }
    }

    @FXML
    // havent handled the case on server!
    public void onReturnStudent(ActionEvent ae) throws IOException {
        controller.sendObject(-1);
        Object source = ae.getSource();
        StudentLogIn(source);
    }

    @FXML
    public void onReturnFaculty(ActionEvent ae) throws IOException {
        controller.sendObject(-1);
        Object source = ae.getSource();
        ProfessorLogIn(source);
    }

    @FXML
    public void onReturnFacultyStringList(ActionEvent ae) throws JsonProcessingException, IOException {
        List<String> obj = new ArrayList<>();
        obj.add("-1");
        controller.sendObject(obj);
        Object source = ae.getSource();
        ProfessorLogIn(source);
    }

    @FXML
    public void onSubmitSection(ActionEvent ae) throws IOException {
        int selectedIndex = chooseSection.getSelectionModel().getSelectedIndex();
        controller.sendObject(selectedIndex);
        Object source = ae.getSource();
        StudentLogIn(source);
    }

    @FXML
    public void onSubmitSectionFaculty(ActionEvent ae) throws IOException {
        int selectedIndex = chooseSection.getSelectionModel().getSelectedIndex();
        controller.sendObject(selectedIndex);
        Object source = ae.getSource();
        ProfessorLogIn(source);
    }

    @FXML
    public void onSubmitAndReturnFaculty(ActionEvent ae) throws IOException {
        int selectedIndex = chooseLecture.getSelectionModel().getSelectedIndex();
        controller.sendObject(selectedIndex);
        Object source = ae.getSource();
        ProfessorLogIn(source);
    }

    @FXML
    public void onSubmitSectionReceivePrompt(ActionEvent ae) throws IOException {
        onSubmitSection(ae);
        String prompt = controller.changeEmailPreferences();
        showAlertTF(prompt);
        prompt = controller.confirmFromServer();
        showAlert(prompt);
    }

    public void helperOnSubmitSectionReceiveLecture(Object source, String file) throws IOException {
        int selectedIndex = chooseSection.getSelectionModel().getSelectedIndex();
        controller.sendObject(selectedIndex);
        List<String> res = controller.receiveAllLectureBySectionId(1);
        if (res != null && res.get(0).equals("ERROR")) {
            showAlert(res.get(1));
            controller.sendObject(-1);
        } else {
            System.out.println("Get Lectures");
            helperShowSectionLecture(res, source, file, 2);
        }
    }

    @FXML
    public void onSubmitSectionReceiveLecture(ActionEvent ae) throws IOException {
        // int selectedIndex = chooseSection.getSelectionModel().getSelectedIndex();
        // controller.sendObject(selectedIndex);
        // List<String> res = controller.receiveAllLectureBySectionId(1);
        // if(res!=null && res.get(0).equals("ERROR")){
        // showAlert(res.get(1));
        // controller.sendObject(-1);
        // }
        // else{
        // System.out.println("Get Lectures");
        // Object source = ae.getSource();
        // helperShowSectionLecture(res,source,"/ui/LectureChoice.fxml",false);
        // }
        helperOnSubmitSectionReceiveLecture(ae.getSource(), "/ui/LectureChoice.fxml");

    }

    @FXML
    public void onSubmitSectionReceiveLectureUpdate(ActionEvent ae) throws IOException {
        helperOnSubmitSectionReceiveLecture(ae.getSource(), "/ui/LectureChoice2.fxml");
    }

    @FXML
    public void onGetStudentsFromLecture(ActionEvent ae) throws IOException, ClassNotFoundException {
        // onSubmitSection(ae);
        int selectedIndex = chooseLecture.getSelectionModel().getSelectedIndex();
        controller.sendObject(selectedIndex);
        // TODO: take students and prompt alert, then wrap up and send back to server!
        List<String> students = controller.receiveAllStudentInfoByLectureIdForRecord();
        List<String> ans = new ArrayList<>();
        for (String stu : students) {
            ButtonType b = chooseAttendance("Record Attendance", stu);
            if (b.getText().equals("Present")) {
                ans.add("P");
            } else if (b.getText().equals("Tardy")) {
                ans.add("T");
            } else {
                ans.add("A");
            }
        }

        controller.sendObject(ans);
        ProfessorLogIn(ae.getSource());
    }

    @FXML
    public void onGetStudentConfirmation(ActionEvent ae) throws JsonProcessingException, IOException {
        int selectedIndex = chooseStudent.getSelectionModel().getSelectedIndex();
        List<String> res = new ArrayList<>();
        res.add("" + selectedIndex);
        ButtonType b = chooseAttendance("Update Attendance", "Select the new Attendance");
        if (b.getText().equals("Present")) {
            res.add("1");
        } else if (b.getText().equals("Tardy")) {
            res.add("2");
        } else {
            res.add("3");
        }
        controller.sendObject(res);
        ProfessorLogIn(ae.getSource());
    }

    @FXML
    public void onGetStudentsFromLectureComboBox(ActionEvent ae) throws IOException, ClassNotFoundException {
        int selectedIndex = chooseLecture.getSelectionModel().getSelectedIndex();
        controller.sendObject(selectedIndex);
        // TODO: take students and prompt alert, then wrap up and send back to server!
        List<String> students = controller.receiveAllStudentInfoByLectureIdForRecord();
        helperShowSectionLecture(students, ae.getSource(), "/ui/StudentChoice.fxml", 3);
    }

    @FXML
    public void onSubmitReceiveCSV(ActionEvent ae) throws IOException {
        int selectedIndex = chooseSection.getSelectionModel().getSelectedIndex();
        controller.sendObject(selectedIndex);
        controller.generateSectionAttendanceReport(chooseSection.getValue());
        Object source = ae.getSource();
        ProfessorLogIn(source);
    }

    // for EC, studnets select a lecture to check in.
    @FXML
    public void onSubmitSectionAndLecture(ActionEvent ae) throws IOException {
        // select section and submit
        int selectedIndex = chooseSection.getSelectionModel().getSelectedIndex();
        controller.sendObject(selectedIndex);
        // get lecture list from server
        List<String> res = controller.receiveAllEnrolledLecturesAndSetChoice(4);
        if (res != null && res.get(0).equals("ERROR")) {
            showAlert(res.get(1));
            controller.sendObject(-1);
        } else {
            System.out.println("Get Lectures");
            helperShowSectionLecture(res, ae.getSource(), "/ui/LectureChoiceForStudents.fxml", 2);
        }
    }

    @FXML
    public void onCheckInButton(ActionEvent ae) throws IOException, ClassNotFoundException {
        String code = codeID.getText();
        String res = controller.checkInToServer(code);
        showAlert(res);
        StudentLogIn(ae.getSource());
    }

    @FXML
    public void onSubmitLectureToCheckIn(ActionEvent ae) throws IOException {
        // select lecture and submit
        int selectedIndex = chooseLecture.getSelectionModel().getSelectedIndex();
        controller.sendObject(selectedIndex);

        helperTypeCodeToCheckIn(ae.getSource(), "/ui/studentCheckIn.fxml");

    }

    public void helperTypeCodeToCheckIn(Object source, String file) {
        Button b = (Button) source;
        Stage stage = (Stage) b.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            loader.setControllerFactory(controller -> new ButtonController(this.controller));
            TitledPane page = (TitledPane) loader.load();
            Scene newScene = new Scene(page);
            stage.setScene(newScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void helperShowSectionLecture(List<String> res, Object source, String file, int choice) {
        Button b = (Button) source;
        Stage stage = (Stage) b.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            loader.setControllerFactory(controller -> new ButtonController(this.controller));
            TitledPane page = (TitledPane) loader.load();
            ButtonController bcontroller = loader.getController();
            if (choice == 1) {
                ComboBox<String> chooseSection = bcontroller.chooseSection;
                if (chooseSection != null) {
                    ObservableList<String> sections = FXCollections.observableArrayList(res);
                    chooseSection.setItems(sections);
                } else {
                    System.out.println("ComboBox not found in FXML file.");
                }
            } else if (choice == 2) {
                ComboBox<String> chooseLecture = bcontroller.chooseLecture;
                if (chooseLecture != null) {
                    ObservableList<String> lectures = FXCollections.observableArrayList(res);
                    chooseLecture.setItems(lectures);
                } else {
                    System.out.println("ComboBox not found in FXML file.");
                }

            } else {
                ComboBox<String> chooseStudent = bcontroller.chooseStudent;
                if (chooseStudent != null) {
                    ObservableList<String> students = FXCollections.observableArrayList(res);
                    chooseStudent.setItems(students);
                } else {
                    System.out.println("ComboBox not found in FXML file.");
                }
            }
            Scene newScene = new Scene(page);
            stage.setScene(newScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onGetEmailPreference(ActionEvent ae) throws IOException {
        controller.studentFunctionality(1);
        List<String> res = controller.receiveAllEnrolledSectionAndSetChoice(1);
        if (res != null && res.get(0).equals("ERROR")) {
            showAlert(res.get(1));
            controller.sendObject(-1);
        } else {
            System.out.println("Get Sections email Pre");
            Object source = ae.getSource();
            helperShowSectionLecture(res, source, "/ui/SectionChoice1.fxml", 1);
        }
    }

    @FXML
    public void onGetReport(ActionEvent ae) throws IOException {
        controller.studentFunctionality(2);
        List<String> res = controller.receiveAllEnrolledSectionAndSetChoice(2);
        if (res != null && res.get(0).equals("ERROR")) {
            showAlert(res.get(1));
            controller.sendObject(-1);
        } else {
            Object source = ae.getSource();
            helperShowSectionLecture(res, source, "/ui/SectionChoice.fxml", 1);
        }
    }

    @FXML
    public void onCheckIn(ActionEvent ae) throws IOException {
        controller.studentFunctionality(4);
        List<String> res = controller.receiveAllEnrolledSectionAndSetChoice(3);
        if (res != null && res.get(0).equals("ERROR")) {
            showAlert(res.get(1));
            controller.sendObject(-1);
        } else {
            Object source = ae.getSource();
            helperShowSectionLecture(res, source, "/ui/SectionForCheckIn.fxml", 1);
        }

    }

    public List<String> helperReadName(List<Section> sec) {
        List<String> res = new ArrayList<>();
        for (Section s : sec) {
            res.add("ID: " + s.getSectionID() + " Name: " + s.getName());
        }
        return res;
    }

    @FXML
    public void onTakeAttendance(ActionEvent ae) throws ClassNotFoundException, IOException {
        controller.professorFunctionality(1);
        List<String> res = controller.receiveAllTakenSectionAndSendChoice(1);
        if (res != null && res.get(0).equals("ERROR")) {
            showAlert(res.get(1));
            controller.sendObject(-1);
        } else {
            Object source = ae.getSource();
            helperShowSectionLecture(res, source, "/ui/SectionChoice4.fxml", 1);
        }
    }

    @FXML
    public void onUpdateAttendance(ActionEvent ae) throws ClassNotFoundException, IOException {
        controller.professorFunctionality(2);
        List<String> res = controller.receiveAllTakenSectionAndSendChoice(2);
        if (res != null && res.get(0).equals("ERROR")) {
            showAlert(res.get(1));
            controller.sendObject(-1);
        } else {
            Object source = ae.getSource();
            helperShowSectionLecture(res, source, "/ui/SectionChoice5.fxml", 1);
        }
    }

    @FXML
    public void onGetAttendance(ActionEvent ae) throws IOException, ClassNotFoundException {
        controller.professorFunctionality(3);
        List<String> res = controller.receiveAllTakenSectionAndSendChoice(3);
        if (res != null && res.get(0).equals("ERROR")) {
            showAlert(res.get(1));
            controller.sendObject(-1);
        } else {
            Object source = ae.getSource();
            helperShowSectionLecture(res, source, "/ui/SectionChoice3.fxml", 1);
        }
    }

    @FXML
    public void onSetFaculty(ActionEvent ae) throws ClassNotFoundException {
        controller.professorFunctionality(4);
        List<Section> sections = controller.chooseSection();
        List<String> res = helperReadName(sections);
        Object source = ae.getSource();
        helperShowSectionLecture(res, source, "/ui/SectionChoice2.fxml", 1);
    }

    public void helperQuit(Object source) {
        Button b = (Button) source;
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();

        return;
    }

    @FXML
    public void onExitButtonStudent(ActionEvent ae) {

        controller.studentFunctionality(3);

        Object source = ae.getSource();
        helperQuit(source);

    }

    @FXML
    public void onExitButtonFaculty(ActionEvent ae) throws ClassNotFoundException {

        controller.professorFunctionality(5);
        Object source = ae.getSource();
        helperQuit(source);
    }

}
