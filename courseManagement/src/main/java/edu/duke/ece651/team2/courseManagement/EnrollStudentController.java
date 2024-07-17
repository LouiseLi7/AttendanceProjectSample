package edu.duke.ece651.team2.courseManagement;

import edu.duke.ece651.team2.shared.Section;
import edu.duke.ece651.team2.shared.Student;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

public class EnrollStudentController extends UpdateSectionSuperController {
    @FXML ComboBox<Student> StudentSelector;
    @FXML Button EnrollButton;

    EnrollStudentController(CourseManagementInterface model) {
        super(model, "/views/enrollStudent.fxml");

        setStudentSelector();
        setEnrollButton();
    }

    private void setStudentSelector() {
        StudentSelector.getItems().addAll(model.getAllStudentsInUniversity());
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

    private void setEnrollButton() {
        EnrollButton.setOnAction(actionEvent -> {
            Section section = SectionSelector.getValue();
            if(section == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a section", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            Student student = StudentSelector.getValue();
            if(student == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a student", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            boolean success = model.addStudentToSection(section, student);
            if(!success) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "This student is already enrolled", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            EnrollButton.getScene().setRoot(model.getMainMenuController());
        });
    }
}
