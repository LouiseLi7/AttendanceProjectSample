package edu.duke.ece651.team2.courseManagement;

import edu.duke.ece651.team2.shared.Course;
import edu.duke.ece651.team2.shared.Section;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class ModifySectionController extends UpdateSectionSuperController {
    @FXML
    Button ConfirmButton;

    @FXML
    TextField SectionNameField;

    ModifySectionController(CourseManagementInterface model) {
        super(model, "/views/modifySection.fxml");

        setConfirmButton();
    }

    private void setConfirmButton() {
        ConfirmButton.setOnAction(actionEvent -> {
            Section section = SectionSelector.getValue();
            if(section == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a section", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            String newName = SectionNameField.getText();
            if(newName.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a name", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            section.setName(newName);
            model.updateSection(section);
            ConfirmButton.getScene().setRoot(model.getMainMenuController());
        });
    }
}
