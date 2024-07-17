package edu.duke.ece651.team2.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.internal.InputStreams;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.duke.ece651.team2.client.controller.ClientSideView;
import edu.duke.ece651.team2.client.controller.GeneralController;
import edu.duke.ece651.team2.shared.Password;
import edu.duke.ece651.team2.shared.Section;


public class GeneralControllerTest {

    @Test
    public void testSendObjectInt() throws IOException {
        OutputStream mockOut = mock(OutputStream.class);
        Socket mockSocket = mock(Socket.class);
        when(mockSocket.getOutputStream()).thenReturn(mockOut);
        ObjectOutputStream mockObjOut = new ObjectOutputStream(mockOut);

        GeneralController controller = new GeneralController();
        controller.setOut(mockObjOut);
        List<String> test = new ArrayList<>();
        test.add("a");

        // Call the method
        controller.sendObject(123);
        controller.sendObject(test);
    }

    @Test
    public void testDisconnectFromServer() throws IOException {
        // Mock dependencies
        ObjectOutputStream mockOut = mock(ObjectOutputStream.class);
        ObjectInputStream mockIn = mock(ObjectInputStream.class);
        Socket mockSocket = mock(Socket.class);

        // Create GeneralController instance
        GeneralController controller = new GeneralController();
        controller.setOut(mockOut);
        controller.setIn(mockIn);
        controller.setSocket(mockSocket);

        // Set up the socket as open
        when(mockSocket.isClosed()).thenReturn(false);

        // Call the method
        controller.disconnectFromServer();

        // Verify interactions
        verify(mockOut).close();
        verify(mockIn).close();
        verify(mockSocket).close();
        assertFalse(controller.getConnected());
    }



    @Test
    public void testLogin() throws IOException, ClassNotFoundException {
        String[] credentials = {"123", "password"};

        // Create in-memory streams
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject("1:someSerializedData"); 
        objectOutputStream.flush(); 
    
        byte[] serializedData = outStream.toByteArray(); 
        ByteArrayInputStream inStream = new ByteArrayInputStream(serializedData);
        ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
    
        // Mock ObjectMapper
        ObjectMapper objectMapper = mock(ObjectMapper.class);
        when(objectMapper.writeValueAsString(any(Password.class))).thenReturn("someSerializedPassword");
    
        // Create instance of GeneralController with in-memory streams and mock ObjectMapper
        GeneralController controller = new GeneralController();
        controller.setOut(objectOutputStream);
        controller.setIn(objectInputStream);
        controller.setMapper(objectMapper);
    
        // Call the method under test
        int userType = controller.login(credentials);
    
        // Assertion
        assertEquals(1, userType); 

        outStream = new ByteArrayOutputStream();
        objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject("2:someSerializedData"); 
        objectOutputStream.flush(); 
    
        serializedData = outStream.toByteArray(); 
        inStream = new ByteArrayInputStream(serializedData);
        objectInputStream = new ObjectInputStream(inStream);
    
        controller.setOut(objectOutputStream);
        controller.setIn(objectInputStream);

        userType = controller.login(credentials);
    
        assertEquals(2, userType); 

        outStream = new ByteArrayOutputStream();
        objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject("3:someSerializedData"); 
        objectOutputStream.flush(); 
    
        serializedData = outStream.toByteArray(); 
        inStream = new ByteArrayInputStream(serializedData);
        objectInputStream = new ObjectInputStream(inStream);
    
        controller.setOut(objectOutputStream);
        controller.setIn(objectInputStream);

        userType = controller.login(credentials);
    
        assertEquals(0, userType); 

    }

    @Test
    public void testStudentProfessorFunctionality() throws IOException{
        // Create in-memory streams
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject("someSerializedData"); 
        objectOutputStream.flush(); 
    
        byte[] serializedData = outStream.toByteArray(); 
        ByteArrayInputStream inStream = new ByteArrayInputStream(serializedData);
        ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
    
        // Mock ObjectMapper
        ObjectMapper objectMapper = mock(ObjectMapper.class);
        when(objectMapper.writeValueAsString(any(int.class))).thenReturn("someSerializedPassword");
    
        // Create instance of GeneralController with in-memory streams and mock ObjectMapper
        GeneralController controller = new GeneralController();
        controller.setOut(objectOutputStream);
        controller.setIn(objectInputStream);
        controller.setMapper(objectMapper);
    
        // Call the method under test
        controller.studentFunctionality(1);
        controller.studentFunctionality(2);
        controller.studentFunctionality(3);
        controller.professorFunctionality(1);
        controller.professorFunctionality(2);
        controller.professorFunctionality(3);
        controller.professorFunctionality(4);
        controller.professorFunctionality(5);
    }


    @Test
    public void testGenerateSectionAttendanceReport() throws IOException{
        // Create in-memory streams
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject("generateSectionAttendanceReport function test"); 
        objectOutputStream.flush(); 
    
        byte[] serializedData = outStream.toByteArray(); 
        ByteArrayInputStream inStream = new ByteArrayInputStream(serializedData);
        ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
    
    
        // Create instance of GeneralController with in-memory streams and mock ObjectMapper
        GeneralController controller = new GeneralController();
        controller.setIn(objectInputStream);

        controller.generateSectionAttendanceReport("test");
    }

    @Test
    public void testReceiveAllEnrolledSectionAndSetChoice() throws IOException{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject("ReceiveAllEnrolledSectionAndSetChoice"); 
        objectOutputStream.flush(); 
    
        byte[] serializedData = outStream.toByteArray(); 
        ByteArrayInputStream inStream = new ByteArrayInputStream(serializedData);
        ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
    
    
        // Create instance of GeneralController with in-memory streams and mock ObjectMapper
        GeneralController controller = new GeneralController();
        controller.setIn(objectInputStream);
        List<String> result = controller.receiveAllEnrolledSectionAndSetChoice(1);

        assertNotNull(result);
    }

    @Test
    public void testConfirmFromServer() throws IOException, ClassNotFoundException{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject("1||Success"); 
        objectOutputStream.flush(); 
    
        byte[] serializedData = outStream.toByteArray(); 
        ByteArrayInputStream inStream = new ByteArrayInputStream(serializedData);
        ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
    

        GeneralController controller = new GeneralController();
        controller.setIn(objectInputStream);
        String result = controller.confirmFromServer();

        assertNotNull(result);
        assertEquals("Success", result);
        controller.confirmFromServer();

        outStream = new ByteArrayOutputStream();
        objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject(null); 
        objectOutputStream.flush(); 
    
        serializedData = outStream.toByteArray(); 
        inStream = new ByteArrayInputStream(serializedData);
        objectInputStream = new ObjectInputStream(inStream);
    

        controller = new GeneralController();
        controller.setIn(objectInputStream);
        result = controller.confirmFromServer();

        assertNotNull(result);
        assertEquals("Server failed to send a message!", result);

    }

    @Test
    public void testChangeEmailPref() throws IOException{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject("1||Success"); 
        objectOutputStream.flush(); 
    
        byte[] serializedData = outStream.toByteArray(); 
        ByteArrayInputStream inStream = new ByteArrayInputStream(serializedData);
        ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
    

        GeneralController controller = new GeneralController();
        controller.setIn(objectInputStream);
        String result = controller.changeEmailPreferences();

        assertNotNull(result);
        assertEquals("Success", result);
        controller.changeEmailPreferences();

        outStream = new ByteArrayOutputStream();
        objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject(null); 
        objectOutputStream.flush(); 
    
        serializedData = outStream.toByteArray(); 
        inStream = new ByteArrayInputStream(serializedData);
        objectInputStream = new ObjectInputStream(inStream);
    

        controller = new GeneralController();
        controller.setIn(objectInputStream);
        result = controller.changeEmailPreferences();

        assertNotNull(result);
        assertEquals("Some Error happens ,maybe you dont have a course", result);
    }

    @Test
    public void testReceiveAllStudentInfoByLectureIdForRecord() throws IOException, ClassNotFoundException{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject("ReceiveAllStudentInfoByLectureIdForRecord"); 
        objectOutputStream.flush(); 
    
        byte[] serializedData = outStream.toByteArray(); 
        ByteArrayInputStream inStream = new ByteArrayInputStream(serializedData);
        ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
    
    
        // Create instance of GeneralController with in-memory streams and mock ObjectMapper
        GeneralController controller = new GeneralController();
        controller.setIn(objectInputStream);
        List<String> result = controller.receiveAllStudentInfoByLectureIdForRecord();

    }

    @Test
    public void testReceiveAllLectureBySectionId() throws IOException{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject("ReceiveAllLectureBySectionId"); 
        objectOutputStream.flush(); 
    
        byte[] serializedData = outStream.toByteArray(); 
        ByteArrayInputStream inStream = new ByteArrayInputStream(serializedData);
        ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
    
    
        // Create instance of GeneralController with in-memory streams and mock ObjectMapper
        GeneralController controller = new GeneralController();
        controller.setIn(objectInputStream);
        List<String> result = controller.receiveAllLectureBySectionId(1);

        assertNotNull(result);
    }

    @Test
    public void testReceiveAllTakenSectionAndSendChoice() throws IOException{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject("ReceiveAllTakenSectionAndSendChoice"); 
        objectOutputStream.flush(); 
    
        byte[] serializedData = outStream.toByteArray(); 
        ByteArrayInputStream inStream = new ByteArrayInputStream(serializedData);
        ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
    
    
        // Create instance of GeneralController with in-memory streams and mock ObjectMapper
        GeneralController controller = new GeneralController();
        controller.setIn(objectInputStream);
        List<String> result = controller.receiveAllTakenSectionAndSendChoice(1);

        assertNotNull(result);
    }
}


