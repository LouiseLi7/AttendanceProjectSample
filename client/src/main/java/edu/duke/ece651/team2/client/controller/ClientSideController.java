package edu.duke.ece651.team2.client.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.duke.ece651.team2.shared.*;

public class ClientSideController {
    private ClientSideView clientSideView;

    public ClientSideController(ClientSideView clientSideView) {
        this.clientSideView = clientSideView;
    }

    public void displayPromptForStudent(int n) {
        if (n == 1) {
            clientSideView.displayMessage(
                    "Below are all the courses you are enrolled in this semester, please select one to set your email preferences.");
        } else if (n == 2) {
            clientSideView.displayMessage(
                    "Below are all the courses you are enrolled in this semester, please select one to get your attendance report.");
        } else if (n == 3) {
            clientSideView.displayMessage(
                    "Below are all the courses you are enrolled in this semester, please select one to continue.");
        } else if (n == 4) {
            clientSideView.displayMessage(
                    "Below are all the lectures you are enrolled in this semester, please select one to check in.");
        }
    }

    public void displayPromptForFacultyGetSections(int n) {
        if (n == 1) {
            clientSideView.displayMessage(
                    "Below are the courses you are teaching this semester, please select a course to record your attendance.");
        } else if (n == 2) {
            clientSideView.displayMessage(
                    "Below are the courses you are teaching this semester, please select a course to update your attendance.");
        } else if (n == 3) {
            clientSideView.displayMessage(
                    "Below are the courses you are teaching this semester, please select a course to export students attendance info.");
        }
    }

    public void displayPromptForFacultyGetLectures(int n) {
        if (n == 1) {
            clientSideView.displayMessage(
                    "Below are the lectures you are teaching this semester, please select a course to record your attendance.");
        } else if (n == 2) {
            clientSideView.displayMessage(
                    "Below are the lectures you are teaching this semester, please select a course to update your attendance.");
        } else if (n == 3) {
            clientSideView.displayMessage(
                    "Below are the lectures you are teaching this semester, please select a course to export students attendance info.");
        }
    }

}