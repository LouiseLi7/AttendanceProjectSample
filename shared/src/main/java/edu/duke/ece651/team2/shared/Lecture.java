package edu.duke.ece651.team2.shared;

import java.time.LocalDate;
import java.util.Calendar;

/**
 * The Lecture class represents a lecture for a course.
 */
public class Lecture {
    private Integer LectureID;
    private final Integer sectionId;
    private int year;
    private int month;
    private int day;

    public Lecture(Integer sectionId){
        this.sectionId = sectionId;
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public Lecture(Integer sectionId, LocalDate date) {
        this.sectionId = sectionId;
        year = date.getYear();
        month = date.getMonthValue();
        day = date.getDayOfMonth();
    }

    public Integer getLectureID() {
        return LectureID;
    }

    public void setLectureID(Integer lectureID) {
        LectureID = lectureID;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    // public void setDate(LocalDate d){
    //     year = d.getYear();
    //     month = d.getMonthValue();
    //     day = d.getDayOfMonth();
    // }

}
