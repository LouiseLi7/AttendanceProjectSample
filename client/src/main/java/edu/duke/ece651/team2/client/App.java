/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.duke.ece651.team2.client;

import edu.duke.ece651.team2.client.controller.ButtonController;
import edu.duke.ece651.team2.client.controller.GeneralController;
import edu.duke.ece651.team2.shared.*;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.HashMap;

public class App extends Application{

  @Override
  public void start(Stage stage) throws IOException {
    URL xmlResource = getClass().getResource("/ui/LogIn.fxml");
    URL cssResource = getClass().getResource("/ui/settings.css");
    FXMLLoader loader = new FXMLLoader(xmlResource);
    loader.setControllerFactory(controller -> new ButtonController(new GeneralController()));
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