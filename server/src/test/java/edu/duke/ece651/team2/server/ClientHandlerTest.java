package edu.duke.ece651.team2.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.Socket;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.duke.ece651.team2.shared.Section;

public class ClientHandlerTest {

    @Test
    public void testHandleStudentRequest() throws IOException, ClassNotFoundException {
        ServerSideController msc = mock(ServerSideController.class);
        Socket ms = mock(Socket.class);
        ServerSideView mv = mock(ServerSideView.class);
        ClientHandler h = new ClientHandler(ms, mv, msc);
        doNothing().when(msc).handleChangeEmailPreference(anyInt());
        doNothing().when(msc).handleGetAttendanceReport(anyInt());

        assertEquals("", h.handleStudentRequest(1));
        assertEquals("", h.handleStudentRequest(2));
        assertEquals("break", h.handleStudentRequest(3));
        assertEquals("Invalid request!", h.handleStudentRequest(5));
    }

    @Test
    public void testHandleFacultyRequest() throws ClassNotFoundException {
        ServerSideController msc = mock(ServerSideController.class);
        Socket ms = mock(Socket.class);
        ServerSideView mv = mock(ServerSideView.class);
        ClientHandler h = new ClientHandler(ms, mv, msc);
        h.setUserID(1);
        doNothing().when(msc).sendAllTeachedSectionNames(eq(1), anyInt());
        doNothing().when(msc).sendAllTeachedSectionNames(eq(2), anyInt());
        doNothing().when(msc).sendAllTeachedSectionNames(eq(3), anyInt());
        when(msc.getNoFacultySection()).thenReturn(new ArrayList<>());
        when(msc.getChosenSection(anyList())).thenReturn(-1);

        assertEquals("", h.handleFacultyRequest(1));
        assertEquals("", h.handleFacultyRequest(2));
        assertEquals("", h.handleFacultyRequest(3));
        assertEquals("", h.handleFacultyRequest(4));
        assertEquals("break", h.handleFacultyRequest(5));
        assertEquals("Invalid request!", h.handleFacultyRequest(10));
        when(msc.getChosenSection(anyList())).thenReturn(0);
        doNothing().when(msc).setFaculty(any(Section.class));
    }
}
