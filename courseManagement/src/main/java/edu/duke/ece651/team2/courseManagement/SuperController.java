package edu.duke.ece651.team2.courseManagement;

import edu.duke.ece651.team2.shared.Course;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;

public abstract class SuperController extends AnchorPane {
    @FXML
    Button CancelButton;

    protected final CourseManagementInterface model;

    SuperController(CourseManagementInterface model, String fxml) {
        this.model = model;

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                fxml));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setCancelButton();
    }


    protected void setCancelButton() {
        CancelButton.setOnAction(actionEvent -> {
            CancelButton.getScene().setRoot(model.getMainMenuController());
        });
    }


}
