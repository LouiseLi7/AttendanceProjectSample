package edu.duke.ece651.team2.courseManagement;

import edu.duke.ece651.team2.shared.Course;
import edu.duke.ece651.team2.shared.Section;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class DeleteSectionController extends UpdateSectionSuperController {
    @FXML
    Button ConfirmButton;

    DeleteSectionController(CourseManagementInterface model) {
        super(model, "/views/deleteSection.fxml");

        setConfirmButton();
    }

    private void setConfirmButton() {
        ConfirmButton.setOnAction(actionEvent -> {
            Section section = SectionSelector.getValue();
            if(section == null) {
                Alert alert = new Alert(AlertType.ERROR, "Please select a section", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete " + section.getName() + "?", ButtonType.OK, ButtonType.CANCEL);
            alert.showAndWait().filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> {
                        model.removeSection(section);
                        ConfirmButton.getScene().setRoot(model.getMainMenuController());
                    });
        });
    }
}
