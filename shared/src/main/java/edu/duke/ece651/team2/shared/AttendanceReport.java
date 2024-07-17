package edu.duke.ece651.team2.shared;

public class AttendanceReport {
    private AttendanceRecord record;
    private float score;
    private String studentLegalName;
    private String attendanceDate;

    public AttendanceReport(AttendanceRecord record, String studentLegalName,
            String attendanceDate) {
        this.record = record;
        this.studentLegalName = studentLegalName;
        this.attendanceDate = attendanceDate;
        this.score = calculateScore(record);
    }

    private float calculateScore(AttendanceRecord record) {
        float score = 0;
        if (record.getStatus() == AttendanceStatus.TARDY) {
            score += 0.8;
        } else if (record.getStatus() == AttendanceStatus.PRESENT) {
            score += 1;
        }
        return score;
    }

    public float getScore() {
        return score;
    }

    public Integer getStudentId() {
        return record.getStudentId();
    }

    public AttendanceRecord getRecord() {
        return record;
    }

    public Integer getLectureId() {
        return record.getLectureId();
    }

    public String getStudentLegalName() {
        return studentLegalName;
    }

    public String getAttendanceDate() {
        return attendanceDate;
    }
}
