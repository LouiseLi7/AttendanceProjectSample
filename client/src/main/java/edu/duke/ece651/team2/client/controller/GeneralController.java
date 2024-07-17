package edu.duke.ece651.team2.client.controller;

import edu.duke.ece651.team2.shared.*;

import java.io.*;
import java.net.*;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Object;

import java.util.ArrayList;
import java.util.List;

import org.checkerframework.checker.units.qual.m;
import java.net.InetAddress; //get local host IP address

import java.util.Arrays;

public class GeneralController {
    private ClientSideController clientSideController;
    private ClientSideView clientSideView;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket socket;
    private boolean connected = false;
    private ObjectMapper mapper = new ObjectMapper();

    public GeneralController() {
        clientSideView = new ClientSideView();
        clientSideController = new ClientSideController(clientSideView);
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public String[] parseMessage(String receivedMessage, String delimiter) {
        return receivedMessage.split(delimiter);
    }

    public boolean getConnected() {
        return this.connected;
    }

    public void sendObject(int choice) throws IOException {
        out.writeObject(choice);
        out.flush();
    }

    // public void sendObject(Object obj) throws IOException{
    // out.writeObject(obj);
    // out.flush();
    // }

    public void sendObject(List<String> obj) throws JsonProcessingException, IOException {
        out.writeObject(mapper.writeValueAsString(obj)); // send string type e.g. {'A','T','P'...}
        out.flush();
    }

    public String checkInToServer(String code) throws ClassNotFoundException {
        try {
            StudentSubmission studentSubmission = new StudentSubmission(code);
            String input = mapper.writeValueAsString(studentSubmission);
            out.writeObject(input);
            out.flush();

            String resulString = (String) in.readObject();
            return resulString;
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public int login(String[] credentials) throws ClassNotFoundException {
        int userType = 0;

        try {
            int userID = Integer.parseInt(credentials[0]);
            String input = mapper.writeValueAsString(new Password(userID, credentials[1]));
            out.writeObject(input); // Send userID & password to server (default send Password object)
            out.flush(); // Flush the stream to ensure data is sent immediately

            // Read login result from server (By default, a string object is sent back.Click
            // to apply)
            String res = (String) in.readObject();
            String[] response = parseMessage(res, ":");
            String choice = response[0];
            String prompt = response[1];
            if (choice.equals("1")) {
                userType = 1; // student
                // clientSideView.displayMessage("Login successful.");
            } else if (choice.equals("2")) {
                userType = 2; // Faculty
                // clientSideView.displayMessage("Login successful.");
            } else {
                userType = 0;
                clientSideView.displayMessage(prompt);
                clientSideView.displayMessage("Login failed. Please try again.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userType;
    }

    // Student-specific functionality
    public void studentFunctionality(int choice) {
        try {
            if (choice == 3) {
                // exit
                out.writeObject(choice);
                out.flush();
                disconnectFromServer();
            } else if (choice == 2) {
                // get report
                out.writeObject(choice); // int type to String
                out.flush();
                // getAttendanceReport();
            } else if (choice == 1) {
                // set preferences
                out.writeObject(choice); // int type to String
                out.flush();
                // setEmailPreferences();
            } else if (choice == 4) {
                // students check attendance (submit their codes)
                out.writeObject(choice);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Section> chooseSection() throws ClassNotFoundException {
        try {
            Section[] sections = mapper.readValue((String) in.readObject(), Section[].class);
            return Arrays.asList(sections);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void generateSectionAttendanceReport(String fileName) {
        clientSideView.displayMessage("Waiting for the exported file...");
        try {
            String output = (String) in.readObject();
            System.out.println(output);
            FileWriter writer = new FileWriter("export/" + fileName + ".csv");
            writer.write(output);
            clientSideView.displayMessage("Write successfully!");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getResponseList() {
        try {
            return mapper.readValue((String) in.readObject(), new TypeReference<ArrayList<String>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> receiveAllEnrolledSectionAndSetChoice(int num) {
        clientSideView.displayMessage("waiting for Sections...");
        clientSideController.displayPromptForStudent(num);
        try {
            // get from server
            List<String> responseList = getResponseList();
            clientSideView.displayMessage("" + responseList.size());
            if (responseList.isEmpty()) {
                clientSideView.displayMessage("You haven't taken any classes this semester!");
                responseList.add("ERROR");
                responseList.add("You haven't taken any classes this semester!");
                return responseList;
            } else {
                if (responseList.get(0).equals("ERROR")) {
                    clientSideView.displayMessage(responseList.get(1));
                    return responseList;
                } else {
                    return responseList;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            List<String> responseList = new ArrayList<>();
            responseList.add("Error: " + e.getMessage());
            return responseList;
        }
    }

    public List<String> receiveAllEnrolledLecturesAndSetChoice(int num) {
        clientSideView.displayMessage("waiting for lectures...");
        clientSideController.displayPromptForStudent(num);
        try {
            // get from server
            List<String> responseList = getResponseList();
            clientSideView.displayMessage("" + responseList.size());
            if (responseList.isEmpty()) {
                clientSideView.displayMessage("You haven't taken any classes this semester!");
                responseList.add("ERROR");
                responseList.add("You haven't taken any classes this semester!");
                return responseList;
            } else {
                if (responseList.get(0).equals("ERROR")) {
                    clientSideView.displayMessage(responseList.get(1));
                    return responseList;
                } else {
                    return responseList;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            List<String> responseList = new ArrayList<>();
            responseList.add("Error: " + e.getMessage());
            return responseList;
        }
    }

    public String confirmFromServer() {
        try {
            String responseStr = (String) in.readObject();
            if (responseStr != null) {
                String[] parts = responseStr.split("\\|\\|");
                // String stateCode = parts[0]; // 0/1 0 - error, 1 - success
                String prompt = parts[1];
                return prompt;
            } else {
                return "Server failed to send a message!";
                // clientSideView.displayMessage("Server failed to send a message!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Some error happens";
        }
    }

    public String changeEmailPreferences() {
        clientSideView.displayMessage("Check Course Subscription Status...");
        try {
            // get from server
            String responseStr = (String) in.readObject();
            if (responseStr != null) {
                String[] parts = responseStr.split("\\|\\|");
                // String stateCode = parts[0]; // 0/1 0 - error, 1 - success
                String prompt = parts[1];
                return prompt;
            }
            return "Some Error happens ,maybe you dont have a course";
        } catch (Exception e) {
            e.printStackTrace();
            return "Some Error happens";
        }
    }

    public List<String> receiveAllStudentInfoByLectureIdForRecord() throws ClassNotFoundException {
        clientSideView.displayMessage(
                "Below are all students and their attendance status for this lecture,  please record their attendances in turn.\n");
        try {
            List<String> response = getResponseList();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            List<String> warning = new ArrayList<>();
            warning.add("ERROR");
            warning.add("Something went wrong.");
            return warning;
        }
    }

    public List<String> receiveAllLectureBySectionId(int n) {
        clientSideController.displayPromptForFacultyGetLectures(n);
        try {
            // get from server
            List<String> responseList = getResponseList();
            clientSideView.displayMessage("" + responseList.size());
            if (responseList.isEmpty()) {
                clientSideView
                        .displayMessage("This Section does not have any Lecture right now, please contact Admin!");
                responseList.add("ERROR");
                responseList.add("This Section does not have any Lecture right now, please contact Admin!");
                return responseList;
            } else {
                if (responseList.get(0).equals("ERROR")) {
                    clientSideView.displayMessage(responseList.get(1));
                    return responseList;
                } else {
                    return responseList;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            List<String> warning = new ArrayList<>();
            warning.add("ERROR");
            warning.add("Something went wrong.");
            return warning;
        }
    }

    public List<String> receiveAllTakenSectionAndSendChoice(int num) {
        clientSideController.displayPromptForFacultyGetSections(num);
        try {
            // get from server
            List<String> responseList = getResponseList();
            clientSideView.displayMessage("" + responseList.size());
            if (responseList.isEmpty()) {
                clientSideView.displayMessage("You haven't taught any classes this semester!");
                responseList.add("ERROR");
                responseList.add("You haven't taught any classes this semester!");
                return responseList;
            } else {
                if (responseList.get(0).equals("ERROR")) {
                    clientSideView.displayMessage(responseList.get(1));
                    return responseList;
                } else {
                    return responseList;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            List<String> warning = new ArrayList<>();
            warning.add("ERROR");
            warning.add("Something went wrong.");
            return warning;
        }
    }

    // Professor-specific functionality
    public void professorFunctionality(int choice) {
        try {
            if (choice == 5) {
                out.writeObject(choice); // int type
                out.flush();
                // exit
                disconnectFromServer();
            } else if (choice == 1) {
                // record attendance
                out.writeObject(choice); // int type to String
                out.flush();
                // recordAttendance();
            } else if (choice == 2) {
                // update attendance
                out.writeObject(choice); // int type to String
                out.flush();
                // updateAttendance();
            } else if (choice == 3) {
                // export file
                out.writeObject(choice); // int type to String
                out.flush();
                // exportStuentAttendance();
            } else if (choice == 4) {
                // select courses
                out.writeObject(choice);
                out.flush();
                // beFaculty();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connectToServer() throws ClassNotFoundException {

        while (!connected) {
            try {
                // Connect to the server
                socket = new Socket("vcm-37905.vm.duke.edu", 8088);
                // in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                // out = new PrintWriter(socket.getOutputStream(), true);
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());

                // Read connection status from server
                int connectionStatus = (int) in.readObject(); // int type
                if (connectionStatus == 1) {
                    clientSideView.displayMessage("Connected to server.");
                    connected = true;
                } else {
                    clientSideView.displayMessage("Failed to connect to server.");
                    clientSideView.displayMessage("Trying to connect again...");
                    try {
                        Thread.sleep(2000); // Wait for 2 seconds before retrying
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Disconnect from the server
    public void disconnectFromServer() {
        try {
            if (socket != null && !socket.isClosed()) {
                in.close();
                out.close();
                socket.close();
                connected = false;
                clientSideView.displayMessage("Disconnected from the server.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
