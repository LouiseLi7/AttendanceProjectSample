package edu.duke.ece651.team2.client;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.Start;

import edu.duke.ece651.team2.client.controller.ClientSideController;
import edu.duke.ece651.team2.client.controller.ClientSideView;

public class ClientSideControllerTest {

    // ClientSideController controller = new ClientSideController(new
    // ClientSideView());

    @Test
    public void testDisplayPromptForStudent() {
        ClientSideView mockClientSideView = mock(ClientSideView.class);

        ClientSideController controller = new ClientSideController(mockClientSideView);

        controller.displayPromptForStudent(1);
        verify(mockClientSideView).displayMessage(
                "Below are all the courses you are enrolled in this semester, please select one to set your email preferences.");

        controller.displayPromptForStudent(2);
        verify(mockClientSideView).displayMessage(
                "Below are all the courses you are enrolled in this semester, please select one to get your attendance report.");

        controller.displayPromptForStudent(3);
    }

    @Test
    public void testDisplayPromptForFacultyGetSections() {
        ClientSideView mockClientSideView = mock(ClientSideView.class);

        ClientSideController controller = new ClientSideController(mockClientSideView);

        controller.displayPromptForFacultyGetSections(1);
        verify(mockClientSideView).displayMessage(
                "Below are the courses you are teaching this semester, please select a course to record your attendance.");

        controller.displayPromptForFacultyGetSections(2);
        verify(mockClientSideView).displayMessage(
                "Below are the courses you are teaching this semester, please select a course to update your attendance.");

        controller.displayPromptForFacultyGetSections(3);
        verify(mockClientSideView).displayMessage(
                "Below are the courses you are teaching this semester, please select a course to export students attendance info.");
        controller.displayPromptForFacultyGetSections(5);
    }

    @Test
    public void testDisplayPromptForFacultyGetLectures() {
        ClientSideView mockClientSideView = mock(ClientSideView.class);

        ClientSideController controller = new ClientSideController(mockClientSideView);

        controller.displayPromptForFacultyGetLectures(1);
        verify(mockClientSideView).displayMessage(
                "Below are the lectures you are teaching this semester, please select a course to record your attendance.");

        controller.displayPromptForFacultyGetLectures(2);
        verify(mockClientSideView).displayMessage(
                "Below are the lectures you are teaching this semester, please select a course to update your attendance.");

        controller.displayPromptForFacultyGetLectures(3);
        verify(mockClientSideView).displayMessage(
                "Below are the lectures you are teaching this semester, please select a course to export students attendance info.");

        controller.displayPromptForFacultyGetLectures(5);
    }

}