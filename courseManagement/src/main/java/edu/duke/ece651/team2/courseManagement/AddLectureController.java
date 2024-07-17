package edu.duke.ece651.team2.courseManagement;

import edu.duke.ece651.team2.shared.Lecture;
import edu.duke.ece651.team2.shared.Section;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class AddLectureController extends UpdateSectionSuperController {
    @FXML DatePicker datePicker;
    @FXML Button AddButton;

    AddLectureController(CourseManagementInterface model) {
        super(model, "/views/addLecture.fxml");
        setAddButton();
    }

    private void setAddButton() {
        AddButton.setOnAction(actionEvent -> {
            Section section = SectionSelector.getValue();
            if (section == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a section", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            LocalDate date = datePicker.getValue();
            if(date == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a date", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            Lecture lecture = new Lecture(section.getSectionID(), date);
            model.addLecture(lecture);
            AddButton.getScene().setRoot(model.getMainMenuController());
        });
    }
}
