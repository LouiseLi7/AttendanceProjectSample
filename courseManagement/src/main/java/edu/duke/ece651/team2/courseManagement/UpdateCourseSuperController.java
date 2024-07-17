package edu.duke.ece651.team2.courseManagement;

import edu.duke.ece651.team2.shared.Course;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.util.List;

abstract public class UpdateCourseSuperController extends SuperController {
    @FXML
    ComboBox<Course> CourseSelector;
    UpdateCourseSuperController(CourseManagementInterface model, String fxml) {
        super(model, fxml);

        setCourses();
    }

    protected void setCourses() {
        CourseSelector.getItems().addAll(model.listCourses());
        Callback<ListView<Course>, ListCell<Course>> cellFactory = new Callback<ListView<Course>, ListCell<Course>>() {
            @Override
            public ListCell<Course> call(ListView<Course> universityListView) {
                return new ListCell<Course>() {
                    @Override
                    public void updateItem(Course item, boolean empty) {
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

        CourseSelector.setCellFactory(cellFactory);
        CourseSelector.setButtonCell(cellFactory.call(null));
    }
}
