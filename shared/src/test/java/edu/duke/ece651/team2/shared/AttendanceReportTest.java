package edu.duke.ece651.team2.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AttendanceReportTest {
    AttendanceRecord r1 = new AttendanceRecord(2, AttendanceStatus.PRESENT, 3);
    AttendanceRecord r2 = new AttendanceRecord(2, AttendanceStatus.TARDY, 4);
    AttendanceRecord r3 = new AttendanceRecord(2, AttendanceStatus.ABSENT, 5);

    AttendanceReport rp1 = new AttendanceReport(r1, "Stu 1", "2023-1-1");
    AttendanceReport rp2 = new AttendanceReport(r2, "Stu 1", "2023-1-3");
    AttendanceReport rp3 = new AttendanceReport(r3, "Stu 1", "2023-1-5");

    @Test
    public void testAll(){
        assertEquals(1,rp1.getScore());
        assertEquals(0.8, Math.round(rp2.getScore() * 10.0f) / 10.0f, 0.0001);
        assertEquals(0, rp3.getScore());
        assertEquals("Stu 1", rp1.getStudentLegalName());
        assertEquals(2, rp1.getStudentId());
        assertEquals(r1, rp1.getRecord());
        assertEquals(r2, rp2.getRecord());
        assertEquals(r3, rp3.getRecord());
        assertEquals(3, rp1.getLectureId());
        assertEquals("2023-1-1", rp1.getAttendanceDate());
        assertEquals(4, rp2.getLectureId());
        assertEquals("2023-1-3", rp2.getAttendanceDate());
        assertEquals(5, rp3.getLectureId());
        assertEquals("2023-1-5", rp3.getAttendanceDate());
    }
}


