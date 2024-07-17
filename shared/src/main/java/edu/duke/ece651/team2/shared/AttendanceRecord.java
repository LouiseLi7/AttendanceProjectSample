package edu.duke.ece651.team2.shared;


/**
 * The AttendanceRecord class represents a record of student attendance for a
 * specific lecture.
 */
public class AttendanceRecord {
    /**
     * The ID of the student associated with the attendance record.
     */
    private final Integer studentId;
    /**
     * The status of the student's attendance (true if present, false if absent).
     */
    private AttendanceStatus status;
    /**
     * The ID of the lecture associated with the attendance record.
     */
    private final Integer lectureId;

    private int recordID;

    /**
     * Constructs a new AttendanceRecord object with the specified attendance date,
     * student ID, status, and lecture ID.
     *
     * @param attendanceDate The date of the attendance record.
     * @param studentID      The ID of the student associated with the attendance
     *                       record.
     * @param studentName    The name of the student associated with the attendance
     * @param lectureID      The ID of the lecture associated with the attendance
     *                       record.
     * @param status         The status of the student's attendance (true if
     *                       present, false if absent).
     */
    public AttendanceRecord(Integer studentId, AttendanceStatus status, Integer lec) {
        this.studentId = studentId;
        this.status = status;
        this.lectureId = lec;
    }

    public void setRecordID(int id){
        this.recordID = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }

    public Integer getLectureId() {
        return lectureId;
    }

    public int getRecordID() {
        return recordID;
    }



}
