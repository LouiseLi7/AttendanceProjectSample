package edu.duke.ece651.team2.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class CourseTest {

    Integer uInteger = 0;
    String courseName1 = "ECE 651";
    Course c = new Course(courseName1, uInteger);


    /*
    * BufferedReader provideInput(String data) {
    * BufferedReader input = new BufferedReader(new StringReader(data));
    * return input;
    * }
    */

    @Test
    void testGetuInteger(){
        assertEquals(0, c.getUniversityId());
    }

    @Test
    void testGetAndSetName(){
        assertEquals(courseName1, c.getName());
        courseName1 = "ECE651";
        c.setName(courseName1);
        assertEquals(courseName1, c.getName());
        courseName1 = "ECE 651";
        c.setCourseName(courseName1);
        assertEquals(courseName1, c.getName());
    }

    @Test
    void testSetAndGetCourseId(){
        c.setCourseID(12);
        assertEquals(12, c.getCourseID());
    }


    
    

}
