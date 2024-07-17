//package edu.duke.ece651.team2.shared;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.parallel.ResourceAccessMode;
//import org.junit.jupiter.api.parallel.ResourceLock;
//import org.junit.jupiter.api.parallel.Resources;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.PrintStream;
//import java.security.GeneralSecurityException;
//import java.util.Date;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class EventManagerTest {
//    private EventManager eventManager;
//    private TestEventListener listener1;
//    private TestEventListener listener2;
//    private Student student;
//    private AttendanceRecord record;
//
//    @BeforeEach
//    void setUp() {
//        eventManager = new EventManager();
//        listener1 = new TestEventListener();
//        listener2 = new TestEventListener();
//        student = new Student("Qianyi", "qx49", "qx49@duke.edu", "qxxx49");
//        // record = new AttendanceRecord();
//        record = new AttendanceRecord(new Date(), "qx49", "Qianyi", AttendanceStatus.PRESENT,
//                "ECE651");
//    }
//
//    @Test
//    public void testSubscribe() {
//        eventManager.subscribe(listener1);
//        eventManager.subscribe(listener2);
//        assertEquals(2, eventManager.getListeners().size());
//    }
//
//    @Test
//    void testUnsubscribe() {
//        eventManager.subscribe(listener1);
//        eventManager.subscribe(listener2);
//        eventManager.unsubscribe(listener1);
//        assertEquals(1, eventManager.getListeners().size());
//    }
//
//    @Test
//    void testNotifyAttendanceChanged() throws IOException, GeneralSecurityException {
//        eventManager.subscribe(listener1);
//        eventManager.subscribe(listener2);
//        eventManager.notifyAttendanceChanged(student, record);
//        assertEquals(1, listener1.getAttendanceChangedCount());
//        assertEquals(1, listener2.getAttendanceChangedCount());
//    }
//
//    private static class TestEventListener implements EventListener {
//        private int attendanceChangedCount = 0;
//
//        @Override
//        public void attendanceChanged(Student student, AttendanceRecord record) {
//            attendanceChangedCount++;
//        }
//
//        public int getAttendanceChangedCount() {
//            return attendanceChangedCount;
//        }
//    }
//}
//// @Disabled
//// @Test
//// @ResourceLock(value = Resources.SYSTEM_OUT, mode =
//// ResourceAccessMode.READ_WRITE)
//// public void testNotifyAttendanceChanged() throws IOException,
//// GeneralSecurityException {
//// EventManager eventManager = new EventManager();
//// EmailAlertsListener emailAlertsListener = new EmailAlertsListener();
//// eventManager.subscribe(emailAlertsListener);
//
//// EmailAlertsListener emailAlertsListener2 = new EmailAlertsListener();
//// eventManager.subscribe(emailAlertsListener2);
//// eventManager.unsubscribe(emailAlertsListener2);
//
//// ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//// PrintStream out = new PrintStream(bytes, true);
//// PrintStream oldOut = System.out;
//
//// // try {
//// // System.setOut(out);
//// // eventManager.notifyAttendanceChanged();
//// // } finally {
//// // System.setOut(oldOut);
//// // }
//
//// String expected = "Sending email alert to test@example.com...\n";
//// String actual = bytes.toString();
//// assertEquals(expected, actual);
//// }
