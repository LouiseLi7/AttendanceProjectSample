package edu.duke.ece651.team2.courseManagement;

import edu.duke.ece651.team2.shared.Section;
import edu.duke.ece651.team2.shared.Student;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

public class UnenrollStudentController extends UpdateSectionSuperController {
    @FXML
    ComboBox<Student> StudentSelector;
    @FXML
    Button UnenrollButton;

    UnenrollStudentController(CourseManagementInterface model) {
        super(model, "/views/unenrollStudent.fxml");
        setUnenrollButton();
    }

    @FXML
    public void sectionSelected() {
        Section section = SectionSelector.getValue();
        StudentSelector.getItems().clear();
        StudentSelector.getItems().addAll(model.getStudentsBySection(section));
        Callback<ListView<Student>, ListCell<Student>> cellFactory = new Callback<ListView<Student>, ListCell<Student>>() {
            @Override
            public ListCell<Student> call(ListView<Student> universityListView) {
                return new ListCell<Student>() {
                    @Override
                    public void updateItem(Student item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            setText(item.getLegalName());
                        }
                    }
                };
            }
        };

        StudentSelector.setCellFactory(cellFactory);
        StudentSelector.setButtonCell(cellFactory.call(null));
    }

    private void setUnenrollButton() {
        UnenrollButton.setOnAction(actionEvent -> {
            Section section = SectionSelector.getValue();
            if (section == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a section", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            Student student = StudentSelector.getValue();
            if (student == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a student", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to unenroll "
                    + student.getLegalName() + " from section " + section.getName() + "?",
                    ButtonType.OK, ButtonType.CANCEL);
            alert.showAndWait().filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> {
                        model.unenrollStudent(student, section);
                        UnenrollButton.getScene().setRoot(model.getMainMenuController());
                    });
        });
    }
}
