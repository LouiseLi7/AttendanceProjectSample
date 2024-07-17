package edu.duke.ece651.team2.server;

import edu.duke.ece651.team2.shared.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.ConcurrentHashMap;

public class ClientHandler implements Runnable {
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket clientSocket;
    private ServerSideView serverSideView;
    private ServerSideController serverSideController;
    private ObjectMapper mapper = new ObjectMapper();
    private int userId;
    private int status; // whether the user is professor of student; // student - 1, faculty - 2, error
                        // - 0

    // private ConcurrentHashMap<Integer, CacheForLecture> lectureCacheMap;

    public ClientHandler(Socket clientSocket, ServerSideView serverSideView,
            ServerSideController serverSideController) {
        this.clientSocket = clientSocket;
        this.serverSideView = serverSideView;
        this.serverSideController = serverSideController;
        this.userId = serverSideController.getUserId();
        this.status = serverSideController.getStatus();
        // this.lectureCacheMap = lectureCacheMap;
    }

    public void setUserID(Integer id) {
        this.userId = id;
    }

    // public void takeAttendance() throws ClassNotFoundException {
    // List<Section> sections = serverSideController.getInstructSection();
    // Section s_chosen = serverSideController.getChosenSection(sections);
    // // To get List of students
    // // TODO
    // }

    public void beFaculty() throws ClassNotFoundException {
        List<Section> sections = serverSideController.getNoFacultySection();
        int s_chosen = serverSideController.getChosenSection(sections);
        if (s_chosen != -1) {
            serverSideController.setFaculty(sections.get(s_chosen));
        }
    }

    public String handleFacultyRequest(Integer request) throws ClassNotFoundException {
        String res = "";
        if (request == 1) {
            // Execute recording attendance
            // generate dynamic code
            // serverSideController.startGenerateCode();
            handleRecordAttendance();
        } else if (request == 2) {
            // Execute updating attendance
            handleUpdateAttendance();
        } else if (request == 3) {
            // Execute exporting student attendance information
            handleExportAttendanceInfo();
        } else if (request == 4) {
            // Handle selecting course to teach
            beFaculty();
        } else if (request == 5) {
            return "break";
        } else {
            res = "Invalid request!";
        }
        return res;
    }

    private void handleUpdateAttendance() {
        // 1. send all course and section and get user's choice
        // 2. send all lecture and get user's choice
        // 3. send all enrolled students'info and status and get user's choice
        // 4. return updated successfully
        serverSideController.sendAllTeachedSectionNames(2, userId);
    }

    private void handleRecordAttendance() {
        // 1. send all course and section and get user's choice
        // 2. send all lecture and get user's choice
        // 3. send all enrolled students'info and status and get user's all choice
        // 4. return recorded successfully
        serverSideController.sendAllTeachedSectionNames(1, userId);
    }

    private void handleExportAttendanceInfo() {
        // 1. send all course and section and get user's choice
        // 2. send all lecture and get user's choice
        // 3. generate file and send file
        serverSideController.sendAllTeachedSectionNames(3, userId);
    }

    public String handleStudentRequest(Integer request) throws IOException, ClassNotFoundException {
        String res = "";
        if (request == 1) {
            // Execute setting email preferences
            serverSideController.handleChangeEmailPreference(userId);
        } else if (request == 2) {
            // Execute getting attendance report
            serverSideController.handleGetAttendanceReport(userId);
        } else if (request == 3) {
            return "break";
        } else if (request == 4) {
            // students submit code
            serverSideController.handleAttendance(userId);
        } else {
            res = "Invalid request!";
        }
        return res;
    }

    @Override
    public void run() {
        try {
            // in = new ObjectInputStream(clientSocket.getInputStream());
            // out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = serverSideController.getObjectInputStream();
            out = serverSideController.getObjectOutputStream();

            // Receive client request and process it
            while (true) {
                // User authentication fail
                int request = (int) in.readObject();
                // serverSideController.executePeriodicTask();
                // if (userId == -1 || status == -1) {
                // String response = "User authentication failed!";
                // out.writeObject(response); // Send response back to client
                // break;
                // }

                // Process client request using controller
                if (status == 1) {
                    // student
                    String response = handleStudentRequest(request);
                    if (response.equals("break")) {
                        System.out.println("The student is leaving.");
                        break;
                    }

                } else {
                    // faculty
                    // handleFacultyRequest(request);
                    String response = handleFacultyRequest((int) request);
                    if (response.equals("break")) {
                        System.out.println("The faculty is leaving.");
                        break;
                    }

                }
            }
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}