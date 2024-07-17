package edu.duke.ece651.team2.client;

import javafx.scene.Scene;
import javafx.stage.Stage;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

class AppTest {
  App a;

  @Start
  public void start(Stage stage) throws IOException{
      a = new App();
      a.start(stage);
  }

  public void testSubmit(){
    
  }
}
