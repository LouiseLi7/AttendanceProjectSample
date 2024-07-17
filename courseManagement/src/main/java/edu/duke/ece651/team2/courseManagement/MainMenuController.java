package edu.duke.ece651.team2.courseManagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class MainMenuController extends AnchorPane {
    @FXML Button UpdateCourseButton;
    @FXML Button DeleteCourseButton;
    @FXML Button AddCourseButton;
    @FXML Button UpdateSectionButton;
    @FXML Button DeleteSectionButton;
    @FXML Button AddSectionButton;
    @FXML Button AddStudentButton;
    @FXML Button BulkAddStudentsButton;
    @FXML Button AddLectureButton;
    @FXML Button RemoveStudentButton;
    @FXML Text welcomeText;

    private final CourseManagementInterface model;

    public MainMenuController(CourseManagementInterface model) {
        this.model = model;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/views/menu.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        welcomeText.setText("Welcome to course management for " + model.getUniversity().getName());
        setUpdateCourseButton();
        setDeleteCourseButton();
        setAddCourseButton();
        setUpdateSectionButton();
        setAddSectionButton();
        setDeleteSectionButton();
        setAddStudentButton();
        setBulkAddStudentsButton();
        setAddLectureButton();
        setRemoveStudentButton();
    }

    private void setUpdateCourseButton() {
        UpdateCourseButton.setOnAction(actionEvent -> {
            UpdateCourseButton.getScene().setRoot(new ModifyCourseController(model));
        });
    }

    private void setDeleteCourseButton() {
        DeleteCourseButton.setOnAction(actionEvent -> {
            DeleteCourseButton.getScene().setRoot(new DeleteCourseController(model));
        });
    }

    private void setAddCourseButton() {
        AddCourseButton.setOnAction(actionEvent -> {
            AddCourseButton.getScene().setRoot(new AddCourseController(model));
        });
    }

    private void setUpdateSectionButton() {
        UpdateSectionButton.setOnAction(actionEvent -> {
            UpdateSectionButton.getScene().setRoot(new ModifySectionController(model));
        });
    }

    private void setAddSectionButton() {
        AddSectionButton.setOnAction(actionEvent -> {
            AddSectionButton.getScene().setRoot(new AddSectionController(model));
        });
    }

    private void setDeleteSectionButton() {
        DeleteSectionButton.setOnAction(actionEvent -> {
            DeleteSectionButton.getScene().setRoot(new DeleteSectionController(model));
        });
    }

    private void setAddStudentButton() {
        AddStudentButton.setOnAction(actionEvent -> {
            AddStudentButton.getScene().setRoot(new EnrollStudentController(model));
        });
    }

    private void setBulkAddStudentsButton() {
        BulkAddStudentsButton.setOnAction(actionEvent -> {
            BulkAddStudentsButton.getScene().setRoot(new BulkEnrollStudentsController(model));
        });
    }

    private void setAddLectureButton() {
        AddLectureButton.setOnAction(actionEvent -> {
            AddLectureButton.getScene().setRoot(new AddLectureController(model));
        });
    }

    private void setRemoveStudentButton() {
        RemoveStudentButton.setOnAction(actionEvent -> {
            RemoveStudentButton.getScene().setRoot(new UnenrollStudentController(model));
        });
    }
}
