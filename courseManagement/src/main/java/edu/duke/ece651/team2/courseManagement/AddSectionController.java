package edu.duke.ece651.team2.courseManagement;

import edu.duke.ece651.team2.shared.Course;
import edu.duke.ece651.team2.shared.Professor;
import edu.duke.ece651.team2.shared.Section;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

public class AddSectionController extends UpdateSectionSuperController {
    @FXML
    Button ConfirmButton;

    @FXML
    TextField SectionNameField;

    @FXML
    ComboBox<Professor> InstructorSelector;

    AddSectionController(CourseManagementInterface model) {
        super(model, "/views/addSection.fxml");

        setInstructorSelector();
        setConfirmButton();
    }

    private void setInstructorSelector() {
        InstructorSelector.getItems().addAll(model.listProfessors());
        Callback<ListView<Professor>, ListCell<Professor>> cellFactory = new Callback<ListView<Professor>, ListCell<Professor>>() {
            @Override
            public ListCell<Professor> call(ListView<Professor> universityListView) {
                return new ListCell<Professor>() {
                    @Override
                    public void updateItem(Professor item, boolean empty) {
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

        InstructorSelector.setCellFactory(cellFactory);
        InstructorSelector.setButtonCell(cellFactory.call(null));
    }

    private void setConfirmButton() {
        ConfirmButton.setOnAction(actionEvent -> {
            Course course = CourseSelector.getValue();
            if(course == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a course", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            Professor professor = InstructorSelector.getValue();
            String newName = SectionNameField.getText();
            if(newName.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a name", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            if(professor == null) {
                Section section = new Section(course.getCourseID(), null, newName);
                model.addSection(section);
            } else {
                Section section = new Section(course.getCourseID(), professor.getProfessorID(), newName);
                model.addSection(section);
            }
            ConfirmButton.getScene().setRoot(model.getMainMenuController());
        });
    }
}
