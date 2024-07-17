package edu.duke.ece651.team2.courseManagement;

import edu.duke.ece651.team2.shared.Course;
import edu.duke.ece651.team2.shared.Section;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.util.List;

public abstract class UpdateSectionSuperController extends UpdateCourseSuperController {

    @FXML ComboBox<Section> SectionSelector;

    UpdateSectionSuperController(CourseManagementInterface model, String fxml) {
        super(model, fxml);
    }

    @FXML
    public void courseSelected() {
        Course course = CourseSelector.getValue();
        SectionSelector.getItems().clear();
        SectionSelector.getItems().addAll(model.getSections(course));
        Callback<ListView<Section>, ListCell<Section>> cellFactory = new Callback<ListView<Section>, ListCell<Section>>() {
            @Override
            public ListCell<Section> call(ListView<Section> universityListView) {
                return new ListCell<Section>() {
                    @Override
                    public void updateItem(Section item, boolean empty) {
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

        SectionSelector.setCellFactory(cellFactory);
        SectionSelector.setButtonCell(cellFactory.call(null));
    }


}
