package edu.duke.ece651.team2.courseManagement;

import edu.duke.ece651.team2.shared.University;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import jdk.tools.jmod.Main;

import java.io.IOException;
import java.util.List;

public class LoginController extends AnchorPane {
    @FXML
    ComboBox<University> universityComboBox;

    @FXML
    Button logInButton;

    public LoginController(List<University> unis) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/views/login.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setUniversities(unis);
        setButtonAction();
    }

    public void setUniversities(List<University> unis) {
        universityComboBox.getItems().addAll(unis);
        Callback<ListView<University>, ListCell<University>> cellFactory = new Callback<ListView<University>, ListCell<University>>() {
            @Override
            public ListCell<University> call(ListView<University> universityListView) {
                return new ListCell<University>() {
                    @Override
                    public void updateItem(University item, boolean empty) {
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

        universityComboBox.setCellFactory(cellFactory);
        universityComboBox.setButtonCell(cellFactory.call(null));
    }

    public void setButtonAction() {
        logInButton.setOnAction(actionEvent -> {
            University university = universityComboBox.getValue();
            CourseManagement model = new CourseManagement(university);
            logInButton.getScene().setRoot(model.getMainMenuController());
        });
    }
}
