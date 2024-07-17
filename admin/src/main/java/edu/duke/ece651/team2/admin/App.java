
package edu.duke.ece651.team2.admin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.io.*;
import java.net.*;
import java.util.List;

import edu.duke.ece651.team2.server.DAOFactory;
import edu.duke.ece651.team2.server.UniversityDAO;
import edu.duke.ece651.team2.shared.University;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;
import javafx.application.Application;

public class App extends Application {
  public void readUniversities(String filename) throws IOException {
    UniversityDAO universityDAO = new UniversityDAO(new DAOFactory());
    BufferedReader br = new BufferedReader(new FileReader(filename));
    String line = br.readLine();
    while ((line = br.readLine()) != null) {
      // Split the line by comma
      String[] data = line.split(",");
      boolean support;
      if (data[1].equals("False")) {
        support = false;
      } else {
        support = true;
      }
      if (!universityDAO.exist(data[0])) {
        universityDAO.create(new University(data[0], support));
      }
    }
  }

  /**
   * The userRegistration method for the Attendance Manager application.
   * 
   * @throws IOException We will not handle this exception.
   */
  public void userRegistrationApp() throws IOException {
    readUniversities("universities.csv");
    // userRegistrationView.menuOptions();
  }

  private List<University> universityNames() {
    DAOFactory daoFactory = new DAOFactory();
    UniversityDAO uniDAO = new UniversityDAO(daoFactory);
    return uniDAO.list();
  }

  @Override
  public void start(Stage stage) throws IOException {
    userRegistrationApp();
    URL xmlResource = getClass().getResource("/ui/UserSelect.fxml");
    URL cssResource = getClass().getResource("/ui/settings.css");
    FXMLLoader loader = new FXMLLoader(xmlResource);
    // loader.setControllerFactory(controller -> new UserRegistrationController());
    // loader.setControllerFactory(controller -> new
    // UserRegistrationController(universityNames()));
    TitledPane tp = loader.load();
    Scene scene = new Scene(tp, 640, 480);
    scene.getStylesheets().add(cssResource.toString());
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }

}
