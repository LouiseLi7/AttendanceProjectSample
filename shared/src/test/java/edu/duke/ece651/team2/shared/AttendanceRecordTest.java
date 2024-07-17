package edu.duke.ece651.team2.shared;

import java.util.Calendar;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AttendanceRecordTest {
   private AttendanceRecord record1;
   private Integer testStudentID;
   private AttendanceStatus testStatus;
   private Integer testLectureID;

   @BeforeEach
   void setUp() {
       testStudentID = 2;
       testStatus = AttendanceStatus.PRESENT; // Assume true means present
       testLectureID = 3;
       record1 = new AttendanceRecord(testStudentID, testStatus, testLectureID);
   }

   @Test
   void testSetGetRecordId(){
        record1.setRecordID(4);
        assertEquals(4,record1.getRecordID());
   }

   @Test
   void testSetGetStudentID() {
        assertEquals(testStudentID, record1.getStudentId());
   }


   @Test
   void testGetStatus() {
       assertEquals(testStatus, record1.getStatus());
       assertEquals(record1.getStatus(), AttendanceStatus.PRESENT);
   }

   @Test
   void testSetStatus() {
       AttendanceStatus newTestStatus = AttendanceStatus.ABSENT;
       record1.setStatus(newTestStatus);
       assertEquals(newTestStatus, record1.getStatus());
       assertEquals(record1.getStatus(), AttendanceStatus.ABSENT);
       newTestStatus = AttendanceStatus.TARDY;
       record1.setStatus(newTestStatus);
       assertEquals(newTestStatus, record1.getStatus());
       assertEquals(record1.getStatus(), AttendanceStatus.TARDY);
   }

   @Test
   void testGetLectureID() {
       assertEquals(testLectureID, record1.getLectureId());
   }
}
