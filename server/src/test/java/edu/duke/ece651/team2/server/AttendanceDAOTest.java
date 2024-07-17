package edu.duke.ece651.team2.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import edu.duke.ece651.team2.shared.AttendanceRecord;
import edu.duke.ece651.team2.shared.AttendanceStatus;
import edu.duke.ece651.team2.shared.Password;

public class AttendanceDAOTest {
    DAOFactory factory = new DAOFactory();
    AttendanceDAO attendanceDAO = new AttendanceDAO(factory);


    @Test
    public void testCreate(){
        AttendanceRecord ar = new AttendanceRecord(1, AttendanceStatus.ABSENT, 1);
        attendanceDAO.create(ar);
        assertEquals(AttendanceStatus.ABSENT,attendanceDAO.get(1, 1).getStatus());
        attendanceDAO.remove(ar);
        assertEquals(0, attendanceDAO.list().size());
    }

    @Test
    public void testupdate(){
        AttendanceRecord ar = new AttendanceRecord(1, AttendanceStatus.ABSENT, 1);
        attendanceDAO.create(ar);
        ar = new AttendanceRecord(1, AttendanceStatus.PRESENT, 1);
        attendanceDAO.update(ar);
        assertEquals(1, attendanceDAO.list().size());
        assertEquals(AttendanceStatus.PRESENT, attendanceDAO.get(1, 1).getStatus());
        AttendanceRecord ar1 = new AttendanceRecord(2, AttendanceStatus.TARDY, 1);
        attendanceDAO.create(ar1);
        assertEquals(2,attendanceDAO.getAllAttendancesForLecture(1).size());
        attendanceDAO.remove(ar);
        attendanceDAO.remove(ar1);
        assertEquals(0, attendanceDAO.list().size());

    }

}
