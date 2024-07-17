package edu.duke.ece651.team2.courseManagement;

import edu.duke.ece651.team2.shared.Course;
import edu.duke.ece651.team2.shared.University;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.WindowMatchers;
import org.testfx.util.WaitForAsyncUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddCourseControllerTest extends ApplicationTest {
    // University university = new University("Duke", true);
    // CourseManagementInterface model;

    // @Override
    // public void start(Stage stage) {
    //     model = new CourseManagementMock(university);

    //     Scene scene = new Scene(new AddCourseController(model), 600, 500);
    //     stage.setScene(scene);
    //     stage.show();
    // }

    // @Test
    // public void testAddCourse() {
    //     Platform.runLater(() -> {
    //         clickOn("#CourseNameField");
    //         write("CourseName");
    //         clickOn("#ConfirmButton");
    //     });
    //     WaitForAsyncUtils.waitForFxEvents();

    //     Set<String> expected = new HashSet<>();
    //     expected.add("CourseName");
    //     assertEquals(expected, new HashSet<>(model.listCourses().stream().map(Course::getName).collect(Collectors.toSet())));
    // }

    // @Test
    // public void testEmptyName() {
    //     clickOn("#ConfirmButton");
    //     FxAssert.verifyThat(window("Error"), WindowMatchers.isShowing());
    // }
}
