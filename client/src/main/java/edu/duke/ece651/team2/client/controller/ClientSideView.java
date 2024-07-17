package edu.duke.ece651.team2.client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientSideView {

    private BufferedReader reader;

    public ClientSideView() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    // public String promptUser(String prompt) throws IOException {
    //     System.out.println(prompt);
    //     return reader.readLine();
    // }

}
