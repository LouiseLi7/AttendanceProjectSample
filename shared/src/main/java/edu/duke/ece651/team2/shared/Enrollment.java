package edu.duke.ece651.team2.shared;

public class Enrollment {
    private final Integer sectionId;
    private final Integer studentId;

    private final boolean notify;

    public Enrollment(Integer sectionId, Integer studentId, boolean notify) {
        this.sectionId = sectionId;
        this.studentId = studentId;
        this.notify = notify;
    }

    public boolean isNotify() {
        return notify;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public Integer getStudentId() {
        return studentId;
    }
}
