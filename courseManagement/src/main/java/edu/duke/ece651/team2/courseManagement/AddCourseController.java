package edu.duke.ece651.team2.courseManagement;

import edu.duke.ece651.team2.shared.Course;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class AddCourseController extends SuperController {
    @FXML
    Button ConfirmButton;

    @FXML
    TextField CourseNameField;

    AddCourseController(CourseManagementInterface model) {
        super(model, "/views/addCourse.fxml");
        setConfirmButton();
    }

    private void setConfirmButton() {
        ConfirmButton.setOnAction(actionEvent -> {
            String newName = CourseNameField.getText();
            if(newName.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a new name", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            Course course = new Course(newName, model.getUniversity().getId());
            model.addCourse(course);
            ConfirmButton.getScene().setRoot(model.getMainMenuController());
        });
    }
}
