package edu.duke.ece651.team2.shared;

import java.util.List;

/**
 * The Course class represents a course that students can take.
 */
public class Course {
    private final Integer universityId;
    /**
     * The name of the Course.
     */
    private String courseName;
    /**
     * The ID of the Course.
     */
    private Integer courseID;
    /**
     * This constructor will create the Course object.
     *
     * @param name is the Course's name.
     */
    public Course(String name, Integer universityId) {
        this.courseName = name;
        this.universityId = universityId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getUniversityId() {
        return universityId;
    }

    /**
     * @return course name
     */
    public String getName() {
        return courseName;
    }

    public void setName(String name){
        courseName = name;
    }

    /**
     * @return courseID
     */
    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

}
