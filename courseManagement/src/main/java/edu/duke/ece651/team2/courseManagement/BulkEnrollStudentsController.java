package edu.duke.ece651.team2.courseManagement;

import edu.duke.ece651.team2.shared.Section;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class BulkEnrollStudentsController extends UpdateSectionSuperController {
    @FXML
    Button ChooseFileButton;
    @FXML
    Text FilenameText;
    @FXML
    CheckBox HeaderCheckBox;
    @FXML
    Button AddButton;

    FileChooser fileChooser;

    File selectedFile;

    BulkEnrollStudentsController(CourseManagementInterface model) {
        super(model, "/views/bulkAddStudents.fxml");

        fileChooser = new FileChooser();
        setChooseFileButton();
        setAddButton();
    }

    private void setChooseFileButton() {
        ChooseFileButton.setOnAction(actionEvent -> {
            selectedFile = fileChooser.showOpenDialog(ChooseFileButton.getScene().getWindow());
            FilenameText.setText(selectedFile.getName());
        });
    }

    private void setAddButton() {
        AddButton.setOnAction(actionEvent -> {
            Section section = SectionSelector.getValue();
            if (section == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a section", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            if (selectedFile == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a file", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            CSVLoader csvLoader = new CSVLoader();

            try {
                List<String> lines = csvLoader.getLines(selectedFile.getAbsolutePath(), HeaderCheckBox.isSelected());
                List<Integer> ids = csvLoader.getIds(lines);
                for (Integer id : ids) {
                    model.addStudentToSection(id, section);
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "File could not be read", ButtonType.OK);
                alert.showAndWait();
                AddButton.getScene().setRoot(model.getMainMenuController());
                return;
            }

            AddButton.getScene().setRoot(model.getMainMenuController());
        });
    }
}
