package edu.duke.ece651.team2.courseManagement;

import edu.duke.ece651.team2.shared.Course;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ModifyCourseController extends UpdateCourseSuperController {
    @FXML TextField CourseNameField;
    @FXML Button ConfirmButton;

    ModifyCourseController(CourseManagementInterface model) {
        super(model, "/views/modifyCourse.fxml");

        setConfirmButton();
    }

    private void setConfirmButton() {
        ConfirmButton.setOnAction(actionEvent -> {
            Course course = CourseSelector.getValue();
            if(course == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a course", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            String newName = CourseNameField.getText();
            if(newName.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a new name", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            course.setName(newName);
            model.updateCourse(course);
            ConfirmButton.getScene().setRoot(model.getMainMenuController());
        });
    }

}
